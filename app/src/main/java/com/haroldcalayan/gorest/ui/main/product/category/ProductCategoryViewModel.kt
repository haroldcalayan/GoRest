package com.haroldcalayan.gorest.ui.main.product.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.haroldcalayan.gorest.base.BaseViewModel
import com.haroldcalayan.gorest.data.model.Category
import com.haroldcalayan.gorest.data.model.User
import com.haroldcalayan.gorest.data.repository.ProductRepository
import com.haroldcalayan.gorest.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductCategoryViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : BaseViewModel() {

    private val _categoryList = MutableLiveData<List<Category>?>()
    val categoryList: LiveData<List<Category>?> = _categoryList

    fun getCategories() {
        invoke {
            val response = productRepository.getCategories()
            _categoryList.postValue(response?.data)
        }
    }
}