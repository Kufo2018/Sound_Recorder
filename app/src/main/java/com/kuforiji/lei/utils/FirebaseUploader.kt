package com.kuforiji.lei.utils

import android.net.Uri
import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage
import java.io.File

private const val LOG_RECORD_AUDIO = "AudioRecordLog"

class FirebaseUploader {

    private val storage = Firebase.storage
    private var storageRef = storage.reference

    private lateinit var uploadTask: UploadTask

    fun uploadAudioFiles(
        fileName: String,
        uploader: String,
        onUploadSuccess: (mediaUrl: String) -> Unit,
        onUploadFailure: (message: String) -> Unit,
        uploadProgress: (progress: String) -> Unit
    ) {

        val file = Uri.fromFile(File(fileName))
        val riversRef = storageRef
            .child("audio")
            .child(uploader + "/${file.lastPathSegment}")

        uploadTask = riversRef.putFile(file)

        // Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener {
            Log.i(LOG_RECORD_AUDIO, "Upload failed " + it.message)
            onUploadFailure(it.message!!)
        }
            .addOnSuccessListener { taskSnapshot ->
                Log.i(LOG_RECORD_AUDIO, "Upload succeeded ")
                if (taskSnapshot.task.isSuccessful) {
                    val downloadUri =
                        taskSnapshot.metadata?.bucket + "/" + taskSnapshot.metadata?.path
                    onUploadSuccess(downloadUri)
                } else {
                    println("Task failed  ${taskSnapshot.task.exception.toString()}")
                }
            }
            .addOnProgressListener {
                Log.i(LOG_RECORD_AUDIO, "upload commenced")
                uploadProgress("${(it.bytesTransferred / it.totalByteCount) * 100}%")
            }
            .addOnPausedListener {
                Log.i(LOG_RECORD_AUDIO, "Upload is paused")
                uploadProgress("Upload is paused")
            }
    }
}