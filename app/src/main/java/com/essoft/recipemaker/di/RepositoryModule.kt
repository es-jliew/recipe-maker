package com.essoft.recipemaker.di

import com.essoft.recipemaker.repo.IRecipeRepository
import com.essoft.recipemaker.repo.RecipeRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<IRecipeRepository> { RecipeRepository(get(), get()) }
}