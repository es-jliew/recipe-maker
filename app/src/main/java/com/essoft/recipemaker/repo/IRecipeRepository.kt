package com.essoft.recipemaker.repo

import com.essoft.recipemaker.model.NonEntityRecipeModel
import com.essoft.recipemaker.model.RecipeModel
import kotlinx.coroutines.flow.Flow

interface IRecipeRepository {
    suspend fun retrieveAllRecipes(): Flow<List<RecipeModel>>

    suspend fun createRecipe(recipe: RecipeModel)

    suspend fun readRecipe(): Flow<RecipeModel>

    suspend fun updateRecipe(recipe: NonEntityRecipeModel)

    suspend fun deleteRecipe(id: Long): Boolean
}