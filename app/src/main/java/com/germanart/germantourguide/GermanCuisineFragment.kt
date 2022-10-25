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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.germanart.germantourguide.api.recipes.CuisineAdapterList
import com.germanart.germantourguide.api.recipes.MyInterceptor
import com.germanart.germantourguide.api.recipes.RecipeServiceAPI
import com.germanart.germantourguide.api.recipes.RecipesListNetItem
import com.germanart.germantourguide.databinding.FragmentGermanCuisineBinding
import com.germanart.germantourguide.some_utils.MainVievModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class GermanCuisineFragment : Fragment() {
    private var list = mutableListOf<RecipesListNetItem>()

    private val adapter by lazy {
        CuisineAdapterList()
    }

    private val mainVievModel by activityViewModels<MainVievModel>()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(RecipeServiceAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    private val client = OkHttpClient.Builder().apply {
        addInterceptor(MyInterceptor())
    }.build()

    val api: RecipeServiceAPI by lazy {
        retrofit.create(RecipeServiceAPI::class.java)
    }

    private var _binding: FragmentGermanCuisineBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("ActivityMainBinding = null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGermanCuisineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("favorit", "ResultFragment mainVievModel ${mainVievModel}")
        try {
            loadList()
            addVertAndHorDividers()
            initProgBar()
        } catch (e: Exception) {
            snackBarError()
        }

        binding.btnGoBack.setOnClickListener {
            try {
                initAlertDialogBuilderExit()
            } catch (e: Exception) {
                snackBarError()
            }
        }
        binding.btnImgExit.setOnClickListener {
            initAlertDialogBuilderExit()
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
            binding.btnGoBack.visibility = View.GONE
            binding.tvHelperName.visibility = View.GONE
            binding.tvHelperIngidients.visibility = View.GONE
            binding.cardV.visibility = View.GONE
            binding.btnImgExit.visibility = View.GONE
            delay(3000)
            binding.lottieAnimVaiting.visibility = View.VISIBLE
            binding.tvPleaseVaitLoading.visibility = View.VISIBLE

            binding.imgCuisineLogo.visibility = View.VISIBLE
            binding.cardV.visibility = View.VISIBLE
            binding.tvHelperName.visibility = View.VISIBLE
            binding.tvHelperIngidients.visibility = View.VISIBLE
            binding.btnGoBack.visibility = View.VISIBLE
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