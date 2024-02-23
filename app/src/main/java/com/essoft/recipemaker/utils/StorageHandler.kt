package com.essoft.recipemaker.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import java.io.File
import java.io.FileOutputStream

class StorageHandler (private val context: Context) {
    fun copyFileByUri(pathFrom: Uri): String {
        val inputStream = context.contentResolver.openInputStream(pathFrom)
        val destFile = File(context.getExternalFilesDir(null), getFileNameByUri(pathFrom, context).toString())
        val output = FileOutputStream(destFile)
        inputStream?.copyTo(output, 4 * 1024)
        inputStream?.close()
        return destFile.toURI().toString()
    }

    @SuppressLint("Recycle")
    private fun getFileNameByUri(uri: Uri, context: Context): String? {
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        return if (cursor != null) {
            val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            cursor.moveToFirst()
            cursor.getString(nameIndex)
        } else ""
    }
}