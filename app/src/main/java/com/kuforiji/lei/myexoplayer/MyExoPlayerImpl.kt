package com.kuforiji.lei.myexoplayer

import android.content.Context
import com.google.android.exoplayer2.SimpleExoPlayer

class MyExoPlayerImpl(context: Context) : MyExoplayerInterface {

    private var player = SimpleExoPlayer.Builder(context).build()

    override fun play() {


    }

    override fun stop() {

    }

    override fun pause() {

    }

}