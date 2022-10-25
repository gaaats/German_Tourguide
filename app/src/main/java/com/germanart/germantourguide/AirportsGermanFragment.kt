package com.germanart.germantourguide

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.germanart.germantourguide.api.airports.AirporstResponseItem
import com.germanart.germantourguide.api.airports.AirportsAdapterList
import com.germanart.germantourguide.api.airports.AirportsServiceAPI
import com.germanart.germantourguide.api.recipes.CuisineAdapterList
import com.germanart.germantourguide.api.recipes.MyInterceptor
import com.germanart.germantourguide.api.recipes.RecipeServiceAPI
import com.germanart.germantourguide.api.recipes.RecipesListNetItem
import com.germanart.germantourguide.databinding.FragmentAirportsGermanBinding
import com.germanart.germantourguide.databinding.FragmentGermanCuisineBinding
import com.germanart.germantourguide.some_utils.MainVievModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AirportsGermanFragment : Fragment() {
    private var list = mutableListOf<AirporstResponseItem>()

    private val adapter by lazy {
        AirportsAdapterList()
    }

    private val mainVievModel by activityViewModels<MainVievModel>()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(AirportsServiceAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(MyInterceptor())
    }.build()

    val api: AirportsServiceAPI by lazy {
        retrofit.create(AirportsServiceAPI::class.java)
    }

    private var _binding: FragmentAirportsGermanBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentAirportsGermanBinding = null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAirportsGermanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            Log.d("favorit", "ResultFragment mainVievModel ${mainVievModel}")
            try {
                loadList()
                addVertAndHorDividers()
                initProgBar()
            } catch (e: Exception) {
                snackBarError()
            }

            binding.btnImgExit.setOnClickListener {
                try {
                    initAlertDialogBuilderExit()
                } catch (e: Exception) {
                    snackBarError()
                }
            }
            binding.btnOk.setOnClickListener {
                initAlertDialogBuilderExit()
            }
        } catch (e: Exception) {
            snackBarError()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun loadList() {
        lifecycleScope.launch {
            try {
                val result = api.getRecipes()
                if (result.isSuccessful) {
                    list = result.body()!!
                    adapter.submitList(list)
                    binding.recyclerView.adapter = adapter
                } else {
                    snackBarError()
                }
            } catch (e: Exception) {
                snackBarError()
            }
        }
    }

    private fun snackBarError() {
        Snackbar.make(
            binding.root,
            "Oops. Something went wrong. Please try again.",
            Snackbar.LENGTH_LONG
        ).show()
        requireActivity().onBackPressed()
    }

    private fun initProgBar() {
        lifecycleScope.launch {
            binding.imgCuisineLogo.visibility = View.GONE
            binding.btnOk.visibility = View.GONE
            binding.tvHelperTitleAirport.visibility = View.GONE
            binding.tvHelperIata.visibility = View.GONE
            binding.cardV.visibility = View.GONE
            binding.btnImgExit.visibility = View.GONE
            delay(3000)
            binding.lottieAnimVaiting.visibility = View.VISIBLE
            binding.tvPleaseVaitLoading.visibility = View.VISIBLE

            binding.imgCuisineLogo.visibility = View.VISIBLE
            binding.cardV.visibility = View.VISIBLE
            binding.tvHelperTitleAirport.visibility = View.VISIBLE
            binding.tvHelperIata.visibility = View.VISIBLE
            binding.btnOk.visibility = View.VISIBLE
            binding.btnImgExit.visibility = View.VISIBLE
            binding.lottieAnimVaiting.visibility = View.GONE
            binding.tvPleaseVaitLoading.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun saveToClipBoard() {
        Snackbar.make(binding.root, "Saved!", Snackbar.LENGTH_LONG).show()
    }

    private fun addVertAndHorDividers() {
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
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


}