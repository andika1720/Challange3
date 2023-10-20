package com.example.challangebinar3.dataApi.model


import com.google.gson.annotations.SerializedName

data class ListMenu(
    @SerializedName("data")
    val `data`: List<DataListMenu>,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Boolean?
)