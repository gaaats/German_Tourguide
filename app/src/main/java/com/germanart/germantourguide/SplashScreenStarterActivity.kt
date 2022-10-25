package com.germanart.germantourguide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.lifecycleScope
import com.germanart.germantourguide.databinding.ActivitySplashScreenStarterBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashScreenStarterActivity : AppCompatActivity() {

    private var _binding: ActivitySplashScreenStarterBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("ActivityMainBinding = null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashScreenStarterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initProgBar()
        Handler(Looper.myLooper()!!).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_SCREEN_TIME_FOR_LOAD)
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun initProgBar() {
        lifecycleScope.launch {
            for (progress in 1..100) {
                withContext(Dispatchers.Main) {
                    binding.progBarSplashScrn.progress = progress
                }
                delay(SPLASH_SCREEN_TIME_FOR_LOAD / 130)
            }
        }
    }

    companion object{
        private const val SPLASH_SCREEN_TIME_FOR_LOAD: Long = 2200
    }
}