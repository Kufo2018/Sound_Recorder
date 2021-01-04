package com.kuforiji.lei.mymediarecorder

import android.media.MediaRecorder
import android.util.Log

private const val LOG_TAG = "AudioRecordLog"

class MyMediaRecorderImpl : MyMediaRecorderInterface {

    private var mediaRecorder: MediaRecorder? = null

    override fun startRecording(fileName: String) {
        mediaRecorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            setOutputFile(fileName)

            try {
                prepare()
            } catch (e: Exception) {
                Log.i(LOG_TAG, "prepare() failed")
            }

            start()
        }
    }

    override fun stopRecording() {
        mediaRecorder?.stop()
        mediaRecorder?.release()
        mediaRecorder = null
    }
}