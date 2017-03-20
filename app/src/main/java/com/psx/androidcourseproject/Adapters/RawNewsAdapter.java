package com.psx.androidcourseproject.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    public RawNewsAdapter (List<RawNews> rawNewsList, Context context){
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
    public void onBindViewHolder(MyNewsViewHolder holder, int position) {
        rawNews = rawNewsList.get(position);
        holder.news_description.setText(rawNews.getDescription());
        holder.news_title.setText(rawNews.getTitle());
        holder.news_date.setText(rawNews.getDate());
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
