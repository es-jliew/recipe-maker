package com.essoft.recipemaker.model

//Use for temporary updating actual entity in ObjectBox
//Dno not use this to create new entity
data class RecipeModel(
    var id: Long = 0,
    var imageUri: String = "",
    var name: String = "",
    var type: String = "",
    var ingredients: String = "",
    var instructions: String = "",
)
