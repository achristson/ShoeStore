package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeDetailsViewModel() : ViewModel(){

    private val _eventCancel = MutableLiveData<Boolean>()
    val eventCancel : LiveData<Boolean>
        get() = _eventCancel

    private val _eventSave = MutableLiveData<Boolean>()
    val eventSave : LiveData<Boolean>
        get() = _eventSave

    fun onSave(){
        _eventSave.value = true
    }

    fun addShoe(
        shoeName: String,
        companyName: String,
        size: Double,
        description: String): Shoe {
        return Shoe(
            shoeName,
            size,
            companyName,
            description
        )
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