package com.example.challangebinar3

import android.content.Context
import android.content.SharedPreferences

class UserSharedPreference(context: Context) {
    private val sharedPreference: SharedPreferences = context.getSharedPreferences("UserPreferen", Context.MODE_PRIVATE)

    fun saveLogin(loggin: Boolean){
        val sharePref: SharedPreferences.Editor = sharedPreference.edit()
        sharePref.putBoolean("Telah Login", loggin)
        sharePref.apply()
    }
}