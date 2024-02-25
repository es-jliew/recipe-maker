package com.essoft.recipemaker.model

import android.os.Parcelable
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import kotlinx.parcelize.Parcelize

//Use for persisting new entity in ObjectBox
//@Id will be automatically generated
@Entity
@Parcelize
data class DbRecipeModel(
    @Id
    var id: Long = 0,
    var imageUri: String = "",
    var name: String = "",
    var type: String = "",
    var ingredients: String = "",
    var instructions: String = "",
) : Parcelable