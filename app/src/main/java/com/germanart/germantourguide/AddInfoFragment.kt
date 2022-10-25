package com.germanart.germantourguide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.germanart.germantourguide.databinding.FragmentAddInfoBinding
import com.germanart.germantourguide.some_utils.CustomPagerAdapter
import com.google.android.material.snackbar.Snackbar

class AddInfoFragment : Fragment() {
    private var _binding: FragmentAddInfoBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentStartBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            binding.btnImgExit.setOnClickListener {
                initAlertDialogBuilderExit()
            }

            val listOfImages = generateImgForPager()
            val pagerAdapter = CustomPagerAdapter(listOfImages)
            binding.pager.adapter = pagerAdapter
            binding.circleIndicatorPhotoGalery.setViewPager(binding.pager)


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
}