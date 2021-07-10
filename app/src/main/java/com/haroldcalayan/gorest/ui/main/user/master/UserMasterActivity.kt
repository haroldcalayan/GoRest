package com.haroldcalayan.gorest.ui.main.user.master

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.activity.viewModels
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import com.haroldcalayan.gorest.R
import com.haroldcalayan.gorest.base.BaseActivity
import com.haroldcalayan.gorest.data.model.User
import com.haroldcalayan.gorest.databinding.ActivityUserMasterBinding
import com.haroldcalayan.gorest.ui.main.user.detail.UserDetailActivity
import com.haroldcalayan.gorest.ui.main.user.detail.UserDetailFragment
import com.haroldcalayan.gorest.util.JsonUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserMasterActivity : BaseActivity<UserMasterViewModel, ActivityUserMasterBinding>(),
    UserAdapter.UserAdapterListener {

    override val layoutId = R.layout.activity_user_master
    override val viewModel: UserMasterViewModel by viewModels()

    private lateinit var adapter: UserAdapter
    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        observe()
        viewModel.getUsers()
    }

    override fun initViews() {
        super.initViews()

        if (findViewById<NestedScrollView>(R.id.item_detail_container) != null) twoPane = true
        setupUserList(findViewById(R.id.user_item_list))

        binding.spinnerUserStatuses.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val users = viewModel.userList.value
                when (position) {
                    0 -> adapter.updateData(users.orEmpty())
                    1 -> adapter.updateData(users?.filter { it.status == "Active" }.orEmpty())
                    2 -> adapter.updateData(users?.filter { it.status == "Inactive" }.orEmpty())
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    override fun observe() {
        super.observe()
        viewModel.userList.observe(this, {
            adapter.updateData(it.orEmpty())
        })
    }

    override fun onUserItemClick(user: User) {
        val serializedItem = JsonUtils.getGson().toJson(user)
        if (twoPane) {
            val fragment = UserDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(UserDetailFragment.ARG_USER, serializedItem)
                }
            }
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.user_item_detail_container, fragment)
                .commit()
        } else {
            val intent = Intent(this, UserDetailActivity::class.java).apply {
                putExtra(UserDetailActivity.ARG_USER, serializedItem)
            }
            startActivity(intent)
        }
    }

    private fun setupUserList(recyclerView: RecyclerView) {
        adapter = UserAdapter(emptyList(), this)
        recyclerView.adapter = adapter
    }
}