package com.psx.androidcourseproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by Pranav on 07-03-2017.s
 */

public class YoutubePlayerActivity extends YouTubePlayerSupportFragment {

    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer youTubePlayer;
    private Intent intent;
    private String video_code;
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    private AppCompatActivity appCompatActivity;
    private Context context;

    public static YoutubePlayerActivity newInstance (String url) {
        YoutubePlayerActivity youtubePlayerActivity = new YoutubePlayerActivity();

        Bundle bundle = new Bundle();
        bundle.putString("url",url);
        youtubePlayerActivity.setArguments(bundle);
        return youtubePlayerActivity;
    }

    private void init()
    {
        initialize(Config.DEVELOPER_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean b) {
                youTubePlayer = player;
                youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                if (!b){
                    youTubePlayer.cueVideo(video_code);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }


    /*
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        appCompatActivity = (AppCompatActivity) getActivity();
        context = getContext();
        youTubePlayerView = (YouTubePlayerView) appCompatActivity.findViewById(R.id.youtube_view);
        youTubePlayerView.initialize(Config.DEVELOPER_KEY,this);
        intent = appCompatActivity.getIntent();
        video_code = intent.getStringExtra("video_code");
    }

    /*@Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        video_code = intent.getStringExtra("video_code");
        youTubePlayerView = (YouTubePlayerView) appCompatActivity.findViewById(R.id.youtube_view);
        youTubePlayerView.initialize(Config.DEVELOPER_KEY,this);
    }*/

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.activity_youtube_player,viewGroup,false);
    }

    protected YouTubePlayer.Provider getYouTubePLayerProvider ()
    {
        return (YouTubePlayer.Provider) appCompatActivity.findViewById(R.id.youtube_view);
    }

}
