package com.example.challangebinar3.util

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ParcelMakanan(
    val image: Int,
    val name: String,
    val harga: Int,
    val desc : String
):Parcelable
