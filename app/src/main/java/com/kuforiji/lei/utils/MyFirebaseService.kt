package com.kuforiji.lei.utils

import android.net.Uri
import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.UploadTask
import com.google.firebase.storage.ktx.storage
import com.google.firebase.storage.ktx.storageMetadata
import java.io.File

private const val LOG_RECORD_AUDIO = "AudioRecordLog"

class MyFirebaseService {

    private val storage = Firebase.storage
    private var storageRef = storage.reference

    private lateinit var uploadTask: UploadTask

    private var downloadUri: Uri? = null

    fun uploadAudioFiles(
        fileName: String,
        filePath: String,
        uploader: String,
        onUploadSuccess: (mediaUrl: String) -> Unit,
        onUploadFailure: (message: String) -> Unit,
        uploadProgress: (progress: String) -> Unit,
        getDownloadUrl: (uri: String) -> Unit
    ) {

        val file = Uri.fromFile(File(filePath))
        val ref = storageRef
            .child("audio")
            .child(uploader + "/${file.lastPathSegment}")

        val metadata = storageMetadata {
            setCustomMetadata(FIREBASE_METADATA_KEY, fileName)
        }

        uploadTask = ref.putFile(file, metadata)

        // Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener {
            Log.i(LOG_RECORD_AUDIO, "Upload failed " + it.message)
            onUploadFailure(it.message!!)
        }
            .addOnSuccessListener { taskSnapshot ->
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
                if (it.bytesTransferred == it.totalByteCount) {

                    ref.downloadUrl.addOnSuccessListener { uri ->
                        getDownloadUrl(uri.toString())
                    }.addOnFailureListener {
                        Log.i(LOG_RECORD_AUDIO, "Could not get download Uri")
                    }

                }
            }

            .addOnPausedListener {
                Log.i(LOG_RECORD_AUDIO, "Upload is paused")
                uploadProgress("Upload is paused")
            }
    }

    fun getAudioLinksAndMetaData() {
        val listOfUrls = arrayListOf<String>()
        val listOfMetaData = arrayListOf<String>()
        val ref = storageRef
            .child("audio/david")

        ref.listAll()
            .addOnSuccessListener {
                it.items.forEach { item ->
                    item.downloadUrl
                        .addOnSuccessListener { uri ->
                            listOfUrls.add(uri.toString())
                            Log.i(LOG_RECORD_AUDIO, "working")
                        }
                        .addOnFailureListener {
                            Log.i(LOG_RECORD_AUDIO, "could not each item's urls")
                        }

                    item.metadata.addOnSuccessListener { metadata ->
                        listOfMetaData.add(
                            metadata.getCustomMetadata(FIREBASE_METADATA_KEY).toString()
                        )
                        Log.i(LOG_RECORD_AUDIO, "working")
                    }.addOnFailureListener { exception ->
                        Log.i(
                            LOG_RECORD_AUDIO,
                            "could not fetch meta data ${exception.message}"
                        )
                    }
                }
            }.addOnFailureListener {
                Log.i(LOG_RECORD_AUDIO, "could not fetch audio files")
                // Uh-oh, an error occurred.
            }
    }
}