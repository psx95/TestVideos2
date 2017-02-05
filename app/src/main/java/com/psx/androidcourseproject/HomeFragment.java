package com.psx.androidcourseproject;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import com.psx.androidcourseproject.tabFragments.LeftFragment;
import com.psx.androidcourseproject.tabFragments.RightFragment;

public class HomeFragment extends Fragment {

    private AppCompatActivity activity;
    ViewPager viewPager;
    LeftFragment leftFragment;
    RightFragment rightFragment;
    ViewPagerAdapter viewPagerAdapter;
    TabLayout tabLayout;
    Toolbar toolbar;

    //commit 2

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    // for commit

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (AppCompatActivity) getActivity();
        toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        viewPager = (ViewPager) activity.findViewById(R.id.viewpager);
        activity.setSupportActionBar(toolbar);
        tabLayout = (TabLayout) activity.findViewById(R.id.tabs);
        viewPagerAdapter = new ViewPagerAdapter(getFragmentManager());
        leftFragment = new LeftFragment();
        rightFragment = new RightFragment();
        viewPagerAdapter.addFragment(leftFragment,"LEFT");
        viewPagerAdapter.addFragment(rightFragment,"RIGHT");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> mFragmentList = new ArrayList<>();
        private List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment (Fragment fragment, String title){
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        //this function is responsible for displaying the title of the tabs that appear in tabLayout
        //the 'page' title, pages displayed using viewPager get their title by calling this function
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
