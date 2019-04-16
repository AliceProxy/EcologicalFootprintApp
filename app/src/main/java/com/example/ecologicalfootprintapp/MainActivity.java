package com.example.ecologicalfootprintapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

    private DrawerLayout navDrawer;
    private ActionBarDrawerToggle navToggle;
    private ActionBar actionBar;

    private SectionsStatePagerAdapter mSectionsStatePagerAdapter;
    private ViewPager mViewPager;

    public Questionaire questionaire;

    public ProgressDialog progressDiag;
    public TextView resultText;

    private static final String TAG = "MainActivity";

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
        navDrawer.setBackgroundColor(this.getResources().getColor(R.color.colorPrimary));
        navToggle.syncState();
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(this.getResources().getColor(R.color.colorPrimaryDark)));
        actionBar.setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        //setting up questionaire variable
        Log.d(TAG, "questionaire completed");
        questionaire = new Questionaire(this);



        Log.d(TAG, "questionaire completed: "+(questionaire.getCompleted()+""));
        Log.d(TAG, "questionaire completed");

        FetchAPI();

    }


    private void setupViewPager(ViewPager viewPager)
    {
        SectionsStatePagerAdapter adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new FragmentHome(), "FragmentHome"); // 0
        adapter.addFragment(new FragmentScoreBreakdown(), "FragmentBreakdown"); // 1
        adapter.addFragment(new FragmentScoreTracking(), "FragmentTracking"); // 2
        adapter.addFragment(new FragmentReduceImpact(), "FragmentImpact"); // 3
        adapter.addFragment(new FragmentCorvallisResources(), "FragmentCorvallis"); // 4
        adapter.addFragment(new FragmentAbout(), "FragmentAbout"); // 5
        adapter.addFragment(new FragmentQuestionaire1(), "FragmentQ1"); // 6
        adapter.addFragment(new FragmentQuestionaire2(), "FragmentQ2"); // 7
        adapter.addFragment(new FragmentQuestionaire3(), "FragmentQ3"); // 8
        adapter.addFragment(new FragmentQuestionaire4(), "FragmentQ4"); // 9
        adapter.addFragment(new FragmentQuestionaire5(), "FragmentQ5"); // 10
        adapter.addFragment(new FragmentQuestionaire6(), "FragmentQ6"); // 11
        adapter.addFragment(new FragmentQuestionaire7(), "FragmentQ7"); // 12
        adapter.addFragment(new FragmentQuestionaire8(), "FragmentQ8"); // 13
        adapter.addFragment(new FragmentQuestionaire9(), "FragmentQ9"); // 14
        adapter.addFragment(new FragmentQuestionaire10(), "FragmentQ10"); // 15
        adapter.addFragment(new FragmentQuestionaire11(), "FragmentQ11"); // 16
        adapter.addFragment(new FragmentQuestionaire12(), "FragmentQ12"); // 17
        adapter.addFragment(new FragmentQuestionaire13(), "FragmentQ13"); // 18
        adapter.addFragment(new FragmentQuestionaire14(), "FragmentQ14"); // 19
        adapter.addFragment(new FragmentQuestionaire15(), "FragmentQ15"); // 20
        adapter.addFragment(new FragmentQuestionResDuplicate(), "FragmentResDuplicate"); // 21
        adapter.addFragment(new FragmentQuestionRes(), "FragmentRes"); // 22
        adapter.addFragment(new FragmentCorvallisResourcesWater(), "CorvallisWater"); // 23
        adapter.addFragment(new FragmentCorvallisResourcesEnergy(), "CorvallisEnergy"); // 24
        adapter.addFragment(new FragmentCorvallisAgriculture(), "CorvallisAgriculture"); // 25
        adapter.addFragment(new FragmentCorvallisResourcesRecycling(), "CorvallisRecycling"); // 26

        // copy and repeat this line for all fragments, first fragment will be loaded at start
        viewPager.setAdapter(adapter);
    }

    // sets the current fragment within the view pager
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

    //handles what happens when a navigation item is pressed
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
    {
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

    public void FetchAPI()
    {
        resultText = new TextView(this);
        progressDiag = new ProgressDialog(this);
        new ApiJson(progressDiag, resultText).execute("http://api.footprintnetwork.org/v1/data/177/2014");
    }
}


