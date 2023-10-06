package com.example.challangebinar3.ViewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.challangebinar3.Database.Cart
import com.example.challangebinar3.Database.CartRepo

class CartViewModel(application: Application):ViewModel() {
    val repository:CartRepo = CartRepo(application)

    val allCartItems: LiveData<List<Cart>> = repository.getAllCartItems()

    //fun deleteAllMenu
}