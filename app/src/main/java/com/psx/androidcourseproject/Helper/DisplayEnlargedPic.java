package com.psx.androidcourseproject.Helper;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.psx.androidcourseproject.R;

public class DisplayEnlargedPic extends AppCompatActivity {

    ImageView imageView;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_enlarged_pic);
        imageView = (ImageView) findViewById(R.id.imageView8);
        Intent intent = getIntent();
        actionBar = getSupportActionBar();
        int s = intent.getIntExtra("imageid",R.id.pic_raw_1);
        switch (s){
            case R.id.pic_raw_1:
                imageView.setImageResource(R.drawable.pic1);
                break;
            case R.id.pic_raw_2:
                imageView.setImageResource(R.drawable.pic2);
                break;
            case R.id.pic_raw_3:
                imageView.setImageResource(R.drawable.pic3);
                break;
            case R.id.pic_raw_4:
                imageView.setImageResource(R.drawable.pic4);
                break;
            case R.id.pic_raw_5:
                imageView.setImageResource(R.drawable.pic5);
                break;
            case R.id.pic_raw_6:
                imageView.setImageResource(R.drawable.pic2);
                break;
        }
        actionBar.setTitle("View Photo");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setHomeButtonEnabled(true);
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
