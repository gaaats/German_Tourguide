package com.germanart.germantourguide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.germanart.germantourguide.api.air.AirQualityServiceAPI
import com.germanart.germantourguide.api.air.AirQualitySingle
import com.germanart.germantourguide.api.recipes.CuisineAdapterList
import com.germanart.germantourguide.api.recipes.MyInterceptor
import com.germanart.germantourguide.api.recipes.RecipeServiceAPI
import com.germanart.germantourguide.api.recipes.RecipesListNetItem
import com.germanart.germantourguide.databinding.FragmentAirQualityBinding
import com.germanart.germantourguide.some_utils.MainVievModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AirQualityFragment : Fragment() {

    private var resultSingle = AirQualitySingle(
        cO = null, nO2 = null, o3 = null, overallAqi = null, pM10 = null, pM25 = null, sO2 = null
    )

    private val mainVievModel by activityViewModels<MainVievModel>()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(AirQualityServiceAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(MyInterceptor())
    }.build()

    val api: AirQualityServiceAPI by lazy {
        retrofit.create(AirQualityServiceAPI::class.java)
    }

    private var _binding: FragmentAirQualityBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentStartBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAirQualityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {

//            val textAboutAir = "Carbon monoxide 293.73, AQI 3\n" +
//                    "Nitrogen dioxide(NO2) 30.5, AQI 38\n" +
//                    "Ozone(O3) 19.67 AQI 16\n" +
//                    "Sulphur dioxide(SO2) 6.14 AQI 9\n" +
//                    "PM2.5 particulates 10.86 AQI 35\n" +
//                    "PM10 particulates 12.26 AQI11\n" +
//                    "Overall AQI 38."



            initProgBar()

            binding.btnImgExit.setOnClickListener {
                initAlertDialogBuilderExit()
            }

            binding.btnOk.setOnClickListener {
                requireActivity().onBackPressed()
            }


        } catch (e: Exception) {
            snackBarError()
        }

        super.onViewCreated(view, savedInstanceState)
    }


    private fun snackBarError() {
        Snackbar.make(
            binding.root,
            "Oops. Something went wrong. Please try again.",
            Snackbar.LENGTH_LONG
        ).show()
        requireActivity().onBackPressed()
    }

    private fun initAlertDialogBuilderExit() {
        AlertDialog.Builder(requireContext())
            .setTitle("Exit")
            .setMessage("The current data will not be saved. Do you really want to log out?")
            .setPositiveButton("Yes, Exit") { _, _ ->
                requireActivity().onBackPressed()
            }
            .setNegativeButton("Deny") { _, _ ->
            }
            .setCancelable(true)
            .create()
            .show()
    }

    private fun generateImgForPager(): List<Int> {
        return listOf(
            R.drawable.oktoberfest__,
            R.drawable.waitress,
            R.drawable.waiter,
            R.drawable.bavaria,
            R.drawable.accordionist,
            R.drawable.carriage,
            R.drawable.oktoberfest,
            R.drawable.beer_tap,

            )
    }

    private fun initProgBar() {
        lifecycleScope.launch {
            binding.imgAirQualityLogo.visibility = View.GONE
            binding.btnOk.visibility = View.GONE
            binding.tvAboutAirQuality.visibility = View.GONE
            binding.tvAboutAirQualityTitle.visibility = View.GONE
            binding.tvDovnText.visibility = View.GONE
            binding.btnImgExit.visibility = View.GONE
            binding.btnImgExit.visibility = View.GONE


            binding.lottieAnimVaiting.visibility = View.VISIBLE
            binding.tvPleaseVaitLoading.visibility = View.VISIBLE

            loadList()

            delay(2000)


            binding.imgAirQualityLogo.visibility = View.VISIBLE
            binding.tvAboutAirQualityTitle.visibility = View.VISIBLE
            binding.tvAboutAirQuality.visibility = View.VISIBLE
            binding.tvDovnText.visibility = View.VISIBLE
            binding.btnOk.visibility = View.VISIBLE
            binding.btnImgExit.visibility = View.VISIBLE

            binding.lottieAnimVaiting.visibility = View.GONE
            binding.tvPleaseVaitLoading.visibility = View.GONE
        }
    }

    private fun loadList() {
        lifecycleScope.launch {
            try {
                val result = api.getRecipes()
                if (result.isSuccessful) {
                    resultSingle = result.body()!!

                    val textAboutAir = "Carbon monoxide ${resultSingle.cO!!.concentration}, AQI ${resultSingle.cO!!.aqi}\n" +
                            "Nitrogen dioxide(NO2) ${resultSingle.nO2!!.concentration}, AQI ${resultSingle.nO2!!.aqi}\n" +
                            "Ozone(O3) ${resultSingle.o3!!.concentration} AQI ${resultSingle.o3!!.aqi}\n" +
                            "Sulphur dioxide(SO2) ${resultSingle.sO2!!.concentration} AQI ${resultSingle.sO2!!.aqi}\n" +
                            "PM2.5 particulates ${resultSingle.pM25!!.concentration} AQI ${resultSingle.pM25!!.aqi}\n" +
                            "PM10 particulates ${resultSingle.pM10!!.concentration} AQI ${resultSingle.pM10!!.aqi}\n" +
                            "Overall AQI ${resultSingle.overallAqi!!}."

                    binding.tvAboutAirQuality.text = textAboutAir
                } else {
                    snackBarError()
                }
            } catch (e: Exception) {
                snackBarError()
            }
        }
    }
}