package com.example.challangebinar3.ViewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challangebinar3.Database.Cart
import com.example.challangebinar3.Database.CartDao
import com.example.challangebinar3.Database.CartDatabase
import com.example.challangebinar3.Database.CartRepo
import com.example.challangebinar3.ParcelMakanan
import com.example.challangebinar3.dataApi.model.DataListMenu
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class DetailFragmentMenuViewModel(application: Application): ViewModel() {

    private val _counter = MutableLiveData(1)
    val counter: LiveData<Int> = _counter

    private val _totalPrice = MutableLiveData<Int?>()
    val totalPrice: MutableLiveData<Int?> = _totalPrice

    private val _selectedItem = MutableLiveData<DataListMenu>()

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    private val cardDao: CartDao

    private val cartRepo: CartRepo = CartRepo(application)

    init {
        val database = CartDatabase.getInstance(application)
        cardDao = database.cartDao
    }

    private fun insert(cart: Cart) {
            cartRepo.insert(cart)

    }

    fun initSelectedItem(item: DataListMenu) {
        _selectedItem.value = item
        _totalPrice.value = item.harga

    }

    private fun total() {
        val currentAmount = _counter.value ?: 1
        val selectedItem = _selectedItem.value
        if (selectedItem != null) {
            val totalPrice = selectedItem.harga?.times(currentAmount)
            _totalPrice.value = totalPrice
        }

    }

    fun increment() {
        _counter.value = (_counter.value ?: 1) + 1
        total()

    }

    fun decrement() {
        val currentValues: Int = _counter.value ?: 1
        if (currentValues > 1) {
            _counter.value = currentValues - 1
        }
        total()
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

            cartItem?.let { it1 -> insert(it1) }

        }

    }
}

