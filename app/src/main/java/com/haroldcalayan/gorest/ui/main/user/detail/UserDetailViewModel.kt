package com.haroldcalayan.gorest.ui.main.user.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.haroldcalayan.gorest.base.BaseViewModel
import com.haroldcalayan.gorest.data.model.User

class UserDetailViewModel : BaseViewModel() {

    private val _user = MutableLiveData<User>()
    val user : LiveData<User> = _user
}