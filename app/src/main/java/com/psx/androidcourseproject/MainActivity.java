package com.psx.androidcourseproject;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.psx.androidcourseproject.MenuFragments.Events;
import com.psx.androidcourseproject.MenuFragments.RAW;
import com.psx.androidcourseproject.MenuFragments.Shop;
import com.psx.androidcourseproject.MenuFragments.SmackDownLive;
import com.psx.androidcourseproject.MenuFragments.Superstars;
import com.psx.androidcourseproject.MenuFragments.WWENetwork;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    Fragment fragment = null;
    ActionBar actionBar;
    ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        //actionBar.setIcon(R.drawable.ic_logo_app);
        assert actionBar!=null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setLogo(R.mipmap.ic_logo);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        DisplayView(R.id.nav_home);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        DisplayView(id);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void DisplayView (int id){
        String title = "Home";
        switch (id) {
            case R.id.nav_home :
                // open the home Fragment
                fragment = new HomeFragment();
                title = "Home";
                break;
            case R.id.nav_raw :
                fragment = new RAW();
                title = "RAW";
                break;
            case R.id.nav_wwe_network :
                fragment = new WWENetwork();
                title = "WWE Network";
                break;
            case R.id.nav_shop :
                fragment = new Shop();
                title = "Shop";
                break;
            case R.id.nav_superstars :
                fragment = new Superstars();
                title = "Superstars";
                break;
            case R.id.nav_smackdown_live :
                fragment = new SmackDownLive();
                title = "SmackDown Live";
                break;
            case R.id.nav_events :
                fragment = new Events();
                title = "Upcoming Events";
                break;
        }
        if (fragment!=null){
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_frame,fragment);
            fragmentTransaction.commit();
        }

        if (getSupportActionBar()!=null)
        {
            getSupportActionBar().setTitle(title);
        }
    }
}
