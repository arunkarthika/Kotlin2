package com.example.kotlin

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ext.ima.ImaAdsLoader
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.ads.AdsLoader
import com.google.android.exoplayer2.source.ads.AdsMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

class StreamActivity : AppCompatActivity() {
    lateinit var playerView: PlayerView
    val playbackposition = 0L
    val STreamingurl = "https://www.hdwplayer.com/videos/300.mp4"
    val adsurl = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"

    internal lateinit var player: SimpleExoPlayer
    lateinit var adsloader:ImaAdsLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stream)
        playerView = findViewById(R.id.playerview)
        adsloader=ImaAdsLoader(this,Uri.parse(STreamingurl))


    }

    override fun onStart() {
        super.onStart()
        player = ExoPlayerFactory.newSimpleInstance(this, DefaultTrackSelector())
        playerView.setPlayer(player)
        val defaultTrackSelector = DefaultDataSourceFactory(this, Util.getUserAgent(this, "Kotlin2"))
        val extractorMediaSource = ExtractorMediaSource.Factory(defaultTrackSelector)
            .createMediaSource(Uri.parse(STreamingurl))
        val adsmedi= AdsMediaSource(extractorMediaSource,defaultTrackSelector,adsloader,playerView.overlayFrameLayout)

        player.prepare(adsmedi)
        player.setPlayWhenReady(true)

    }

    override fun onStop() {
        super.onStop()
        playerView.player = null
        player.release()
        player == null
    }

    override fun onDestroy() {
        super.onDestroy()
        adsloader.release()
    }
}
