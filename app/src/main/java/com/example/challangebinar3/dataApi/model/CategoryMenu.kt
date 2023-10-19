package com.example.challangebinar3.dataApi.model


import com.google.gson.annotations.SerializedName

data class CategoryMenu(
    @SerializedName("data")
    val `data`: List<DataCategoryMenu?>?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Boolean?
)