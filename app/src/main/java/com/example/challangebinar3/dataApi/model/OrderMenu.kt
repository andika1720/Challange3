package com.example.challangebinar3.dataApi.model


import com.google.gson.annotations.SerializedName

data class OrderMenu(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Boolean?
)