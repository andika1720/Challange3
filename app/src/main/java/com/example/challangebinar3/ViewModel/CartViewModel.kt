package com.example.challangebinar3.ViewModel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.challangebinar3.Database.Cart
import com.example.challangebinar3.Database.CartRepo

class CartViewModel(application: Application):ViewModel() {
    private val repository:CartRepo = CartRepo(application)

    val allCartItems: LiveData<List<Cart>> = repository.getAllCartItems()

    fun deleteItemCart(cartId: Long) {
        repository.deleteItemCart(cartId)
    }

    private fun updateQuantityItem(cart: Cart) {
        repository.quantityUpdate(cart)
    }

    @SuppressLint("SuspiciousIndentation")
    fun increment(cart: Cart) {
        val newTotal = cart.foodQuantity + 1
        cart.foodQuantity = newTotal
        cart.totalPrice = cart.priceMenu * newTotal

        updateQuantityItem(cart)
    }


    fun decrement(cart: Cart) {
        val newTotal = cart.foodQuantity - 1
        cart.foodQuantity = newTotal
        cart.totalPrice = cart.priceMenu * newTotal

        updateQuantityItem(cart)
    }

    fun updateNotes(notes: String, cart: Cart){
        cart.foodNote = notes
        updateQuantityItem(cart)
    }

}


    //fun deleteAllMenu
