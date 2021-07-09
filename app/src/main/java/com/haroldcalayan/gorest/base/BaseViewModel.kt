package com.haroldcalayan.gorest.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    val showProgressBar = MutableLiveData<Boolean>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable: Throwable ->
        throwable.printStackTrace()
        showProgressBar.postValue(false)
    }

    fun invoke(apiCall: suspend () -> Unit) {
        showProgressBar.postValue(true)
        viewModelScope.launch(exceptionHandler) {
            apiCall.invoke()
            showProgressBar.postValue(false)
        }
    }
}