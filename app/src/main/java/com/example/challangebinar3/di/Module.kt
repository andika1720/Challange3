package com.example.challangebinar3.di

import com.example.challangebinar3.Database.CartDatabase
import com.example.challangebinar3.repository.NewRepo

import com.example.challangebinar3.viewModel.HomeViewModel
import com.example.challangebinar3.viewModel.NewViewModel
import com.example.challangebinar3.dataApi.api.APIClient
import com.example.challangebinar3.repository.HomeRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Module {

    val moduleData
        get() = module {

            single { CartDatabase.getInstance(context = get()) }

            single { APIClient.instance }

            factory { HomeRepository(get()) }
            factory { NewRepo(get<CartDatabase>().cartDao) }


        }

    val uiModule
        get() = module {
            viewModel { HomeViewModel(get())}
            viewModel { NewViewModel(get())}
        }
}