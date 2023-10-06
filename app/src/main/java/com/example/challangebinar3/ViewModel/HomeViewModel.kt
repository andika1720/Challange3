package com.example.challangebinar3.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challangebinar3.ParcelMakanan

class HomeViewModel:ViewModel() {
    val menuView = MutableLiveData<Boolean>().apply { value= true }
    val menuItem = MutableLiveData<ArrayList<ParcelMakanan>>()
}