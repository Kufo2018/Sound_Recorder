package com.kuforiji.lei.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.kuforiji.lei.R
import com.kuforiji.lei.adapters.AudioListAdapter
import com.kuforiji.lei.datasource.model.FetchUrlResponse
import com.kuforiji.lei.presentation.FetchAudioUrlViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DavidRecords : Fragment() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var playerView: PlayerView
    private lateinit var layout: View

    private lateinit var player: SimpleExoPlayer

    private val fetchAudioUrlViewModel: FetchAudioUrlViewModel by viewModels()

    // private val args: DavidRecordsArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        layout = inflater.inflate(R.layout.fragment_david_records, container, false)
        return layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        observers()
    }

    private fun initializePlayer(url: String) {
        context.let {
            player = SimpleExoPlayer.Builder(it!!).build()
            playerView.player = player
        }
        val mediaItem: MediaItem = MediaItem.fromUri(url)
        player.setMediaItem(mediaItem)
        player.playWhenReady = true
        player.prepare()
    }

    private fun init() {
        playerView = layout.findViewById(R.id.video_view)
        recyclerView = layout.findViewById(R.id.audio_reycler_view)
        fetchAudioUrlViewModel.fetchAudioUrls("david")
    }

    private fun observers() {
        fetchAudioUrlViewModel.fetchAudioLiveData.observe(viewLifecycleOwner, {
            initializeAdapter(it)
        })
    }

    private fun initializeAdapter(list: List<FetchUrlResponse>) {
        val adapter = AudioListAdapter(list as ArrayList<FetchUrlResponse>) { fetchUrlResponse ->
            itemClick(
                fetchUrlResponse
            )
        }
        recyclerView.layoutManager =
            LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter
    }

    private fun itemClick(fetchUrlResponse: FetchUrlResponse) {
        initializePlayer(fetchUrlResponse.url)
    }
}
