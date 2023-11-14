package com.example.challangebinar3.repository

import com.example.challangebinar3.dataApi.api.APIService

class HomeRepository(private val apiService: APIService) {
    suspend fun getList() = apiService.getListMenu()
    suspend fun getCategory() = apiService.getCategory()
}