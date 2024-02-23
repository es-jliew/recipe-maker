package com.essoft.recipemaker.model

import android.os.Parcelable
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class RecipeModel(
    @Id
    var id: Long = 0,
    var imageUri: String? = null,
    var name: String = "",
    var type: String? = null,
    var ingredients: String = "",
    var instructions: String = "",
) : Parcelable