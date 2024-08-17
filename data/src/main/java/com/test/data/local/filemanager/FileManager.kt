package com.test.data.local.filemanager

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import java.io.File
import java.io.FileOutputStream

class FileManager(
    private val context: Context
) {

    fun copyImagesToInternalStorage(imageUris: List<String>): List<String> {
        val newUris = mutableListOf<String>()
        val directory = File(context.filesDir, "images")
        if (!directory.exists()) {
            directory.mkdirs()
        }
        for (uriString in imageUris) {
            val uri = Uri.parse(uriString)
            try {
                val inputStream = context.contentResolver.openInputStream(uri)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                inputStream?.close()
                val fileName = uri.lastPathSegment ?: "image_${System.currentTimeMillis()}.png"
                val file = File(directory, fileName)
                val outputStream = FileOutputStream(file)
                bitmap?.compress(Bitmap.CompressFormat.PNG, 40, outputStream)
                outputStream.flush()
                outputStream.close()
                val newUri = Uri.fromFile(file).toString()
                newUris.add(newUri)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return newUris
    }

}