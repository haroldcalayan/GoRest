package com.haroldcalayan.gorest.ui.main.user.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.haroldcalayan.gorest.base.BaseViewModel
import com.haroldcalayan.gorest.data.model.User
import com.haroldcalayan.gorest.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {

    private val _createdUser = MutableLiveData<User?>()
    val createdUser: LiveData<User?> = _createdUser

    fun createUser(user: User) {
        invoke {
            val response = userRepository.createUser(user)
            _createdUser.postValue(response?.data)
        }
    }
}