package com.psx.androidcourseproject.Helper;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.psx.androidcourseproject.R;

import java.util.ArrayList;
import java.util.List;

public class DisplayPhotosActivity extends AppCompatActivity implements View.OnClickListener{

    boolean isFullScreen = false;
    private ActionBar actionBar;
    ImageView imageView1, imageView2, imageView3, imageView4, imageView5, imageView6;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_photos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageView1 = (ImageView) findViewById(R.id.pic_raw_1);
        imageView2 = (ImageView) findViewById(R.id.pic_raw_2);
        imageView3 = (ImageView) findViewById(R.id.pic_raw_3);
        imageView4 = (ImageView) findViewById(R.id.pic_raw_4);
        imageView5 = (ImageView) findViewById(R.id.pic_raw_5);
        imageView6 = (ImageView) findViewById(R.id.pic_raw_6);
        imageView6.setOnClickListener(this);
        imageView5.setOnClickListener(this);
        imageView4.setOnClickListener(this);
        imageView3.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView1.setOnClickListener(this);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.md_grey_700));
        actionBar = getSupportActionBar();
        actionBar.setTitle("Photo Gallery");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.mipmap.ic_wwe_network);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }

    @Override
    public void onClick(View view) {
        ImageView iv = (ImageView)view;
        Intent intent = new Intent(this,DisplayEnlargedPic.class);
        intent.putExtra("imageid",iv.getId());
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home :
                // end this activity
                finish();
                break;
            default:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
