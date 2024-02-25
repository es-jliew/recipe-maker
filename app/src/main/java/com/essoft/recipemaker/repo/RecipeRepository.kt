package com.essoft.recipemaker.repo

import androidx.core.net.toUri
import com.essoft.recipemaker.dao.IRecipeDao
import com.essoft.recipemaker.model.DbRecipeModel
import com.essoft.recipemaker.model.RecipeModel
import com.essoft.recipemaker.utils.IStorageHandler
import kotlinx.coroutines.flow.Flow

class RecipeRepository(private val recipeDao: IRecipeDao, private val storageHandler: IStorageHandler): IRecipeRepository {
    override suspend fun getAllRecipes(): Flow<List<DbRecipeModel>> {
        return recipeDao.getAllRecipes()
    }

    override suspend fun createRecipe(dbRecipe: DbRecipeModel) {
        recipeDao.createRecipe(dbRecipe)
    }

    override suspend fun readRecipe(): Flow<DbRecipeModel> {
        TODO("Not yet implemented")
    }

    override suspend fun updateRecipe(recipe: RecipeModel) {
        recipeDao.updateRecipe(recipe)
    }

    override suspend fun deleteRecipe(id: Long): Boolean {
        return recipeDao.deleteRecipe(id)
    }

    override suspend fun saveRecipeImageToLocal(uri: String): String {
        return storageHandler.copyFileByUri(uri.toUri())
    }
}