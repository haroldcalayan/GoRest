package com.haroldcalayan.gorest.ui.main.user.add

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import com.haroldcalayan.gorest.R
import com.haroldcalayan.gorest.base.BaseActivity
import com.haroldcalayan.gorest.data.model.User
import com.haroldcalayan.gorest.databinding.ActivityAddUserBinding
import com.haroldcalayan.gorest.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddUserActivity : BaseActivity<AddUserViewModel, ActivityAddUserBinding>() {

    override val layoutId = R.layout.activity_add_user
    override val viewModel: AddUserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        observe()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add_user, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) : Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.menu_save -> {
                createUser()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun initViews() {
        super.initViews()
        initToolbar()
    }

    override fun observe() {
        super.observe()
        viewModel.createdUser.observe(this, {
            showToast(R.string.message_client_is_created_successfully)
            finish()
        })
    }

    private fun initToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.main_new_client)
    }

    private fun createUser() {
        val name = binding.edittextName.text.toString()
        val email = binding.edittextEmail.text.toString()
        val gender = binding.spinnerGender.selectedItem.toString()
        val status = binding.spinnerStatus.selectedItem.toString()

        when {
            name.isEmpty() -> {
                showToast(R.string.message_name_not_empty)
                return
            }
            email.isEmpty() -> {
                showToast(R.string.message_email_not_empty)
                return
            }
            else -> {
                val user = User(name = name, email = email, gender = gender, status = status)
                viewModel.createUser(user)
            }
        }
    }

}
