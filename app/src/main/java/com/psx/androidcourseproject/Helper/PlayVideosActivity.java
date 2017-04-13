package com.psx.androidcourseproject.Helper;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.psx.androidcourseproject.Config;
import com.psx.androidcourseproject.R;

import static com.psx.androidcourseproject.Config.DEVELOPER_KEY;

public class PlayVideosActivity extends AppCompatActivity implements  YouTubePlayer.OnInitializedListener {

    private android.support.v7.app.ActionBar actionBar;
    private Context context;
    private Intent intent;
    private TextView textView_title;
    private YouTubePlayerSupportFragment youTubePlayerView;
    private String videoCode, videoTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_videos);
        context = getApplicationContext();
        intent = getIntent();
        // video code  for the video to play
        videoCode = intent.getStringExtra("video_code");
        videoTitle = intent.getStringExtra("video_title");
        actionBar = getSupportActionBar();
        assert actionBar!=null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setTitle(videoTitle);
        actionBar.setLogo(R.mipmap.ic_wwe_network);
        textView_title = (TextView) findViewById(R.id.video_title_play);
        youTubePlayerView = (YouTubePlayerSupportFragment)getSupportFragmentManager().findFragmentById(R.id.youtube_view);
        if (youTubePlayerView!=null){
            youTubePlayerView.initialize(DEVELOPER_KEY,this);
        }
        textView_title.setText(videoTitle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        if (!wasRestored){
            youTubePlayer.loadVideo(videoCode);
            youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this,1).show();
        }
        else {
            String error  = youTubeInitializationResult.toString();
            Toast.makeText(context,error,Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(DEVELOPER_KEY, this);
        }
    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView)findViewById(R.id.youtube_view);
    }

}
