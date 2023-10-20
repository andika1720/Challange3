package com.example.challangebinar3.dataApi.model


data class DataOrders( val username: String, val total: Int, val orders: List<ItemOrder>)

data class ItemOrder(val nama: String, val qty: Int, val catatan: String?, val harga: Int)