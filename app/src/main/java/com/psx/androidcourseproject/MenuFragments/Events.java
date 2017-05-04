package com.psx.androidcourseproject.MenuFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.psx.androidcourseproject.Adapters.EventsAdapter;
import com.psx.androidcourseproject.R;
import com.psx.androidcourseproject.model.Event;

import java.util.ArrayList;
import java.util.List;


public class Events extends Fragment {

    private Context context;
    private ActionBar actionBar;
    private RecyclerView recyclerView_events;
    private AppCompatActivity appCompatActivity;
    private List<Event> eventList;
    private EventsAdapter eventsAdapter;

    public Events() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventList = new ArrayList<>();
        createEvents();
    }

    public void createEvents(){
        eventList.add(new Event("WWE Starnight","London, UK","THU. - 07:00 PM GMT+02:00","MAY","06","2017"));
        eventList.add(new Event("WWE NXT","Barcelona, Spain","FRI. - 09:00 PM GMT+03:30","JUNE","09","2017"));
        eventList.add(new Event("WWE Live Liverpool","Liverpool, UK","MON. - 05:00 PM GMT+01:00","AUGUST","15","2017"));
        eventList.add(new Event("WWE Live Stockholm","Stockholm, Sweden","THU. - 04:30 PM GMT+02:00","OCTOBER","07","2017"));
        eventList.add(new Event("WWE Smackdown London","London, UK","THU. - 07:30 PM GMT+02:00","NOVEMBER","20","2017"));
        eventList.add(new Event("WWE Starnight","London, UK","THU. - 07:00 PM GMT+02:00","MAY","06","2017"));
        eventList.add(new Event("WWE NXT","Barcelona, Spain","FRI. - 09:00 PM GMT+03:30","JUNE","09","2017"));
        eventList.add(new Event("WWE Live Liverpool","Liverpool, UK","MON. - 05:00 PM GMT+01:00","AUGUST","15","2017"));
        eventList.add(new Event("WWE Live Stockholm","Stockholm, Sweden","THU. - 04:30 PM GMT+02:00","OCTOBER","07","2017"));
        eventList.add(new Event("WWE Smackdown London","London, UK","THU. - 07:30 PM GMT+02:00","NOVEMBER","20","2017"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_events, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        appCompatActivity = (AppCompatActivity) getActivity();
        recyclerView_events = (RecyclerView) appCompatActivity.findViewById(R.id.recycler_view_new_events);
        context = appCompatActivity.getApplicationContext();
        actionBar = appCompatActivity.getSupportActionBar();
        actionBar.setLogo(R.mipmap.ic_wwe_network);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView_events.setLayoutManager(linearLayoutManager);
        eventsAdapter = new EventsAdapter(context,eventList);
        recyclerView_events.setAdapter(eventsAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
