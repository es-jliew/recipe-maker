package com.essoft.recipemaker.di

import com.essoft.recipemaker.dao.IRecipeDao
import com.essoft.recipemaker.dao.RecipeDao
import com.essoft.recipemaker.db.ObjectBox
import com.essoft.recipemaker.model.DbRecipeModel
import io.objectbox.Box
import org.koin.dsl.module

val databaseModule = module {
    single { objectBoxBuilder() }
    single<IRecipeDao> { RecipeDao(get()) }
}

private fun objectBoxBuilder(): Box<DbRecipeModel> {
    return ObjectBox.boxStore.boxFor(DbRecipeModel::class.java)
}