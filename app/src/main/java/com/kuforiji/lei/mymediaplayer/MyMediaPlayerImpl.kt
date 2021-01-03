package com.kuforiji.lei.mymediaplayer

import android.media.MediaPlayer
import android.util.Log
import java.io.IOException

class MyMediaPlayerImpl : MyMediaPlayerInterface {

    private lateinit var mediaPlayer: MediaPlayer

    override fun playAudio(fileName: String) {
        mediaPlayer = MediaPlayer().apply {
            try {
                setDataSource(fileName)
                prepare()
                start()
            } catch (e: IOException) {
                Log.i("AudioRecordLog", "playback prepare() failed")
            }
        }
    }

    override fun stopPlayingAudio() {
        mediaPlayer.stop()
    }

    override fun releasePlayer() {
        mediaPlayer.release()
    }
}