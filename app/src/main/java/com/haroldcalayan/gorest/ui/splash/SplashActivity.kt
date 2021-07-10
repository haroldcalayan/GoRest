package com.haroldcalayan.gorest.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.haroldcalayan.gorest.R
import com.haroldcalayan.gorest.base.BaseActivity
import com.haroldcalayan.gorest.databinding.ActivitySplashBinding
import com.haroldcalayan.gorest.ui.main.MainActivity
import com.haroldcalayan.gorest.util.Constants.SPLASH_SCREEN_LIFE
import kotlinx.coroutines.*

class SplashActivity : BaseActivity<SplashViewModel, ActivitySplashBinding>() {

    override val layoutId = R.layout.activity_splash
    override val viewModel: SplashViewModel by viewModels()

    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }

    override fun onResume() {
        super.onResume()
        startTimer()
    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }

    override fun initViews() {
        super.initViews()
        hideSystemUI()
    }

    private fun startTimer() {
        activityScope.launch {
            delay(SPLASH_SCREEN_LIFE)
            openMain()
            finish()
        }
    }

    private fun openMain() {
        Intent(this, MainActivity::class.java).apply {
            startActivity(this)
        }
    }
}