package com.psx.androidcourseproject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.psx.androidcourseproject.Helper.DisplayPhotosActivity;
import com.psx.androidcourseproject.R;
import com.psx.androidcourseproject.model.RawPhotos;

import java.util.List;

/**
 * Created by Pranav on 07-03-2017.
 */

public class RawPhotosAdapter extends RecyclerView.Adapter<RawPhotosAdapter.MyPhotosViewHolder>{

    private List<RawPhotos> rawPhotosList;
    private Context context;

    public RawPhotosAdapter (List<RawPhotos> rawPhotosList, Context context) {
        this.context = context;
        this.rawPhotosList = rawPhotosList;
    }

    @Override
    public MyPhotosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_photos_list_item,parent,false);
        MyPhotosViewHolder myPhotosViewHolder = new MyPhotosViewHolder(view);
        return myPhotosViewHolder;
    }

    @Override
    public void onBindViewHolder(MyPhotosViewHolder holder, int position) {
        holder.imageView_holder.setImageResource(rawPhotosList.get(position).getImage_id());
        holder.imageView_holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DisplayPhotosActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rawPhotosList.size();
    }

    public class MyPhotosViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView_holder;

        public MyPhotosViewHolder(View itemView) {
            super(itemView);
            imageView_holder = (ImageView) itemView.findViewById(R.id.image_holder_raw_photos);
        }
    }
}
