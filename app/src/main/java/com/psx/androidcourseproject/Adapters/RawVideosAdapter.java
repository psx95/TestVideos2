package com.psx.androidcourseproject.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.psx.androidcourseproject.Config;
import com.psx.androidcourseproject.R;
import com.psx.androidcourseproject.model.RawVideos;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Pranav on 06-03-2017.
 */

public class RawVideosAdapter extends RecyclerView.Adapter<RawVideosAdapter.MyVideosViewHolder>{

    private Context context;
    private List<RawVideos> rawVideosList;

    public RawVideosAdapter (Context context, List<RawVideos> rawVideosList){
        this.context = context;
        this.rawVideosList = rawVideosList;
    }

    @Override
    public MyVideosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_video_list_item,parent,false);
        MyVideosViewHolder myVideosViewHolder = new MyVideosViewHolder(view);
        return myVideosViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyVideosViewHolder holder, final int position) {
        Log.d("RAWVIDEOSADAPTER","BindViewHolder");
        final YouTubeThumbnailLoader.OnThumbnailLoadedListener onThumbnailLoadedListener = new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
            @Override
            public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                //
            }

            @Override
            public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
                Toast.makeText(context,"Error Loading Videos. Please try again later.",Toast.LENGTH_LONG).show();
                Log.d("RAWVIDEOSADAPTER",errorReason.toString());
            }
        };
        holder.youTubeThumbnailView.initialize(Config.DEVELOPER_KEY, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {
                youTubeThumbnailLoader.setVideo(rawVideosList.get(position).getVideo_code());
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(onThumbnailLoadedListener);
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(context,"Error has Occoured. Please Try again later",Toast.LENGTH_LONG).show();
            }
        });
        holder.videoTitle.setText(rawVideosList.get(position).getTitle());
    }


    @Override
    public int getItemCount() {
        return rawVideosList.size();
    }

    public class MyVideosViewHolder extends RecyclerView.ViewHolder {

        public YouTubeThumbnailView youTubeThumbnailView;
        public TextView videoTitle;

        public MyVideosViewHolder(View itemView) {
            super(itemView);
            youTubeThumbnailView = (YouTubeThumbnailView) itemView.findViewById(R.id.youtube_view_raw);
            videoTitle = (TextView) itemView.findViewById(R.id.video_title_raw);
        }
    }
}
