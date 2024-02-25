package com.essoft.recipemaker.utils

import android.net.Uri

interface IStorageHandler {
    fun copyFileByUri(pathFrom: Uri) : String
}