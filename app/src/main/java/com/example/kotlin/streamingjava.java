package com.example.kotlin;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ext.ima.ImaAdsLoader;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.ads.AdsMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class streamingjava extends AppCompatActivity {
    PlayerView playerView;
    SimpleExoPlayer player;
    ImaAdsLoader imaAdsLoader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stream);
        playerView = findViewById(R.id.playerview);

        imaAdsLoader = new ImaAdsLoader(this, Uri.parse("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"));
    }

    @Override
    protected void onStart() {
        super.onStart();
        player = ExoPlayerFactory.newSimpleInstance(this, new DefaultTrackSelector());
        playerView.setPlayer(player);
        DefaultDataSourceFactory defaultTrackSelector = new DefaultDataSourceFactory(streamingjava.this, Util.getUserAgent(streamingjava.this, "Kotlin2"));
        ExtractorMediaSource extractorMediaSource = new ExtractorMediaSource.Factory(defaultTrackSelector).createMediaSource(Uri.parse("https://www.hdwplayer.com/videos/300.mp4"));
        AdsMediaSource adsMediaSource = new AdsMediaSource(extractorMediaSource, defaultTrackSelector, imaAdsLoader, playerView.getOverlayFrameLayout());
        player.prepare(adsMediaSource);
        player.setPlayWhenReady(true);
    }

    @Override
    protected void onStop() {
        super.onStop();
        playerView.setPlayer(null);
        player.release();
        player = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        imaAdsLoader.release();
    }
}
