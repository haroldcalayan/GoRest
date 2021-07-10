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

class UserDetailFragment : BaseFragment<UserDetailFragmentViewModel, FragmentUserDetailBinding>() {

    override val layoutId = R.layout.fragment_user_detail
    override val viewModel: UserDetailFragmentViewModel by viewModels()

    private var user: User? = null

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
    }

    companion object {
        const val ARG_USER = "user"
    }
}