package com.example.challangebinar3.dataApi.Api

import com.example.challangebinar3.dataApi.model.CategoryMenu
import com.example.challangebinar3.dataApi.model.ListMenu
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("category")
    fun getCategory(): Call<CategoryMenu>

    @GET("listmenu")
    fun getListMenu(
        @Query("c") category: String
    ) : Call<ListMenu>
}