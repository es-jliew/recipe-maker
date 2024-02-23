package com.essoft.recipemaker.di

import com.essoft.recipemaker.utils.StorageHandler
import com.essoft.recipemaker.viewmodel.add.AddViewModel
import com.essoft.recipemaker.viewmodel.detail.DetailViewModel
import com.essoft.recipemaker.viewmodel.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { AddViewModel(get()) }
    single { StorageHandler(androidContext().applicationContext)}
}