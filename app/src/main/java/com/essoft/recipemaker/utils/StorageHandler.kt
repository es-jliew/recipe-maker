package com.essoft.recipemaker.utils

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import com.essoft.recipemaker.constants.FileScheme
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class StorageHandler (private val context: Context): IStorageHandler {
    override fun copyFileByUri(pathFrom: Uri): String {
        if (isLocalFile(pathFrom)) {
            val inputStream = FileInputStream(File(pathFrom.path.toString()))
            val destFile = File(context.filesDir, getFileNameByInternalUri(pathFrom)) // Or any other destination path
            val output = FileOutputStream(destFile)

            inputStream.copyTo(output, 4 * 1024)
            inputStream.close()
            output.close() // Close output stream as well

            return destFile.toURI().toString()
        } else {
            val inputStream = context.contentResolver.openInputStream(pathFrom)
            val destFile = File(context.getExternalFilesDir(null), getFileNameByExternalUri(pathFrom, context).toString())
            val output = FileOutputStream(destFile)

            inputStream?.copyTo(output, 4 * 1024)
            inputStream?.close()
            output.close() // Close output stream as well

            return destFile.toURI().toString()
        }
    }

    private fun isLocalFile(uri: Uri): Boolean {
        return uri.scheme.equals(FileScheme.FILE)
    }

    private fun getFileNameByExternalUri(uri: Uri, context: Context): String? {
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        return cursor.use { it ->
            if (it != null) {
                val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                it.moveToFirst()
                it.getString(nameIndex)
            } else ""
        }
    }

    private fun getFileNameByInternalUri(uri: Uri): String {
        return uri.toString().split("/").last()
    }
}