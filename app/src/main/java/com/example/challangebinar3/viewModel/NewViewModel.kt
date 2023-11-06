package com.example.challangebinar3.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.challangebinar3.Database.Cart
import com.example.challangebinar3.Repository.NewRepo
import com.example.challangebinar3.dataApi.model.DataListMenu
import com.example.challangebinar3.dataApi.model.DataOrders
import com.example.challangebinar3.util.Resource
import kotlinx.coroutines.Dispatchers

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
    fun addToCartt(note: String) = repo.addToCart(note)
    fun deleteItemCart(id : Int) = repo.deleteItemCartById(id)
    fun postData(orders: DataOrders) = repo.postData(orders)
    fun deleteItems() = repo.deleteItems()

    fun getAllCategory() = liveData(Dispatchers.IO){
        try {
            emit(Resource.success(repo.getCategory()))
        } catch (exception: Exception) {
            emit(Resource.error(null, exception.message ?: "Error Occurred!"))
        }
    }

    fun getAllList() = liveData(Dispatchers.IO) {
        try {
            emit(Resource.success( repo.getList()))
        } catch (exception: Exception) {
            emit(Resource.error(null,exception.message ?: "Error Occurred!"))
        }
    }
}