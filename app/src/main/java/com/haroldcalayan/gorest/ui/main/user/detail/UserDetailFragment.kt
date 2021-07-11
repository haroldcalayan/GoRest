package com.haroldcalayan.gorest.ui.main.user.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.haroldcalayan.gorest.R
import com.haroldcalayan.gorest.base.BaseFragment
import com.haroldcalayan.gorest.data.model.User
import com.haroldcalayan.gorest.databinding.FragmentUserDetailBinding
import com.haroldcalayan.gorest.util.JsonUtils
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class UserDetailFragment : BaseFragment<UserDetailFragmentViewModel, FragmentUserDetailBinding>() {

    override val layoutId = R.layout.fragment_user_detail
    override val viewModel: UserDetailFragmentViewModel by viewModels()

    private var user: User? = null
    private lateinit var adapter: TodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let {
            if (it.containsKey(ARG_USER)) {
                user = JsonUtils.getGson().fromJson(it.getString(ARG_USER), User::class.java)
            }
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.user = user
        initViews()
        observe()
        user?.id?.let { viewModel.getTodos(it) }
    }

    override fun initViews() {
        super.initViews()
        initTodoList()
    }

    override fun observe() {
        super.observe()
        viewModel.todoList.observe(viewLifecycleOwner, {
            Timber.d("it: $it")
            adapter.updateData(it ?: emptyList())
        })
    }

    private fun initTodoList() {
        adapter = TodoAdapter(emptyList())
        binding.recyclerviewTodos.adapter = adapter
    }

    companion object {
        const val ARG_USER = "user"
    }
}