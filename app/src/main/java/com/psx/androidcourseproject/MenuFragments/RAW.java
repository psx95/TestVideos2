package com.psx.androidcourseproject.MenuFragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.psx.androidcourseproject.Adapters.NewVideosAdapter;
import com.psx.androidcourseproject.Adapters.RawNewsAdapter;
import com.psx.androidcourseproject.Adapters.RawPhotosAdapter;
import com.psx.androidcourseproject.Adapters.RawVideosAdapter;
import com.psx.androidcourseproject.Helper.AppController;
import com.psx.androidcourseproject.R;
import com.psx.androidcourseproject.model.RawNews;
import com.psx.androidcourseproject.model.RawPhotos;
import com.psx.androidcourseproject.model.RawVideos;
import com.psx.androidcourseproject.model.VideoCard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RAW extends Fragment {

    private AppCompatActivity appCompatActivity;
    private ActionBar actionBar;
    private RecyclerView recyclerView_videos, recyclerView_news, recyclerView_photos;
   // private String [] video_titles = {"Video 1","Video 2","Video 3","Video 4","Video 5","Video 6"};
    private JSONArray jsonArray;
    private JSONObject jsonObject;
    private RawVideos rawVideo;
    private Context context;
    private List<RawVideos> rawVideosList;
    private List<RawNews> rawNewsList;
    private List<RawPhotos> rawPhotosList;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RequestQueue requestQueue;
    public static final String url = "https://www.googleapis.com/youtube/v3/search?key=AIzaSyCzQJQPSPIqnou4AwreMLlch1cr4SHf1qw&channelId=UCJ5v_MCY6GNUBTO8-D3XoAg&part=snippet&maxResults=5";
    private RawVideosAdapter rawVideosAdapter;
    private RawNewsAdapter rawNewsAdapter;
    private RawPhotosAdapter rawPhotosAdapter;
    private ImageView imageView_error;
    private TextView error_message_video;
    LinearLayoutManager linearLayoutManager;

    public RAW() {
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
        return inflater.inflate(R.layout.fragment_raw, container, false);
    }

    public void generateSampleNews (){
        if (rawNewsList == null){
            rawNewsList = new ArrayList<>();
        }
        for (int i = 0 ;i<2;i++){
            rawNewsList.add(new RawNews("WWE RAW: MARCH 6, 2017","March 6, 2017","Goldberg Raises the stake for anticipated WrestleMaina clash with Brock Lesner\n Goldberg claerly didnt needed any help defeating the universal champion Kevin Owenns last night at WWE Fastlane"));
            rawNewsList.add(new RawNews("RUSEV REVEALS NEW LOOK ON INSTAGRAM","March 1, 2017","There will be Something very different about Rusev's hair the next time you see him on RAW"));
        }
    }

    public void generateSamplePhotos ()
    {
        if (rawPhotosList == null){
            rawPhotosList = new ArrayList<>();
        }
        rawPhotosList.add(new RawPhotos(R.drawable.pic1));
        rawPhotosList.add(new RawPhotos(R.drawable.pic2));
        rawPhotosList.add(new RawPhotos(R.drawable.pic3));
        rawPhotosList.add(new RawPhotos(R.drawable.pic4));
        rawPhotosList.add(new RawPhotos(R.drawable.pic5));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        appCompatActivity = (AppCompatActivity) getActivity();
        context = appCompatActivity.getApplicationContext();
        actionBar = appCompatActivity.getSupportActionBar();
        actionBar.setLogo(R.mipmap.ic_wwe_network);
        rawVideosList = new ArrayList<>();
        imageView_error = (ImageView) appCompatActivity.findViewById(R.id.error_videos);
        error_message_video = (TextView) appCompatActivity.findViewById(R.id.error_videos_message);
        requestQueue = Volley.newRequestQueue(context);
        swipeRefreshLayout = (SwipeRefreshLayout) appCompatActivity.findViewById(R.id.swipe_refresh_view_raw_videos);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // update teh recyclerview
                Toast.makeText(context,"Check",Toast.LENGTH_SHORT).show();
                refreshContent();
                Log.d("REFRESH","REFRESH Finished");
            }
        });
       // new JSONFetch().execute();

        // finding the recycler view for videos
        recyclerView_videos = (RecyclerView) appCompatActivity.findViewById(R.id.raw_videos_rv);
        recyclerView_news = (RecyclerView) appCompatActivity.findViewById(R.id.raw_news_rv);
        recyclerView_photos = (RecyclerView) appCompatActivity.findViewById(R.id.raw_photos_rv);
        linearLayoutManager = new LinearLayoutManager(context);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(context);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(context);
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView_videos.setLayoutManager(linearLayoutManager);
        rawVideosAdapter = new RawVideosAdapter(context,rawVideosList);
        recyclerView_videos.setAdapter(rawVideosAdapter);
       /* if (rawVideosList.size()>0){
            recyclerView_videos.setVisibility(View.VISIBLE);
            imageView_error.setVisibility(View.INVISIBLE);
            error_message_video.setVisibility(View.INVISIBLE);
        }
        else {
            recyclerView_videos.setVisibility(View.INVISIBLE);
            imageView_error.setVisibility(View.VISIBLE);
            error_message_video.setVisibility(View.VISIBLE);
        }*/
        imageView_error.setVisibility(View.VISIBLE);
        error_message_video.setVisibility(View.VISIBLE);
        // add the news adapter
        recyclerView_news.setLayoutManager(linearLayoutManager1);
        generateSampleNews();
        rawNewsAdapter = new RawNewsAdapter(rawNewsList,context);
        recyclerView_news.setAdapter(rawNewsAdapter);
        recyclerView_photos.setLayoutManager(linearLayoutManager2);
        generateSamplePhotos();
        rawPhotosAdapter = new RawPhotosAdapter(rawPhotosList,context);
        recyclerView_photos.setAdapter(rawPhotosAdapter);
    }

    public void refreshContent (){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rawVideosList.clear();
                populateCards();
                //cards.clear();
                // makeJSONArrayRequest();
                rawVideosAdapter = new RawVideosAdapter(context,rawVideosList);
                recyclerView_videos.setAdapter(rawVideosAdapter);
                swipeRefreshLayout.setRefreshing(false);
            }
        },4000);
    }

    public void populateCards (){
        new JSONFetch().execute();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        actionBar.setLogo(R.mipmap.ic_logo);
    }

    public void makeJSONArrayRequest (){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    jsonArray = response.getJSONArray("items");
                    if (jsonArray.length() == 0){
                        Log.d("ERROR","List is empty");
                    }
                    else {
                        for (int i =0;i<jsonArray.length();i++){
                            jsonObject = jsonArray.getJSONObject(i);
                            rawVideo = new RawVideos(jsonObject.getJSONObject("snippet").getString("title"),jsonObject.getJSONObject("id").getString("videoId"));
                            rawVideosList.add(rawVideo);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Some Volley Error has Occoured. Please try again.",Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }


    public class JSONFetch extends AsyncTask<Void,Void,Void> {
        private ProgressDialog progressDialog = new ProgressDialog(appCompatActivity);

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d("RAW","Tasks running in background");
            makeJSONArrayRequest();
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d("LeftFragment","preexecute");
            //progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Fetching the latest videos");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.d("RAW","postexecute");
            if (rawVideosList!=null){
                Log.d("RAW","videos not null");
                recyclerView_videos.setVisibility(View.VISIBLE);
                imageView_error.setVisibility(View.INVISIBLE);
                error_message_video.setVisibility(View.INVISIBLE);
                recyclerView_videos.setLayoutManager(linearLayoutManager);
                rawVideosAdapter = new RawVideosAdapter(context,rawVideosList);
                recyclerView_videos.setAdapter(rawVideosAdapter);
                //rawVideosAdapter.notifyDataSetChanged();
            }
            else {
                Log.d("RAW","cards were null");
                recyclerView_videos.setVisibility(View.INVISIBLE);
                imageView_error.setVisibility(View.VISIBLE);
                error_message_video.setVisibility(View.VISIBLE);
                //populateCards();
                //newVideosAdapter = new NewVideosAdapter(videoCards,context);
                //recyclerView.setAdapter(newVideosAdapter);
            }
            progressDialog.dismiss();
        }
    }

}
