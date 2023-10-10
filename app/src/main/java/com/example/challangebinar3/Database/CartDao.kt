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

    @Delete
    fun delete(cart: Cart)

    @Query("DELETE FROM cart_menu WHERE id = :itemId")
    fun deleteItemById(itemId: Long)

    @Update
    fun update(cart: Cart)
}