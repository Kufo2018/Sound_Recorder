package com.kuforiji.lei.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.kuforiji.lei.R


class DavidRecords : Fragment() {


    private lateinit var uri: String
    private lateinit var playerView: PlayerView
    private lateinit var layout: View

    private lateinit var player: SimpleExoPlayer

    private val args: DavidRecordsArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        layout = inflater.inflate(R.layout.fragment_david_records, container, false)
        return layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        playerView = layout.findViewById(R.id.video_view)
        uri = args.downloadUri
        Log.i("AudioRecordLog", "uri received is $uri") // TODO no need for arguments
    }

    private fun initializePlayer() {
        context.let {
            player = SimpleExoPlayer.Builder(it!!).build()
            playerView.player = player
        }
        val mediaItem: MediaItem = MediaItem.fromUri(uri)
        player.setMediaItem(mediaItem)
        player.playWhenReady = true
        player.prepare()
    }
}