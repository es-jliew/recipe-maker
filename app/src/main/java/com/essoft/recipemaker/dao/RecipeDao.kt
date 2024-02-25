package com.essoft.recipemaker.dao

import com.essoft.recipemaker.model.DbRecipeModel
import com.essoft.recipemaker.model.RecipeModel
import io.objectbox.Box
import io.objectbox.kotlin.toFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

class RecipeDao(private val db: Box<DbRecipeModel>): IRecipeDao {
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getAllRecipes(): Flow<List<DbRecipeModel>> {
        return db.query().build().subscribe().toFlow()
    }

    override suspend fun createRecipe(dbRecipe: DbRecipeModel) {
        db.put(dbRecipe)
    }

    override suspend fun readRecipe(id: Long): DbRecipeModel {
        return db.get(id)
    }

    override suspend fun updateRecipe(recipe: RecipeModel) {
        val dbRecipe = readRecipe(recipe.id)
        mapRecipeToDbRecipe(recipe, dbRecipe)
        db.put(dbRecipe)
    }

    override suspend fun deleteRecipe(id: Long): Boolean {
        return db.remove(id)
    }

    private fun mapRecipeToDbRecipe(recipe: RecipeModel, dbRecipe: DbRecipeModel) {
        dbRecipe.imageUri = recipe.imageUri
        dbRecipe.name = recipe.name
        dbRecipe.type = recipe.type
        dbRecipe.ingredients = recipe.ingredients
        dbRecipe.instructions = recipe.instructions
    }
}