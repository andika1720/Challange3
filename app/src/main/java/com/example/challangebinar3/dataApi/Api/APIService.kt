package com.example.challangebinar3.dataApi.Api

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
    fun getCategory(): Call<CategoryMenu>

    @GET("listmenu")
    fun getListMenu(): Call<ListMenu>

    @POST("order-menu")
    fun postOrder(@Body orderData: DataOrders): Call<OrderMenu>

}