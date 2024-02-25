package com.essoft.recipemaker.utils

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import java.io.File
import java.io.FileOutputStream

class StorageHandler (private val context: Context): IStorageHandler {
    override fun copyFileByUri(pathFrom: Uri): String {
        val inputStream = context.contentResolver.openInputStream(pathFrom)
        val destFile = File(context.getExternalFilesDir(null), getFileNameByUri(pathFrom, context).toString())
        val output = FileOutputStream(destFile)
        inputStream?.copyTo(output, 4 * 1024)
        inputStream?.close()
        return destFile.toURI().toString()
    }

    private fun getFileNameByUri(uri: Uri, context: Context): String? {
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        return cursor.use { it ->
            if (it != null) {
                val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                it.moveToFirst()
                it.getString(nameIndex)
            } else ""
        }
    }
}