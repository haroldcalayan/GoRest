package com.haroldcalayan.gorest.ui.main.user.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import com.haroldcalayan.gorest.R
import com.haroldcalayan.gorest.base.BaseActivity
import com.haroldcalayan.gorest.databinding.ActivityUserDetailBinding

class UserDetailActivity : BaseActivity<UserDetailViewModel, ActivityUserDetailBinding>() {

    override val layoutId = R.layout.activity_user_detail
    override val viewModel: UserDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()

        if (savedInstanceState == null) {
            val fragment = UserDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(
                        UserDetailFragment.ARG_USER,
                        intent.getStringExtra(UserDetailFragment.ARG_USER)
                    )
                }
            }

            supportFragmentManager.beginTransaction()
                .add(R.id.item_detail_container, fragment)
                .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) : Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun initViews() {
        super.initViews()

        setSupportActionBar(findViewById(R.id.detail_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.main_client_detail)
    }

    companion object {
        const val ARG_USER = "user"
    }
}