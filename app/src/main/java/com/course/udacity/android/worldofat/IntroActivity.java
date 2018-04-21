package com.course.udacity.android.worldofat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.course.udacity.android.worldofat.Model.GettyImageModel;
import com.course.udacity.android.worldofat.Networking.Controller;
import com.course.udacity.android.worldofat.Fragment.PictureFragment;

import java.util.ArrayList;

public class IntroActivity extends AppCompatActivity implements PictureFragment.OnFragmentInteractionListener {

    private static final String TAG = IntroActivity.class.getSimpleName() ;
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private static String uri="";
    private static ArrayList<String> pictureUri = new ArrayList<>();
    private static int page=0;
    private Handler mHandler;

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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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

        Button mDetailButton = findViewById(R.id.detail_view_button);
        mDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myActivity = new Intent(IntroActivity.this, DetailActivity.class);
                startActivity(myActivity);

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public class PhotoSlideFragmentPagerAdapter extends FragmentStatePagerAdapter{
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
