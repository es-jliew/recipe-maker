package com.essoft.recipemaker.dao

import com.essoft.recipemaker.model.DbRecipeModel
import com.essoft.recipemaker.model.RecipeModel
import kotlinx.coroutines.flow.Flow

interface IRecipeDao {
    suspend fun getAllRecipes(): Flow<List<DbRecipeModel>>

    suspend fun createRecipe(dbRecipe: DbRecipeModel)

    suspend fun readRecipe(id: Long): DbRecipeModel

    suspend fun updateRecipe(recipe: RecipeModel)

    suspend fun deleteRecipe(id: Long): Boolean
}