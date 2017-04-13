package com.psx.androidcourseproject.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.Image;
import android.os.RecoverySystem;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.psx.androidcourseproject.Config;
import com.psx.androidcourseproject.Helper.PlayVideosActivity;
import com.psx.androidcourseproject.R;
import com.psx.androidcourseproject.model.VideoCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pranav on 21-02-2017.
 */

public class NewVideosAdapter extends RecyclerView.Adapter<NewVideosAdapter.CardViewHolder> {

    private Context context;
    List<VideoCard> videoCards = new ArrayList<>();
    private YouTubePlayer youTubePlayer;

    public NewVideosAdapter (){
        // empty Constructor
    }

    public NewVideosAdapter (List<VideoCard> videoCards, Context context){
        this.context = context;
        this.videoCards = videoCards;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_card,parent,false);
        CardViewHolder cardViewHolder = new CardViewHolder(view);
        return cardViewHolder;
    }

    @Override
    public void onBindViewHolder(final CardViewHolder holder, final int position) {
        // inflate all the video cards with the same video
        final YouTubeThumbnailLoader.OnThumbnailLoadedListener onThumbnailLoadedListener = new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
            @Override
            public void onThumbnailLoaded(final YouTubeThumbnailView youTubeThumbnailView, String s) {
                youTubeThumbnailView.setVisibility(View.VISIBLE);
                holder.image_holder.setVisibility(View.GONE);
                holder.imageButton_play.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // play the video
                        Intent intent = new Intent(context, PlayVideosActivity.class);
                        intent.putExtra("video_code",videoCards.get(position).getVideo_code());
                        intent.putExtra("video_title",videoCards.get(position).getVideo_title());
                        context.startActivity(intent);
                    }
                });
            }

            @Override
            public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
                Toast.makeText(context,"Error Loading the videos, try again.",Toast.LENGTH_SHORT).show();
            }
        };

        holder.youTubeThumbnailView.initialize(Config.DEVELOPER_KEY, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
                youTubeThumbnailLoader.setVideo(videoCards.get(position).getVideo_code());
                    youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                        @Override
                        public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                            youTubeThumbnailView.setVisibility(View.VISIBLE);
                            holder.image_holder.setVisibility(View.GONE);
                            holder.imageButton_play.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    // play the video
                                    Intent intent = new Intent(context, PlayVideosActivity.class);
                                    intent.putExtra("video_code",videoCards.get(position).getVideo_code());
                                    intent.putExtra("video_title",videoCards.get(position).getVideo_title());
                                    context.startActivity(intent);
                                }
                            });
                            youTubeThumbnailLoader.release();
                        }

                        @Override
                        public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {

                        }
                    });
                //youTubeThumbnailLoader.setVideo(Config.YOUTUBE_VIDEO_CODE);
                //youTubeThumbnailLoader.release();
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                Log.d("INITIALISATION ERROR","There seems to be some error");
                Toast.makeText(context,"Error loading thumbanils. PLease Try again",Toast.LENGTH_SHORT).show();
            }
        });
        holder.video_title.setText(videoCards.get(position).getVideo_title());
        holder.imageButton_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }


    @Override
    public int getItemCount() {
        return videoCards.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder{

        private YouTubeThumbnailView youTubeThumbnailView;
        private ImageView image_holder;
        private ImageButton imageButton_play;
        private ImageView image_classifier;
        private TextView video_title;
        public YouTubePlayer.OnInitializedListener onInitializedListener;

        public CardViewHolder(View itemView) {
            super(itemView);
            youTubeThumbnailView = (YouTubeThumbnailView) itemView.findViewById(R.id.youtube_view);
            image_holder = (ImageView) itemView.findViewById(R.id.image_holder);
            imageButton_play  = (ImageButton) itemView.findViewById(R.id.imageButton_play);
            image_classifier = (ImageView) itemView.findViewById(R.id.image_classifier);
            video_title = (TextView) itemView.findViewById(R.id.video_title_new_videos);
            //logo_image = (ImageView) itemView.findViewById(R.id.logo_wwe);
        }


    }
}
