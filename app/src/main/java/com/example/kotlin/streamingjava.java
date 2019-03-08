package com.example.kotlin;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ext.ima.ImaAdsLoader;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.ads.AdsMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class streamingjava extends Activity {
    PlayerView playerView;
    SimpleExoPlayer player;
    ImaAdsLoader imaAdsLoader;
    ImageView exo_pause;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_stream);
        playerView = findViewById(R.id.playerview);




        imaAdsLoader = new ImaAdsLoader(this, Uri.parse("https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dlinear&correlator="));
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
        Log.d("playerposition", String.valueOf(player.getPlayWhenReady()));

    }

    @Override
    protected void onStop() {
        super.onStop();
        playerView.setPlayer(null);
        player.release();
        player = null;
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.setPlayWhenReady(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        imaAdsLoader.release();
    }
}
