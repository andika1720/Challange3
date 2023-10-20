package com.example.challangebinar3.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.challangebinar3.ParcelMakanan
import com.example.challangebinar3.dataApi.Api.APIClient
import com.example.challangebinar3.dataApi.model.CategoryMenu
import com.example.challangebinar3.dataApi.model.ListMenu
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel:ViewModel() {
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val status: MutableLiveData<String> = MutableLiveData()


    private val mCategoriesLiveData: MutableLiveData<CategoryMenu> by lazy {
        MutableLiveData<CategoryMenu>().also {
            getCategories()
        }
    }
    val dataCategory: LiveData<CategoryMenu> = mCategoriesLiveData

    private val mListMenuLiveData: MutableLiveData<ListMenu> by lazy {
        MutableLiveData<ListMenu>().also {
            getListMenu()
        }
    }
    val dataList: LiveData<ListMenu> = mListMenuLiveData

    fun getCategories() {
        loading.postValue(true)
        APIClient.instance.getCategory().enqueue(object : Callback<CategoryMenu> {
            override fun onResponse(
                call: Call<CategoryMenu>,
                response: Response<CategoryMenu>
            ) {
                loading.postValue(false)
                when {
                    response.code() == 200 -> mCategoriesLiveData.postValue(response.body())
                    else -> status.postValue("Error")
                }
            }

            override fun onFailure(call: Call<CategoryMenu>, t: Throwable) {
                Log.d("categoryError", t.message.toString())
            }

        })
    }

    fun getListMenu() {
        loading.postValue(true)
        APIClient.instance.getListMenu().enqueue(object : Callback<ListMenu> {
            override fun onResponse(
                call: Call<ListMenu>,
                response: Response<ListMenu>
            ) {
                loading.postValue(false)
                when {
                    response.code() == 200 -> mListMenuLiveData.postValue(response.body())
                    else -> status.postValue("Error")
                }
            }

            override fun onFailure(call: Call<ListMenu>, t: Throwable) {
                Log.d("categoryError", t.message.toString())
            }

        })
    }
}