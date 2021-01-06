package com.kuforiji.lei.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.kuforiji.lei.R
import com.kuforiji.lei.presentation.FetchAudioUrlViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DavidRecords : Fragment() {


    private lateinit var uri: String
    private lateinit var playerView: PlayerView
    private lateinit var layout: View

    private lateinit var player: SimpleExoPlayer

    private val fetchAudioUrlViewModel: FetchAudioUrlViewModel by viewModels()

    // private val args: DavidRecordsArgs by navArgs()

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
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun initializePlayer() {
        context.let {
            player = SimpleExoPlayer.Builder(it!!).build()
            playerView.player = player
        }
        val mediaItem: MediaItem = MediaItem.fromUri("") // TODO update
        player.setMediaItem(mediaItem)
        player.playWhenReady = true
        player.prepare()
    }

    private fun init() {
        fetchAudioUrlViewModel.fetchAudioUrls("david")
    }

    private fun observers() {
        fetchAudioUrlViewModel.fetchAudioLiveData.observe(viewLifecycleOwner, {
            // TODO populate recycler view
        })
    }
}

//val surveysV1Adapter =
//    SurveysAdapter(newValue as ArrayList<SurveyTemplateV1>) { surveyTemplateV1 ->
//        itemClick(
//            surveyTemplateV1
//        )
//    }
//binding.surveysRecycler.layoutManager =
//LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
//binding.surveysRecycler.adapter = surveysV1Adapter