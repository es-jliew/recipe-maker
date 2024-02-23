package com.essoft.recipemaker.model

sealed class RecipeType(val name:String) {
    data object ALL : RecipeType("All")
    data object APPETIZER : RecipeType("Appetizer")
    data object DESSERT : RecipeType("Dessert")
    data object MAIN : RecipeType("Main")
    data object SIDE : RecipeType("Side")
    data object SOUP : RecipeType("Soup")

    companion object {
        fun getAllRecipeTypes(): List<RecipeType> {
            return listOf(ALL, MAIN, DESSERT, SOUP, APPETIZER, SIDE)
        }

        fun getSubRecipeTypes(): List<RecipeType> {
            return getAllRecipeTypes().filter { it.name != "All" }
        }

        fun getRecipeType(value: String): RecipeType {
            return when (value) {
                ALL.name -> ALL
                MAIN.name -> MAIN
                DESSERT.name -> DESSERT
                SOUP.name -> SOUP
                APPETIZER.name -> APPETIZER
                SIDE.name -> SIDE
                else -> ALL
            }
        }
    }
}
