package com.essoft.recipemaker.model

sealed class DbResponse {
    data class Success(val message: String) : DbResponse()
    data class Failure(val message: String) : DbResponse()
}