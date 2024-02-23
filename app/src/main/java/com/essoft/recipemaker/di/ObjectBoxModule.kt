package com.essoft.recipemaker.di

import com.essoft.recipemaker.db.ObjectBox
import com.essoft.recipemaker.model.RecipeModel
import io.objectbox.Box
import org.koin.dsl.module

val objectBoxModule = module {
    single { objectBoxBuilder() }
}

private fun objectBoxBuilder(): Box<RecipeModel>? {
    return ObjectBox.boxStore.boxFor(RecipeModel::class.java)
}