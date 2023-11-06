package com.example.challangebinar3.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.challangebinar3.repository.HomeRepository
import com.example.challangebinar3.util.Resource
import kotlinx.coroutines.Dispatchers

class HomeViewModel(private val repository: HomeRepository): ViewModel() {

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

