package com.essoft.recipemaker.di

import com.essoft.recipemaker.utils.IStorageHandler
import com.essoft.recipemaker.utils.StorageHandler

import org.koin.dsl.module

val utilsModule = module {
    single<IStorageHandler> { StorageHandler(get()) }
}