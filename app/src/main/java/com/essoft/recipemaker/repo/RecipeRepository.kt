package com.essoft.recipemaker.repo

import androidx.core.net.toUri
import com.essoft.recipemaker.model.NonEntityRecipeModel
import com.essoft.recipemaker.model.RecipeModel
import com.essoft.recipemaker.utils.StorageHandler
import io.objectbox.Box
import io.objectbox.kotlin.toFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

class RecipeRepository(private val recipeBox: Box<RecipeModel>, private val storageHandler: StorageHandler): IRecipeRepository {
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun retrieveAllRecipes(): Flow<List<RecipeModel>> {
        return recipeBox.query().build().subscribe().toFlow()
    }

    override suspend fun createRecipe(recipe: RecipeModel) {
        recipe.imageUri = recipe.imageUri?.let { storageHandler.copyFileByUri(it.toUri()) }
        recipeBox.put(recipe)
    }

    override suspend fun readRecipe(): Flow<RecipeModel> {
        TODO("Not yet implemented")
    }

    override suspend fun updateRecipe(recipe: NonEntityRecipeModel) {
        val recipeFromDb = recipeBox.get(recipe.id)
        recipeFromDb.imageUri = recipe.imageUri
        recipeFromDb.name = recipe.name
        recipeFromDb.type = recipe.type
        recipeFromDb.ingredients = recipe.ingredients
        recipeFromDb.instructions = recipe.instructions
        recipeBox.put(recipeFromDb)
    }

    override suspend fun deleteRecipe(id: Long): Boolean {
        return recipeBox.remove(id)
    }
}