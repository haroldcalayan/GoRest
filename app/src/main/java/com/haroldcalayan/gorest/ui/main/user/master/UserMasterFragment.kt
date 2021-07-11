package com.haroldcalayan.gorest.ui.main.user.master

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haroldcalayan.gorest.R
import com.haroldcalayan.gorest.data.model.User
import com.haroldcalayan.gorest.databinding.FragmentUserMasterBinding
import com.haroldcalayan.gorest.ui.main.MainViewModel
import com.haroldcalayan.gorest.ui.main.user.add.AddUserActivity
import com.haroldcalayan.gorest.ui.main.user.detail.UserDetailActivity
import com.haroldcalayan.gorest.ui.main.user.detail.UserDetailFragment
import com.haroldcalayan.gorest.util.JsonUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserMasterFragment : Fragment(), UserAdapter.UserAdapterListener {

    private val viewModel: MainViewModel by activityViewModels()
    private val binding by lazy { FragmentUserMasterBinding.inflate(layoutInflater) }

    private lateinit var adapter: UserAdapter
    private var scrollView: NestedScrollView? = null
    private var recyclerViewUserList: RecyclerView? = null
    private var twoPane: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scrollView = view.findViewById(R.id.user_item_detail_container)
        recyclerViewUserList = view.findViewById(R.id.user_item_list)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
        observe()
        viewModel.getUsers()
    }

    private fun initViews() {
        if (scrollView != null) twoPane = true
        recyclerViewUserList?.let { setupUserList(it) }

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
                    1 -> adapter.updateData(users?.filter { it.status.toLowerCase() == "active" }
                        .orEmpty())
                    2 -> adapter.updateData(users?.filter { it.status.toLowerCase() == "inactive" }
                        .orEmpty())
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        binding.imageviewAdd.setOnClickListener {
            openAddUser()
        }
    }

    private fun observe() {
        viewModel.userList.observe(viewLifecycleOwner, {
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
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.user_item_detail_container, fragment)
                ?.commit()
        } else {
            val intent = Intent(requireContext(), UserDetailActivity::class.java).apply {
                putExtra(UserDetailActivity.ARG_USER, serializedItem)
            }
            startActivity(intent)
        }
    }

    private fun setupUserList(recyclerView: RecyclerView) {
        adapter = UserAdapter(emptyList(), this)
        recyclerView.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }

    private fun openAddUser() {
        Intent(requireContext(), AddUserActivity::class.java).apply {
            startActivity(this)
        }
    }
}