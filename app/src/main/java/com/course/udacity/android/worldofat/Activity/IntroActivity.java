package com.course.udacity.android.worldofat.Activity;

import android.app.PictureInPictureParams;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.course.udacity.android.worldofat.Fragment.PictureFragment;
import com.course.udacity.android.worldofat.Model.GettyImageModel;
import com.course.udacity.android.worldofat.Networking.Controller;
import com.course.udacity.android.worldofat.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class IntroActivity extends AppCompatActivity implements PictureFragment.OnFragmentInteractionListener {

    private static final String TAG = IntroActivity.class.getSimpleName() ;
    private static final String AD_MOB_APPID = "YOUR AD ID";
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private static String uri="";
    private static ArrayList<String> pictureUri = new ArrayList<>();
    private static int page=0;
    private Handler mHandler;
    private ImageButton pipBtn;
    private  AdView adView;
    private DrawerLayout drawerLayout;


    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (getSupportFragmentManager().getFragments().size() == 0) {
                mHandler.removeCallbacks(mRunnable);
            } else {
                if (mPagerAdapter.getCount() == page) {
                    page = 0;
                } else {
                    page++;
                }
                mViewPager.setCurrentItem(page, true);
                mHandler.postDelayed(this, 5000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        MobileAds.initialize(this, AD_MOB_APPID);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_pic_frag);

        mHandler = new Handler();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Controller controller = new Controller(getApplicationContext());

                controller.start();
                controller.setDataInterfaceListener(new Controller.DataInterface() {
                    @Override
                    public void responseData(GettyImageModel gettyImageModel) {

                        Log.i(TAG, gettyImageModel.getResultCount() + "");
                        for (GettyImageModel.Image image : gettyImageModel.getImages()) {
                            for (int i=0; i < image.getDisplaySizes().size(); i++) {
                                pictureUri.add(image.getDisplaySizes().get(i).getUri());
                            }
                        }
                        mViewPager = findViewById(R.id.frag_viewpager);
                        mPagerAdapter = new PhotoSlideFragmentPagerAdapter(getSupportFragmentManager());
                        mViewPager.setAdapter(mPagerAdapter);
                        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                            }

                            @Override
                            public void onPageSelected(int position) {
                                page = position;
                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });
                    }
                });
            }
        });

        pipBtn = findViewById(R.id.btn_minimize);


        pipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT>= 26){
                    try{
                        PictureInPictureParams mParams =
                                new PictureInPictureParams.Builder()
                                .build();
                        enterPictureInPictureMode(mParams);

                    }catch (IllegalStateException ioe){
                        ioe.printStackTrace();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), com.course.udacity.android.worldofat.R.string.pip_not_supported_msg,Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Displaying Google's ad Mob ads

//        MobileAds.initialize(this, AD_MOB_APPID);

        adView =(AdView)this.findViewById(R.id.mobile_ad);

        boolean isTestDevice;


        AdRequest adRequest = new AdRequest.Builder().build();
        isTestDevice = adRequest.isTestDevice(this);
        Toast.makeText(this, isTestDevice+"", Toast.LENGTH_LONG).show();

        adView.loadAd(adRequest);



        Button mDetailButton = findViewById(R.id.detail_view_button);
        mDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myActivity = new Intent(IntroActivity.this, DetailActivity.class);
                startActivity(myActivity);
                }
        });


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        configureNavigationDrawer();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, Configuration newConfig) {

        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig);

        if(!isInPictureInPictureMode){
            pipBtn.setVisibility(View.VISIBLE);
        }else{
            pipBtn.setVisibility(View.GONE);
        }
    }

    private void configureNavigationDrawer() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.navigation);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                int itemId = menuItem.getItemId();
                if(itemId == R.id.nav_gallery){
                    Toast.makeText(IntroActivity.this, com.course.udacity.android.worldofat.R.string.hi_from_gallery, Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawers();

                } else if(itemId == R.id.camera){
                    Toast.makeText(IntroActivity.this, com.course.udacity.android.worldofat.R.string.hi_from_camera, Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawers();
                }

                return true;
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        mHandler.postDelayed(mRunnable, 5000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mRunnable);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_intro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       switch (id) {
           case android.R.id.home:

               drawerLayout.openDrawer(GravityCompat.START);
               return true;
       }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public class PhotoSlideFragmentPagerAdapter extends FragmentStatePagerAdapter {
        public PhotoSlideFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            uri = pictureUri.get(position);
            Log.i(TAG, uri);
            return PictureFragment.newInstance(uri);
        }

        @Override
        public int getCount() {
            return pictureUri.size();
        }
    }
}