package com.haroldcalayan.gorest.ui.main.user.master

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.haroldcalayan.gorest.base.BaseViewModel
import com.haroldcalayan.gorest.data.model.User
import com.haroldcalayan.gorest.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserMasterViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {

    private val _userList = MutableLiveData<List<User>?>()
    val userList: LiveData<List<User>?> = _userList

    fun getUsers() {
        invoke {
            val response = userRepository.getUsers()
            _userList.postValue(response?.data)
        }
    }
}