package com.example.challangebinar3

import com.example.challangebinar3.Database.CartDao
import com.example.challangebinar3.dataApi.Api.APIClient
import com.example.challangebinar3.dataApi.Api.APIService
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Repositoryy (private val cartDao: CartDao) {

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    private val apiService: APIService = APIClient.instance










}