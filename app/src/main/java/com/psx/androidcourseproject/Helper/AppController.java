package com.psx.androidcourseproject.Helper;

import android.app.Application;
import android.nfc.Tag;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import static com.android.volley.VolleyLog.TAG;

/**
 * Created by Pranav on 03-03-2017.
 */

public class AppController extends Application {
    private RequestQueue requestQueue;
    private static AppController mInstance;
    public static final String TAG = AppController.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized AppController getmInstance (){
        return mInstance;
    }

    public RequestQueue getRequestQueue ()
    {
        if (this.requestQueue == null){
            this.requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return this.requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }
}
