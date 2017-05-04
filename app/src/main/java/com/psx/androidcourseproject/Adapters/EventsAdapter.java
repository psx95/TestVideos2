package com.psx.androidcourseproject.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.psx.androidcourseproject.R;
import com.psx.androidcourseproject.model.Event;

import java.util.List;

/**
 * Created by Pranav on 04-05-2017.
 */

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.MyEventsViewHolder> {


    private Context context;
    private List<Event> eventList;

    public EventsAdapter (Context context, List<Event> eventList){
        this.context = context;
        this.eventList = eventList;
    }


    @Override
    public MyEventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_event,parent,false);
        MyEventsViewHolder myEventsViewHolder = new MyEventsViewHolder(view);
        return myEventsViewHolder;
    }


    @Override
    public void onBindViewHolder(MyEventsViewHolder holder, int position) {
        // TODO: implement Listners on the seperate views in order to cie the event details
        holder.event_name.setText(eventList.get(position).getEvent_name());
        holder.event_date.setText(eventList.get(position).getEvent_date());
        holder.event_location.setText(eventList.get(position).getEvent_location());
        holder.event_year.setText(eventList.get(position).getEvent_year());
        holder.event_month.setText(eventList.get(position).getEvent_month());
        holder.event_time.setText(eventList.get(position).getEvent_time());
    }


    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public class MyEventsViewHolder extends RecyclerView.ViewHolder {

        private TextView event_date, event_month, event_year, event_name, event_time, event_location;

        public MyEventsViewHolder(View itemView) {
            super(itemView);
            event_date = (TextView) itemView.findViewById(R.id.event_date);
            event_location = (TextView) itemView.findViewById(R.id.event_location);
            event_month = (TextView) itemView.findViewById(R.id.event_month);
            event_time = (TextView) itemView.findViewById(R.id.event_day_time);
            event_year = (TextView) itemView.findViewById(R.id.event_year);
            event_name = (TextView) itemView.findViewById(R.id.event_title_name);
        }
    }
}
