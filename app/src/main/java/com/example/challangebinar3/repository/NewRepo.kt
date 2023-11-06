package com.example.challangebinar3.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.challangebinar3.Database.Cart
import com.example.challangebinar3.Database.CartDao
import com.example.challangebinar3.dataApi.Api.APIClient
import com.example.challangebinar3.dataApi.Api.APIService
import com.example.challangebinar3.dataApi.model.DataListMenu
import com.example.challangebinar3.dataApi.model.DataOrders
import com.example.challangebinar3.dataApi.model.OrderMenu
import com.example.challangebinar3.util.Callback
import retrofit2.Call
import retrofit2.Response
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NewRepo (private val cartDao: CartDao )  {

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    private val apiService: APIService = APIClient.instance


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

    fun getItems(foodname: String, callback: Callback){
        executorService.execute{
            val cart = cartDao.getItem(foodname)
            callback.cartLoad(cart)
        }
    }
    private fun update(cart: Cart) {
        cartDao.update(cart)
    }

    fun deleteItemCartById(cartId : Int) {
        executorService.execute {cartDao.deleteItemById(cartId) }
    }

    private fun insertCartData(cart: Cart) {
        executorService.execute { cartDao.insert(cart) }
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
            getItems(cartItem!!.foodName, object: Callback {
                override fun cartLoad(cart: Cart?): Cart? {
                    if (cart != null) {
                        val total = counter.value!!.toInt() + cart.foodQuantity
                        cart.foodQuantity =total
                        cart.totalPrice = cart.priceMenu.times(total)
                        update(cart)
                    } else {
                        insertCartData(cartItem)
                    }
                    return cart
                }

            })
            //cartItem?.let { it1 -> insert(it1) }
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

    suspend fun getList() = apiService.getListMenu()
    suspend fun getCategory() = apiService.getCategory()
}