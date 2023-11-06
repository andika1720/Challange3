package com.example.challangebinar3.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.challangebinar3.Repositoryy
import com.example.challangebinar3.util.Resource
import kotlinx.coroutines.Dispatchers

class HomeViewModel(private val repository: Repositoryy): ViewModel() {

    fun getAllCategory() = liveData(Dispatchers.IO){
        try {
            emit(Resource.success(repository.getCategory()))
        } catch (exception: Exception) {
            emit(Resource.error(null, exception.message ?: "Error Occurred!"))
        }
    }

    fun getAllList() = liveData(Dispatchers.IO) {
        try {
            emit(Resource.success( repository.getList()))
        } catch (exception: Exception) {
            emit(Resource.error(null,exception.message ?: "Error Occurred!"))
        }
    }

}

