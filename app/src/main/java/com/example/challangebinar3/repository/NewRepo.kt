package com.example.challangebinar3.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.challangebinar3.Database.Cart
import com.example.challangebinar3.Database.CartDao
import com.example.challangebinar3.dataApi.Api.APIClient
import com.example.challangebinar3.dataApi.model.DataListMenu
import com.example.challangebinar3.dataApi.model.DataOrders
import com.example.challangebinar3.dataApi.model.OrderMenu
import retrofit2.Call
import retrofit2.Response
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NewRepo (private val cartDao: CartDao )  {

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()


    private val _counter = MutableLiveData(1)
    val counter: LiveData<Int> = _counter

    private val _totalPrice = MutableLiveData<Int?>()
    val totalPrice: MutableLiveData<Int?> = _totalPrice
    private val _selectedItem = MutableLiveData<DataListMenu>()
    private val mOrderSuccess = MutableLiveData<Boolean>()
    val orderSucces: LiveData<Boolean> = mOrderSuccess
    val items: LiveData<List<Cart>> = getAllCartItems()

    fun initSelectedItem(item: DataListMenu) {
        _selectedItem.value = item
        _totalPrice.value = item.harga

    }

    fun increment() {
        _counter.value = (_counter.value ?: 1) + 1
        total()

    }

    fun deleteItems(){
        executorService.execute {cartDao.deleteItems()}
    }
    fun decrement() {
        val currentValues: Int = _counter.value ?: 1
        if (currentValues > 1) {
            _counter.value = currentValues - 1
        }
        total()
    }


    private fun total() {
        val currentAmount = _counter.value ?: 1
        val selectedItem = _selectedItem.value
        if (selectedItem != null) {
            val totalPrice = selectedItem.harga.times(currentAmount)
            _totalPrice.value = totalPrice
        }

    }

    fun incrementCart(cart: Cart) {
        val newTotal = cart.foodQuantity + 1
        cart.foodQuantity = newTotal
        cart.totalPrice = cart.priceMenu * newTotal

        updateQuantityItem(cart)
    }
    fun decrementCart(cart: Cart) {
        if (cart.foodQuantity > 1) {


            val newTotal = cart.foodQuantity - 1
            cart.foodQuantity = newTotal
            cart.totalPrice = cart.priceMenu * newTotal

            updateQuantityItem(cart)
        }
    }


    private fun updateQuantityItem(cart: Cart) {
        executorService.execute {cartDao.update(cart)}
    }
    fun getAllCartItems(): LiveData<List<Cart>> = cartDao.getAllItem()

    private fun update(cart: Cart) {
        cartDao.update(cart)
    }

    fun deleteItemCartById(cartId : Int) {
        executorService.execute {cartDao.deleteItemById(cartId) }
    }


    private fun addCartToUpdate(cart: Cart) {
        executorService.execute {
            updateCartMenu1(cart)
        }
    }

    private fun updateCartMenu1(cartt: Cart){
        val existingItem = cartDao.getItem(cartt.foodName)
        if (existingItem != null) {
            val newQuantity = existingItem.foodQuantity + cartt.foodQuantity
            val totalPrice = newQuantity * existingItem.priceMenu
            existingItem.foodQuantity = newQuantity
            existingItem.totalPrice = totalPrice
            update(existingItem)
        } else {
            cartDao.insert(cartt)
        }
    }
    fun addToCart(notes: String) {
        val selectedItem = _selectedItem.value

        selectedItem?.let {
            val cartItem =
                totalPrice.value?.let { it1 ->
                    counter.value?.let { it2 ->
                        Cart(
                            foodName = it.nama.toString(),
                            imgId = it.imageUrl.toString(),
                            priceMenu = it.harga,
                            foodQuantity = it2,
                            totalPrice = it1,
                            foodNote = notes
                        )
                    }

                }
                addCartToUpdate(cartItem!!)
        }

    }
    private fun deleteAllItems() {
        executorService.execute { cartDao.deleteItems() }
    }

    fun postData(dataOrder: DataOrders){
        APIClient.instance.postOrder(dataOrder)
            .enqueue(object : retrofit2.Callback<OrderMenu> {
                override fun onResponse(call: Call<OrderMenu>, response: Response<OrderMenu>) {

                    if (response.isSuccessful){
                        mOrderSuccess.postValue(true)
                        deleteAllItems()
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