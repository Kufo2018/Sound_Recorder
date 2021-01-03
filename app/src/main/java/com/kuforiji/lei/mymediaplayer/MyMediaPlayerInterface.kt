package com.kuforiji.lei.mymediaplayer

interface MyMediaPlayerInterface {

    fun playAudio(fileName: String) {}
    fun stopPlayingAudio() {}
    fun releasePlayer() {}
}