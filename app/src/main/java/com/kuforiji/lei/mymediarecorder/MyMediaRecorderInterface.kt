package com.kuforiji.lei.mymediarecorder

interface MyMediaRecorderInterface {

    fun stopRecording() {}
    fun startRecording(fileName: String) {}
    fun releaseRecorder() {}
}