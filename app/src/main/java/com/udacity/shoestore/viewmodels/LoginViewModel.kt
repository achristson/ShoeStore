package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class LoginViewModel(): ViewModel(){

    private val _eventLogin = MutableLiveData<Boolean>()
    val eventLogin: LiveData<Boolean>
        get() = _eventLogin

    private val _eventSignUp = MutableLiveData<Boolean>()
    val eventSignUp: LiveData<Boolean>
        get() = _eventSignUp

    fun onLogIn(){
        _eventLogin.value = true
    }

    fun onLogInComplete(){
        _eventLogin.value = false
    }

    fun onSignUp(){
        _eventSignUp.value = true
    }

    fun onSignUpComplete(){
        _eventSignUp.value = false
    }
}