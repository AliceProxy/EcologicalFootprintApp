package com.example.ecologicalfootprintapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

    private DrawerLayout navDrawer;
    private ActionBarDrawerToggle navToggle;
    private ActionBar actionBar;

    private SectionsStatePagerAdapter mSectionsStatePagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionsStatePagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.fragmentContainer);
        // setting up the pager
        setupViewPager(mViewPager);

        navDrawer = (DrawerLayout) findViewById(R.id.navDrawerLayout);
        navToggle = new ActionBarDrawerToggle(this, navDrawer, R.string.open, R.string.close);
        navDrawer.addDrawerListener(navToggle);
        navDrawer.setBackgroundColor(Color.parseColor("#008577"));
        navToggle.syncState();
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00574B")));
        actionBar.setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setupViewPager(ViewPager viewPager)
    {
        SectionsStatePagerAdapter adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentHome(), "FragmentHome");
        adapter.addFragment(new FragmentScoreBreakdown(), "FragmentBreakdown");
        adapter.addFragment(new FragmentScoreTracking(), "FragmentTracking");
        adapter.addFragment(new FragmentReduceImpact(), "FragmentImpact");
        adapter.addFragment(new FragmentCorvallisResources(), "FragmentCorvallis");
        adapter.addFragment(new FragmentAbout(), "FragmentAbout");
;

        // copy and repeat this line for all fragments, first fragment will be loaded at start
        viewPager.setAdapter(adapter);

    }

    public void setViewPager(int fragmentNumber)
    {
        mViewPager.setCurrentItem(fragmentNumber);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(navToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        Intent startNewActivity;

        if(id == R.id.home)
        {
            Toast.makeText(this,"Clicked Home", Toast.LENGTH_SHORT).show();
            mViewPager.setCurrentItem(0);
            navDrawer.closeDrawers();
        }
        if(id == R.id.scoreBreakdown)
        {
            Toast.makeText(this, "Clicked Score Breakdown", Toast.LENGTH_SHORT).show();
            mViewPager.setCurrentItem(1);
            navDrawer.closeDrawers();

        }
        if(id == R.id.scoreTracking)
        {
            Toast.makeText(this,"Clicked Score Tracking", Toast.LENGTH_SHORT).show();
            // for moving to a new activity
            //startNewActivity = new Intent(this, ScoreTracking.class);
            // startNewActivity.putExtra(EXTRA_MESSAGE, message);
            //startActivity(startNewActivity);
            mViewPager.setCurrentItem(2);
            navDrawer.closeDrawers();
        }
        if(id == R.id.reduceImpact)
        {
            Toast.makeText(this,"Clicked Reduce Impact", Toast.LENGTH_SHORT).show();
            mViewPager.setCurrentItem(3);
            navDrawer.closeDrawers();

        }
        if(id == R.id.corvallisResources)
        {
            Toast.makeText(this,"Clicked Corvallis Resources", Toast.LENGTH_SHORT).show();
            mViewPager.setCurrentItem(4);
            navDrawer.closeDrawers();

        }
        if(id == R.id.about)
        {
            Toast.makeText(this,"Clicked About", Toast.LENGTH_SHORT).show();
            mViewPager.setCurrentItem(5);
            navDrawer.closeDrawers();

        }

        return false;
    }
}
