package com.example.challangebinar3.di

import com.example.challangebinar3.Database.CartDatabase
import com.example.challangebinar3.Database.CartRepo
import com.example.challangebinar3.repository.NewRepo
import com.example.challangebinar3.repository.HomeRepository
import com.example.challangebinar3.viewModel.HomeViewModel
import com.example.challangebinar3.viewModel.NewViewModel
import com.example.challangebinar3.dataApi.Api.APIClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Module {

    val moduleData
        get() = module {

            single { CartDatabase.getInstance(context = get()) }

            single { APIClient.instance }

            factory { CartRepo(get()) }
            factory { HomeRepository(get()) }
            factory { NewRepo(get<CartDatabase>().cartDao) }


        }


    val uiModule
        get() = module {
            viewModel { HomeViewModel(get())}
            viewModel { NewViewModel(get())}
        }

//    val daoModule
//        get() = module {
//            single { CartDatabase.getInstance(get()) }
//            factory { CartRepo(get()) }
//            factory { get<CartDatabase>().cartDao }
//        }
}