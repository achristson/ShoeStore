package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class LoginViewModel(): ViewModel(){

    private val _eventLogin = MutableLiveData<Boolean>()
    val eventLogin: LiveData<Boolean>
        get() = _eventLogin

    fun onLogIn(){
        Timber.i("login called")
        _eventLogin.value = true
    }

    fun onLogInComplete(){
        Timber.i("login complete called")
        _eventLogin.value = false
    }
}