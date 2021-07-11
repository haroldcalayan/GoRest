package com.haroldcalayan.gorest.ui.main.product.category

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.haroldcalayan.gorest.R
import com.haroldcalayan.gorest.base.BaseActivity
import com.haroldcalayan.gorest.data.model.Category
import com.haroldcalayan.gorest.databinding.ActivityProductCategoryBinding
import com.haroldcalayan.gorest.ui.main.product.master.ProductMasterActivity
import com.haroldcalayan.gorest.ui.main.product.master.ProductMasterFragment
import com.haroldcalayan.gorest.util.JsonUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductCategoryActivity : BaseActivity<ProductCategoryViewModel, ActivityProductCategoryBinding>(), ProductCategoryAdapter.ProductCategoryAdapterListener {

    override val layoutId = R.layout.activity_product_category
    override val viewModel: ProductCategoryViewModel by viewModels()

    private lateinit var adapter: ProductCategoryAdapter
    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        observe()
        viewModel.getCategories()
    }

    override fun initViews() {
        super.initViews()

        if (findViewById<NestedScrollView>(R.id.category_item_detail_container) != null) twoPane = true
        setupCategoryList(findViewById(R.id.category_item_list))
    }

    override fun observe() {
        super.observe()
        viewModel.categoryList.observe(this, {
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
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.category_item_detail_container, fragment)
                .commit()
        } else {
            val intent = Intent(this, ProductMasterActivity::class.java).apply {
                putExtra(ProductMasterActivity.ARG_CATEGORY, serializedItem)
            }
            startActivity(intent)
        }
    }

    private fun setupCategoryList(recyclerView: RecyclerView) {
        adapter = ProductCategoryAdapter(emptyList(), this)
        recyclerView.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }
}