package com.haroldcalayan.gorest.ui.main.product.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.haroldcalayan.gorest.BR
import com.haroldcalayan.gorest.R
import com.haroldcalayan.gorest.data.model.Category
import com.haroldcalayan.gorest.databinding.AdapterProductCategoryBinding
import com.haroldcalayan.gorest.databinding.AdapterUserBinding

class ProductCategoryAdapter(
    private var data: List<Category>,
    private val listener: ProductCategoryAdapterListener
) : RecyclerView.Adapter<ProductCategoryAdapter.ProductCategoryAdapterViewHolder>() {

    interface ProductCategoryAdapterListener {
        fun onCategoryItemClick(category: Category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductCategoryAdapterViewHolder {
        val binding: AdapterProductCategoryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.adapter_product_category,
            parent,
            false
        )
        return ProductCategoryAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductCategoryAdapterViewHolder, position: Int) {
        holder.bind(data[position])
        holder?.itemView?.tag = data[position]
        holder?.itemView?.setOnClickListener { listener.onCategoryItemClick(data[position]) }
    }

    override fun getItemCount() = data.size

    fun updateData(data: List<Category>) {
        this.data = data
        notifyDataSetChanged()
    }

    class ProductCategoryAdapterViewHolder(private val binding: AdapterProductCategoryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
            binding.setVariable(BR.category, category)
            binding.executePendingBindings()
        }
    }
}