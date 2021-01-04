package com.kuforiji.lei.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.kuforiji.lei.R
import com.kuforiji.lei.mymediaplayer.MyMediaPlayerImpl
import com.kuforiji.lei.mymediarecorder.MyMediaRecorderImpl
import com.kuforiji.lei.utils.hide
import com.kuforiji.lei.utils.show
import java.text.SimpleDateFormat
import java.util.*

private const val REQUEST_RECORD_AUDIO_PERMISSION = 200
private const val LOG_RECORD_AUDIO = "AudioRecordLog"


class RecordAudio : Fragment() {

    private var mStartRecording = true
    private var mStartPlayblack = true
    private var mediaRecorder: MyMediaRecorderImpl? = null
    private lateinit var recordText: TextView
    private lateinit var playText: TextView
    private var fileName: String? = null
    private var mediaPlayer: MyMediaPlayerImpl? = null
    private lateinit var playAudioButton: ImageView

//    private lateinit var exoPlayerView: PlayerView
//    private var exoPlayer: SimpleExoPlayer? = null
//
//    private var playWhenReady = true
//    private var currentWindow = 0
//    private var playbackPosition: Long = 0;

    // Requesting permission to RECORD_AUDIO
    private var permissionToRecordAccepted = false
    private var permissions: Array<String> = arrayOf(Manifest.permission.RECORD_AUDIO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCompat.requestPermissions(
            requireActivity(),
            permissions,
            REQUEST_RECORD_AUDIO_PERMISSION
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_record_audio, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        clickListeners(view)
    }

    private fun clickListeners(view: View) {
        val leiRecords = view.findViewById<Button>(R.id.lei_records)
        val davidRecords = view.findViewById<Button>(R.id.david_records)
        val recordAudio = view.findViewById<ImageView>(R.id.record_audio_image)
        leiRecords.setOnClickListener {
            view.findNavController().navigate(
                R.id.action_recordAudio_to_leiRecords
            )
        }
        davidRecords.setOnClickListener {
            view.findNavController().navigate(
                R.id.action_recordAudio_to_davidRecords
            )
        }
        recordAudio.setOnClickListener {
            onRecord(mStartRecording)
        }
        playAudioButton.setOnClickListener {
            onPlay(mStartPlayblack)
        }
    }

    private fun startAudioRecording() {
        context.let {
            val uuid = UUID.randomUUID().toString()
            fileName = it?.filesDir?.path + "/" + uuid + ".3gp"
            mediaRecorder?.startRecording(fileName!!)
            recordText.text = getString(R.string.stop_recording)

            playAudioButton.hide()
            playText.hide()

            mStartRecording = !mStartRecording

            Log.i(LOG_RECORD_AUDIO, "Audio recording started $fileName")
        }
    }

    private fun stopAudioRecording() {
        mediaRecorder?.stopRecording()

        recordText.text = getString(R.string.start_recording)

        playAudioButton.show()
        playText.show()

        mStartRecording = true

        Log.i(LOG_RECORD_AUDIO, "Audio recording stopped")
    }

    private fun onRecord(start: Boolean) = if (start) {
        startAudioRecording()
    } else {
        stopAudioRecording()
    }

    private fun startAudioPlayback() {
        context.let {
            mediaPlayer?.playAudio(fileName!!)
            playText.text = getString(R.string.media_is_playing)

            mStartPlayblack = !mStartPlayblack

            playAudioButton.setImageResource(R.drawable.stop_recording_image)
            Log.i(LOG_RECORD_AUDIO, "audio playback stopped")
        }
    }

    private fun stopAudioPlayback() {
        mediaPlayer?.stopPlayingAudio()
        mStartPlayblack = true
        playText.text = getString(R.string.play_audio_recording)
        playAudioButton.setImageResource(R.drawable.playback_icon)
        Log.i(LOG_RECORD_AUDIO, "Audio playback stopped")
    }

    private fun onPlay(start: Boolean) = if (start) {
        startAudioPlayback()
    } else {
        stopAudioPlayback()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionToRecordAccepted = if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        } else {
            false
        }
        if (!permissionToRecordAccepted) activity?.finish()
    }

    private fun generateFileName(): String {
        val date = Calendar.getInstance().time
        return "Recorded on " + date.toString("yyyy/MM/dd HH:mm:ss")
    }

    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    override fun onStop() {
        super.onStop()
        mediaRecorder?.stopRecording()
        mediaPlayer?.stopPlayingAudio()
    }

    private fun init(view: View) {
        recordText = view.findViewById(R.id.record_audio_text)
        recordText.text = getString(R.string.start_recording)
        playText = view.findViewById(R.id.playback_audio_text)
        playText.text = getString(R.string.play_audio_recording)
        playAudioButton = view.findViewById(R.id.playback_audio)

        playAudioButton.hide()
        playText.hide()

        mediaPlayer = MyMediaPlayerImpl()
        mediaRecorder = MyMediaRecorderImpl()
    }
}


