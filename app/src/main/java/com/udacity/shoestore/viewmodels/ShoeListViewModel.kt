package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeListViewModel : ViewModel(){
    private val _shoeList = MutableLiveData<MutableList<Shoe?>>()
    val shoeList : LiveData<MutableList<Shoe?>>
        get() = _shoeList

    private val _eventAddShoe = MutableLiveData<Boolean>()
    val eventAddShoe : LiveData<Boolean>
        get() = _eventAddShoe

    init {
        _shoeList.value = mutableListOf()
        _shoeList.value!!.add(Shoe("Jordan Why Not.5",10.0,"Nike, Inc.","Basketball Shoes"))
//        if (_shoeList.value == null){
//            //Timber.i("true")
//            _shoeList.value = mutableListOf()
//        }
//        _shoeList.value = _shoeList.value
//        //Timber.i("init")
    }

    fun addShoe(
        shoe: Shoe?){
        _shoeList.value?.add(shoe)
        Timber.i("${_shoeList.value}")
    }

    fun onAddShoeClick(){
        _eventAddShoe.value = true
    }

    fun onAddShoeClickComplete(){
        _eventAddShoe.value = false
    }
}