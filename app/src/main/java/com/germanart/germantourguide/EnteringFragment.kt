package com.germanart.germantourguide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.germanart.germantourguide.databinding.FragmentEnteringBinding
import com.google.android.material.snackbar.Snackbar


class EnteringFragment : Fragment() {
    private var _binding: FragmentEnteringBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentStartBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnteringBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        try {
            binding.btnInfo.setOnClickListener {
                findNavController().navigate(R.id.action_enteringFragment_to_addInfoFragment)
            }
            binding.btnImgExitApp.setOnClickListener {
                initAlertDialogBuilderExit()
            }
            binding.imgAiports.setOnClickListener {
                findNavController().navigate(R.id.action_enteringFragment_to_airportsGermanFragment)
            }
            binding.imgCuisine.setOnClickListener {
                findNavController().navigate(R.id.action_enteringFragment_to_germanCuisineFragment)
            }
            binding.imgIntFacts.setOnClickListener {
                findNavController().navigate(R.id.action_enteringFragment_to_intrestingFactsGermanFragment)
            }
            binding.imgAirQuality.setOnClickListener {
                findNavController().navigate(R.id.action_enteringFragment_to_airQualityFragment)
            }


        } catch (e: Exception) {
            snackBarError()
        }

        super.onViewCreated(view, savedInstanceState)
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun snackBarError() {
        Snackbar.make(
            binding.root,
            "There is some error, try again",
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
}