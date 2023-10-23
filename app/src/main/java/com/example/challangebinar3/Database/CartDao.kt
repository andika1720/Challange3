package com.example.challangebinar3.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CartDao {

    @Insert
    fun insert(cart: Cart)

    @Query("SELECT * FROM cart_menu ORDER BY id DESC ")
    fun getAllItem(): LiveData<List<Cart>>

    @Query("DELETE FROM cart_menu")
    fun deleteItems()

    @Delete
    fun delete(cart: Cart)

    @Query("DELETE FROM cart_menu WHERE id = :itemId")
    fun deleteItemById(itemId: Int):Int

    @Update
    fun update(cart: Cart)
    @Query("SELECT * FROM cart_menu WHERE food_name = :foodName")
        fun getItem(foodName: String): Cart?


//        fun updateCartMenu(cart: Cart){
//            val existingItem = getItem(cart.foodName)
//            if (existingItem != null) {
//                val newQuantity = existingItem.foodQuantity + cart.foodQuantity
//                existingItem.foodQuantity =newQuantity
//                update(existingItem)
//            } else {
//                insert(cart)
//            }
//        }
}