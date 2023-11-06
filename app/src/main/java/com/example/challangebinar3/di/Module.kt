package com.example.challangebinar3.di

import com.example.challangebinar3.Database.CartDatabase
import com.example.challangebinar3.Database.CartRepo
import com.example.challangebinar3.Repository.NewRepo
import com.example.challangebinar3.Repositoryy
import com.example.challangebinar3.ViewModel.CartViewModel
import com.example.challangebinar3.ViewModel.DetailFragmentMenuViewModel
import com.example.challangebinar3.ViewModel.HomeViewModel
import com.example.challangebinar3.ViewModel.NewViewModel
import com.example.challangebinar3.dataApi.Api.APIClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Module {

    val moduleData
        get() = module {

            single { CartDatabase.getInstance(context = get()) }

            single { APIClient.instance }

            factory { CartRepo(get()) }
            factory { Repositoryy(get()) }
            factory { NewRepo(get<CartDatabase>().cartDao) }


        }

//    val uiModule
//        get() = module {
//            viewModel { NewViewModel(get()) }
//        }

    val uiModule
        get() = module {
            viewModel { HomeViewModel(get())}
            viewModel { DetailFragmentMenuViewModel(get()) }
            viewModel { CartViewModel(get()) }
            viewModel { NewViewModel(get())}
        }

//    val daoModule
//        get() = module {
//            single { CartDatabase.getInstance(get()) }
//            factory { CartRepo(get()) }
//            factory { get<CartDatabase>().cartDao }
//        }
}