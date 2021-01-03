package com.kuforiji.lei.mymediarecorder

import android.media.MediaRecorder
import android.util.Log
import java.io.IOException

private const val LOG_TAG = "AudioRecordTest"

class MyMediaRecorderImpl : MyMediaRecorderInterface {

    private lateinit var mediaRecorder: MediaRecorder

    override fun startRecording(fileName: String) {
        mediaRecorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            setOutputFile(fileName)

            try {
                prepare()
            } catch (e: IOException) {
                Log.i(LOG_TAG, "prepare() failed")
            }

            start()
        }
    }

    override fun stopRecording() {
        mediaRecorder.stop()
    }

    override fun releaseRecorder() {
        mediaRecorder.release()
    }

}