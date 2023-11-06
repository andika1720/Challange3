package com.example.challangebinar3.viewModel

import androidx.lifecycle.ViewModel
import com.example.challangebinar3.Database.Cart
import com.example.challangebinar3.repository.NewRepo
import com.example.challangebinar3.dataApi.model.DataListMenu
import com.example.challangebinar3.dataApi.model.DataOrders

class NewViewModel(private val repo: NewRepo) : ViewModel() {

    val allCartItems = repo.items

    val counter = repo.counter

    val success = repo.orderSucces

    val totalPricee = repo.totalPrice

    fun increment() = repo.increment()
    fun decrement() = repo.decrement()
    fun initSelectedItem(data: DataListMenu) = repo.initSelectedItem(data)
    fun incrementCart(cart : Cart) = repo.incrementCart(cart)
    fun decrementCart(cart: Cart) = repo.decrementCart(cart)
    fun allCartItems() = repo.getAllCartItems()
    fun addToCart(note: String) = repo.addToCart(note)
    fun deleteItemCart(id : Int) = repo.deleteItemCartById(id)
    fun postData(orders: DataOrders) = repo.postData(orders)
    fun deleteItems() = repo.deleteItems()

}