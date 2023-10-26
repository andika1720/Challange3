package com.example.challangebinar3.dataApi.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataListMenu(
    @SerializedName("alamatResto")
    val alamatResto: String?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("detail")
    val detail: String?,
    @SerializedName("harga")
    val harga: Int,
    @SerializedName("hargaFormat")
    val hargaFormat: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("imageUrl")
    val imageUrl: String?,
    @SerializedName("nama")
    val nama: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?
):Parcelable