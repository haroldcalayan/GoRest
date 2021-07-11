package com.haroldcalayan.gorest.ui.main.user.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.haroldcalayan.gorest.base.BaseViewModel
import com.haroldcalayan.gorest.data.model.Todo
import com.haroldcalayan.gorest.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserDetailFragmentViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {

    private val _todoList = MutableLiveData<List<Todo>?>()
    val todoList: LiveData<List<Todo>?> = _todoList

    fun getTodos(userId: Int) {
        invoke {
            val response = userRepository.getUserTodos(userId)
            _todoList.postValue(response?.data)
        }
    }
}