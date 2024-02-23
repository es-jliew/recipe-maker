package com.essoft.recipemaker.di

import com.essoft.recipemaker.utils.StorageHandler

import org.koin.dsl.module

val utilsModule = module {
    single { StorageHandler(get()) }
}