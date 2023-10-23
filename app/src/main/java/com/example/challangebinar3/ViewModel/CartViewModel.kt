package com.example.challangebinar3.ViewModel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challangebinar3.Database.Cart
import com.example.challangebinar3.Database.CartRepo
import com.example.challangebinar3.dataApi.Api.APIClient
import com.example.challangebinar3.dataApi.model.DataOrders
import com.example.challangebinar3.dataApi.model.OrderMenu
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartViewModel(application: Application):ViewModel() {
    private val repository:CartRepo = CartRepo(application)

    val allCartItems: LiveData<List<Cart>> = repository.getAllCartItems()

    private val mOrderSuccess = MutableLiveData<Boolean>()

    val orderSucces: LiveData<Boolean> = mOrderSuccess

    fun deleteItemCart(cartId: Int) {
        repository.deleteItemCart(cartId)
    }

    fun deleteItems(){
        repository.deleteItems()
    }
    private fun updateQuantityItem(cart: Cart) {
        repository.quantityUpdate(cart)
    }

    @SuppressLint("SuspiciousIndentation")
    fun increment(cart: Cart) {
        val newTotal = cart.foodQuantity + 1
        cart.foodQuantity = newTotal
        cart.totalPrice = cart.priceMenu!! * newTotal

        updateQuantityItem(cart)
    }


    fun decrement(cart: Cart) {
        if (cart.foodQuantity > 1) {


            val newTotal = cart.foodQuantity - 1
            cart.foodQuantity = newTotal
            cart.totalPrice = cart.priceMenu!! * newTotal

            updateQuantityItem(cart)
        }
    }

    fun postData(dataOrder: DataOrders){
        APIClient.instance.postOrder(dataOrder)
            .enqueue(object : Callback<OrderMenu> {
                override fun onResponse(call: Call<OrderMenu>, response: Response<OrderMenu>) {

                    if (response.isSuccessful){
                        mOrderSuccess.postValue(true)
                        deleteItems()
                    } else {
                        mOrderSuccess.postValue(false)
                    }
                }

                override fun onFailure(call: Call<OrderMenu>, t: Throwable) {
                    Log.e("Error", "message= $t")
                }

            })
    }


}


    //fun deleteAllMenu
