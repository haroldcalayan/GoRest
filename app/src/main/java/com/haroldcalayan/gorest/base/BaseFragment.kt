package com.haroldcalayan.gorest.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : BaseViewModel, B: ViewDataBinding> : Fragment() {

    abstract val viewModel: T
    abstract val layoutId: Int

    lateinit var binding: B
    lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = this
        rootView = binding.root
        return rootView
    }

    open fun initViews() {
    }

    open fun observe() {
    }
}