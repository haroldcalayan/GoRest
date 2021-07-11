package com.haroldcalayan.gorest.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.haroldcalayan.gorest.R
import com.haroldcalayan.gorest.base.BaseActivity
import com.haroldcalayan.gorest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val layoutId = R.layout.activity_main
    override val viewModel: MainViewModel by viewModels()

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }

    override fun onBackPressed() {
        when (navController.currentDestination?.id) {
            R.id.navigation_user -> finish()
            else -> navigateToUser()
        }
    }

    override fun initViews() {
        super.initViews()
        initNavigation()
    }

    private fun initNavigation() {
        navController = findNavController(R.id.navigation_host_fragment)
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    private fun navigateToUser() = navController.navigate(R.id.navigation_user)

    private fun navigateToProduct() = navController.navigate(R.id.navigation_product)
}