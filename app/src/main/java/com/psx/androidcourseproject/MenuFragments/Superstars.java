package com.psx.androidcourseproject.MenuFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.psx.androidcourseproject.Adapters.SuperstarsAdapter;
import com.psx.androidcourseproject.R;
import com.psx.androidcourseproject.model.Superstar;

import java.util.ArrayList;
import java.util.List;

public class Superstars extends Fragment {

    public AppCompatActivity appCompatActivity;
    public Context context;
    private RecyclerView recyclerView;
    public String [] names_array = {"Kevin Owens","Bayley","Alexa Bliss","Chad Gable","Neville","Bobby Roodey","Asuka","Akam","Razer"};
    public String [] description_array = {"Universal Championship","Raw Women's Championship","SmackDown's Women Championship","SmackDown Tag Team Championship","SmackDown Tag Team Championship","WWE Cruiserweight Championship","NXT Women's Championship","NXT Tag Team Chapionship","NXT Tag Team Championship"};
    public boolean[] gender = {true,false,false,true,true,true,true,false,true,true};
    public List<Superstar> superstarList;
    private SuperstarsAdapter superstarsAdapter;

    public Superstars() {
        // Required empty public constructor
    }


    public void populateList ()
    {
        superstarList = new ArrayList<>();
        Superstar superstar;
        for (int i = 0;i<names_array.length;i++){
            superstar = new Superstar(names_array[i],gender[i],description_array[i]);
            superstarList.add(superstar);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_superstars, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        appCompatActivity = (AppCompatActivity) getActivity();
        context = getContext();
        recyclerView = (RecyclerView) appCompatActivity.findViewById(R.id.superstar_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        populateList();
        superstarsAdapter = new SuperstarsAdapter(context,superstarList);
        recyclerView.setAdapter(superstarsAdapter);
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
