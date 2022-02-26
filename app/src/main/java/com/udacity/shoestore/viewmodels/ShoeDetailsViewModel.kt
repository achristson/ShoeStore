package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeDetailsViewModel() : ViewModel(){

    private var _eventCancel = MutableLiveData<Boolean>()
    val eventCancel : LiveData<Boolean>
        get() = _eventCancel

    private var _eventSave = MutableLiveData<Boolean>()
    val eventSave : LiveData<Boolean>
        get() = _eventSave

    fun onSave(){
        _eventSave.value = true
    }

    fun onSaveComplete(){
        _eventSave.value = false
    }

    fun onCancel(){
        _eventCancel.value = true
    }

    fun onCancelComplete(){
        _eventCancel.value = false
    }
}