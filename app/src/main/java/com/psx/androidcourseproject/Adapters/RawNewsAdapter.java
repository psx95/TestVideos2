package com.psx.androidcourseproject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.psx.androidcourseproject.Helper.DisplayNewsActivity;
import com.psx.androidcourseproject.R;
import com.psx.androidcourseproject.model.RawNews;

import java.util.List;

/**
 * Created by Pranav on 06-03-2017.
 */

public class RawNewsAdapter extends RecyclerView.Adapter<RawNewsAdapter.MyNewsViewHolder> {

    private List<RawNews> rawNewsList;
    private Context context;
    private RawNews rawNews;

    public RawNewsAdapter (List<RawNews> rawNewsList, final Context context){
        this.rawNewsList = rawNewsList;
        this.context = context;
    }

    @Override
    public MyNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_news_list_item,parent,false);
        MyNewsViewHolder myNewsViewHolder = new MyNewsViewHolder(view);
        return myNewsViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyNewsViewHolder holder, final int position) {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DisplayNewsActivity.class);
                intent.putExtra("news_title",rawNewsList.get(position).getTitle());
                Log.d("TESTING_TITLE",rawNewsList.get(position).getTitle());
                intent.putExtra("news_date",holder.news_date.getText().toString());
                intent.putExtra("news_content",rawNewsList.get(position).getContent());
                context.startActivity(intent);
            }
        };
        rawNews = rawNewsList.get(position);
        holder.news_description.setText(rawNews.getDescription());
        holder.news_title.setText(rawNews.getTitle());
        holder.news_date.setText(rawNews.getDate());
        holder.news_date.setOnClickListener(onClickListener);
        holder.news_title.setOnClickListener(onClickListener);
        holder.news_description.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return rawNewsList.size();
    }

    public class MyNewsViewHolder extends RecyclerView.ViewHolder {

        private TextView news_date;
        private TextView news_title;
        private TextView news_description;

        public MyNewsViewHolder(View itemView) {
            super(itemView);
            news_date = (TextView) itemView.findViewById(R.id.raw_news_date);
            news_title = (TextView) itemView.findViewById(R.id.raw_news_heading);
            news_description = (TextView) itemView.findViewById(R.id.raw_news_description);
        }
    }
}
