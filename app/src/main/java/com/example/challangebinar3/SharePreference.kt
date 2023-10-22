package com.example.challangebinar3

import android.content.Context
import android.content.SharedPreferences

object SharePreference {

    private lateinit var prefe: SharedPreferences
    private const val PREF_NAME = "layout"

    fun initial(context: Context) {
        prefe = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun getPref(key: String, value: Boolean): Boolean{
        return prefe.getBoolean(key, value)
    }

    fun setPref(key: String, value: Boolean) {
        val editor: SharedPreferences.Editor = prefe.edit()
        with(editor){
            putBoolean(key, value)
            apply()
            commit()
        }
    }
}