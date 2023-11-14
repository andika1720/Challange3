package com.example.challangebinar3.dataApi.api

import com.example.challangebinar3.dataApi.model.CategoryMenu
import com.example.challangebinar3.dataApi.model.ListMenu
import com.example.challangebinar3.dataApi.model.DataOrders
import com.example.challangebinar3.dataApi.model.OrderMenu
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIService {

    @GET("category-menu")
    suspend fun getCategory(): CategoryMenu

    @GET("listmenu")
    suspend fun getListMenu(): ListMenu

    @POST("order-menu")
    fun postOrder(@Body orderData: DataOrders): Call<OrderMenu>

}