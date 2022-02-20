package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WelcomeViewModel: ViewModel(){

    private val _eventNavigateNext = MutableLiveData<Boolean>()
    val eventNavigateNext : LiveData<Boolean>
        get() = _eventNavigateNext

    fun onNavigateNext(){
        _eventNavigateNext.value = true
    }
    fun onNavigateNextComplete(){
        _eventNavigateNext.value = false
    }
}