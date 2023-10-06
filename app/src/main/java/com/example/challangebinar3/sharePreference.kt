package com.example.challangebinar3

import android.content.Context
import android.content.SharedPreferences

class sharePreference(context: Context) {

    companion object{
        private const val USER = "User SharePref"
        private const val KEY = "KEY SharePref"
    }

    private val pref: SharedPreferences =
        context.getSharedPreferences(USER, 0)

    fun getPrefLayout():Boolean{
        return pref.getBoolean(KEY, true)
    }

    fun savePrefLayout(isListView: Boolean){
        pref.edit().putBoolean(KEY, isListView).apply()
    }
}
