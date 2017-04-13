package com.psx.androidcourseproject.Helper;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.psx.androidcourseproject.R;

public class DisplayNewsActivity extends AppCompatActivity {

    private Intent intent;
    private ActionBar actionBar;
    private TextView textView_news_title, textView_news_date, textView_news_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_news);
        intent = getIntent();
        actionBar = getSupportActionBar();
        actionBar.setTitle("RAW News");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.mipmap.ic_wwe_network);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        textView_news_content = (TextView) findViewById(R.id.news_content_news_display);
        textView_news_date = (TextView) findViewById(R.id.news_date_display_news);
        textView_news_title = (TextView) findViewById(R.id.news_heading_display_news);
        textView_news_title.setText(intent.getStringExtra("news_title"));
        textView_news_date.setText(intent.getStringExtra("news_date"));
        textView_news_content.setText(intent.getStringExtra("news_content"));
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
