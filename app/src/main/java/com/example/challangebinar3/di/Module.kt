package com.example.challangebinar3.di

import com.example.challangebinar3.Database.CartDatabase
import com.example.challangebinar3.Database.CartRepo
import com.example.challangebinar3.ViewModel.CartViewModel
import com.example.challangebinar3.ViewModel.DetailFragmentMenuViewModel
import com.example.challangebinar3.dataApi.Api.APIClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Module {

    val moduleData
        get() = module {
            //Database
            single { CartDatabase.getInstance(context = get()) }
            //API
            single { APIClient.instance }

        }

    val moduleViewModel
        get() = module {
            viewModel { DetailFragmentMenuViewModel(get()) }
            viewModel { CartViewModel(get()) }
        }

    val daoModule
        get() = module {
            single { CartDatabase.getInstance(get()) }
            factory { CartRepo(get()) }
            factory { get<CartDatabase>().cartDao }
        }
}