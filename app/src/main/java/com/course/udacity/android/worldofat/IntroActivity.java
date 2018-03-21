package com.course.udacity.android.worldofat;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
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

import com.course.udacity.android.worldofat.Model.GettyImageModel;
import com.course.udacity.android.worldofat.Networking.Controller;

public class IntroActivity extends AppCompatActivity implements PictureFragment.OnFragmentInteractionListener {

    private static final String TAG = IntroActivity.class.getSimpleName() ;
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private static GettyImageModel mGettyImageModel;
    static Parcelable myParcel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_pic_frag);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Controller controller = new Controller(getApplicationContext());

                controller.start();


                controller.setDataInterfaceListener(new Controller.DataInterface() {
                    @Override
                    public void responseData(GettyImageModel gettyImageModel) {
                        Log.i(TAG,gettyImageModel.getResultCount()+"");
                    }
                });




            }
        });


        mViewPager = findViewById(R.id.frag_viewpager);
        mPagerAdapter = new PhotoSlideFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
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

//        private final Bundle fragmentBundle;

        public PhotoSlideFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
//            this.fragmentBundle = fragmentBundle;
        }

        @Override
        public Fragment getItem(int position) {
            PictureFragment f = new PictureFragment();
//            f.setArguments();
            return f;
        }

        @Override
        public int getCount() {

                return 20;
        }
    }
}
