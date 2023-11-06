package com.example.challangebinar3

import com.example.challangebinar3.Database.CartDao
import com.example.challangebinar3.dataApi.Api.APIClient
import com.example.challangebinar3.dataApi.Api.APIService
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Repositoryy (private val apiService: APIService) {

    suspend fun getList() = apiService.getListMenu()
    suspend fun getCategory() = apiService.getCategory()









}