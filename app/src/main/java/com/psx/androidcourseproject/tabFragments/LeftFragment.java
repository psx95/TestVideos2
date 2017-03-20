package com.psx.androidcourseproject.tabFragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.psx.androidcourseproject.Adapters.NewVideosAdapter;
import com.psx.androidcourseproject.Config;
import com.psx.androidcourseproject.Helper.AppController;
import com.psx.androidcourseproject.R;
import com.psx.androidcourseproject.model.VideoCard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LeftFragment extends Fragment {

    private AppCompatActivity appCompatActivity;
    private RecyclerView recyclerView;
    private NewVideosAdapter newVideosAdapter;
    private List<VideoCard> videoCards = new ArrayList<>();
    private List<VideoCard> cards = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private JSONArray jsonArray;
    private JSONObject jsonObject;
    private VideoCard videoCard;
    private Context context;
    private Button button;
    private RequestQueue requestQueue ;
    // for commit
    public static final String url = "https://www.googleapis.com/youtube/v3/search?key=AIzaSyCzQJQPSPIqnou4AwreMLlch1cr4SHf1qw&channelId=UCJ5v_MCY6GNUBTO8-D3XoAg&part=snippet&maxResults=10";
    public LeftFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        appCompatActivity = (AppCompatActivity) getActivity();
        context = appCompatActivity.getApplicationContext();
        requestQueue = Volley.newRequestQueue(context);
        populateCards();
        button = (Button) appCompatActivity.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeJSONArrayRequest();
                for (int i = 0;i<cards.size();i++){
                    //Toast.makeText(context,cards.get(i).getVideo_code(),Toast.LENGTH_SHORT).show();
                    Log.d("LIST CODES",cards.get(i).getVideo_code());
                }
            }
        });
        swipeRefreshLayout = (SwipeRefreshLayout) appCompatActivity.findViewById(R.id.swipe_refresh_view);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // update teh recyclerview
                refreshContent();
                Log.d("REFRESH","REFRESH Finished");
            }
        });
        //makeJSONArrayRequest();
        new JSONFetch().execute();
        recyclerView = (RecyclerView) appCompatActivity.findViewById(R.id.recycler_new_videos);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        newVideosAdapter = new NewVideosAdapter(cards,getContext());
        recyclerView.setAdapter(newVideosAdapter);

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
                            videoCard = new VideoCard(jsonObject.getJSONObject("id").getString("videoId"));
                            videoCard.setVideo_title(jsonObject.getJSONObject("snippet").getString("title"));
                            cards.add(videoCard);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Some Error has Occoured. Please try again.",Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }


    public class JSONFetch extends AsyncTask <Void,Void,Void> {
        private ProgressDialog progressDialog = new ProgressDialog(appCompatActivity);

        @Override
        protected Void doInBackground(Void... voids) {
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
            Log.d("LeftFragment","postexecute");
            if (cards!=null){
                Log.d("LeftFragment","cards not null");
                newVideosAdapter = new NewVideosAdapter(cards,context);
                recyclerView.setAdapter(newVideosAdapter);
            }
            else {
                Log.d("LeftFragment","cards were null");
                populateCards();
                newVideosAdapter = new NewVideosAdapter(videoCards,context);
                recyclerView.setAdapter(newVideosAdapter);
            }
            progressDialog.dismiss();
        }
    }



    public void refreshContent (){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                videoCards.clear();
                populateCards();
                //cards.clear();
               // makeJSONArrayRequest();
                newVideosAdapter = new NewVideosAdapter(videoCards,getContext());
                recyclerView.setAdapter(newVideosAdapter);
                swipeRefreshLayout.setRefreshing(false);
            }
        },4000);
    }

    // function to populate the vide cards
    public void populateCards ()
    {
        /*for (int i = 0 ;i<3;i++){
            videoCards.add(new VideoCard());
        }*/
        new JSONFetch().execute();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_left, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
