package com.haroldcalayan.gorest.ui.main.product.category

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haroldcalayan.gorest.R
import com.haroldcalayan.gorest.data.model.Category
import com.haroldcalayan.gorest.databinding.FragmentProductCategoryBinding
import com.haroldcalayan.gorest.ui.main.MainViewModel
import com.haroldcalayan.gorest.ui.main.product.master.ProductMasterActivity
import com.haroldcalayan.gorest.ui.main.product.master.ProductMasterFragment
import com.haroldcalayan.gorest.util.JsonUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductCategoryFragment : Fragment(), ProductCategoryAdapter.ProductCategoryAdapterListener {

    private val viewModel: MainViewModel by activityViewModels()
    private val binding by lazy { FragmentProductCategoryBinding.inflate(layoutInflater) }

    private lateinit var adapter: ProductCategoryAdapter
    private var scrollView: NestedScrollView? = null
    private var recyclerViewCategoryList: RecyclerView? = null
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
        scrollView = view.findViewById(R.id.category_item_detail_container)
        recyclerViewCategoryList = view.findViewById(R.id.category_item_list)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
        observe()
        viewModel.getCategories()
    }

    private fun initViews() {
        if (scrollView != null) twoPane = true
        recyclerViewCategoryList?.let { setupCategoryList(it) }
    }

    private fun observe() {
        viewModel.categoryList.observe(viewLifecycleOwner, {
            adapter.updateData(it.orEmpty())
        })
    }

    override fun onCategoryItemClick(category: Category) {
        val serializedItem = JsonUtils.getGson().toJson(category)
        if (twoPane) {
            val fragment = ProductMasterFragment().apply {
                arguments = Bundle().apply {
                    putString(ProductMasterFragment.ARG_CATEGORY, serializedItem)
                }
            }
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.category_item_detail_container, fragment)
                ?.commit()
        } else {
            val intent = Intent(requireContext(), ProductMasterActivity::class.java).apply {
                putExtra(ProductMasterActivity.ARG_CATEGORY, serializedItem)
            }
            startActivity(intent)
        }
    }

    private fun setupCategoryList(recyclerView: RecyclerView) {
        adapter = ProductCategoryAdapter(emptyList(), this)
        recyclerView.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }
}