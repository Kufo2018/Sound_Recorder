package com.kuforiji.lei.mymediaplayer

import android.media.MediaPlayer
import android.util.Log

class MyMediaPlayerImpl : MyMediaPlayerInterface {

    private var mediaPlayer: MediaPlayer? = null

    override fun playAudio(fileName: String) {
        mediaPlayer = MediaPlayer().apply {
            try {
                setDataSource(fileName)
                prepare()
                start()
            } catch (e: Exception) {
                Log.i("AudioRecordLog", "playback prepare() failed")
            }
        }
    }

    override fun stopPlayingAudio() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    fun MyMediaPlayerImpl.stopPlayBackWhenComplete() {
        mediaPlayer?.setOnCompletionListener { stopPlayingAudio() }
    }
}