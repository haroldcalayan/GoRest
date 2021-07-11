package com.haroldcalayan.gorest.ui.main

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
class MainViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val userRepository: UserRepository
) : BaseViewModel() {

    private val _categoryList = MutableLiveData<List<Category>?>()
    val categoryList: LiveData<List<Category>?> = _categoryList

    private val _userList = MutableLiveData<List<User>?>()
    val userList: LiveData<List<User>?> = _userList

    fun getCategories() {
        invoke {
            val response = productRepository.getCategories()
            _categoryList.postValue(response?.data)
        }
    }

    fun getUsers() {
        invoke {
            val response = userRepository.getUsers()
            _userList.postValue(response?.data)
        }
    }

}