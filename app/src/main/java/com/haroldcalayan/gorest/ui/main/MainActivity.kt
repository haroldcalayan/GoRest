package com.haroldcalayan.gorest.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.haroldcalayan.gorest.R
import com.haroldcalayan.gorest.base.BaseActivity
import com.haroldcalayan.gorest.databinding.ActivityMainBinding
import com.haroldcalayan.gorest.ui.main.product.category.ProductCategoryActivity
import com.haroldcalayan.gorest.ui.main.user.master.UserMasterActivity

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val layoutId = R.layout.activity_main
    override val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }

    override fun initViews() {
        super.initViews()
        binding.buttonUser.setOnClickListener {
            openUser()
        }
        binding.buttonProduct.setOnClickListener {
            openProductCategory()
        }
    }

    private fun openUser() {
        val intent = Intent(this, UserMasterActivity::class.java)
        startActivity(intent)
    }

    private fun openProductCategory() {
        val intent = Intent(this, ProductCategoryActivity::class.java)
        startActivity(intent)
    }
}