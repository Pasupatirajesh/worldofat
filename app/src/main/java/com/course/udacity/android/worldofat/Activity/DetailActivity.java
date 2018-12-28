package com.course.udacity.android.worldofat.Activity;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.course.udacity.android.worldofat.Fragment.BlankFragment;
import com.course.udacity.android.worldofat.Fragment.AtuCertificateFragment;
import com.course.udacity.android.worldofat.Fragment.AtuFragment;
import com.course.udacity.android.worldofat.Fragment.AtuPersonnelFragment;
import com.course.udacity.android.worldofat.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class DetailActivity extends AppCompatActivity implements AtuFragment.OnFragmentInteractionListener, AtuPersonnelFragment.OnFragmentInteractionListener,
        AtuCertificateFragment.OnFragmentInteractionListener, BlankFragment.OnFragmentInteractionListener {

    private TabLayout mTabLayout;
    private static final String AD_MOB_APPID = "ca-app-pub-3940256099942544~3347511713";

    private AdView adView;
    private static final String TAG = DetailActivity.class.getSimpleName();
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        MobileAds.initialize(this,AD_MOB_APPID );

        adView =(AdView)findViewById(R.id.mobile_ad);

        AdRequest adRequest = new AdRequest.Builder().build();

        adView.loadAd(adRequest);


        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);


        mTabLayout = findViewById(R.id.detail_view_tablayout);
        mTabLayout.addTab(mTabLayout.newTab().setText("ATU"));
        mTabLayout.addTab(mTabLayout.newTab().setText("ATU PERSONNEL"));
        mTabLayout.addTab(mTabLayout.newTab().setText("ATU CERTIFICATE"));

        final ViewPager viewPager = findViewById(R.id.detailView_view_pager);
        final PagerAdapter mPagerAdapter = new PagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());
        viewPager.setAdapter(mPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }




    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public class PagerAdapter extends FragmentStatePagerAdapter{

        int numOfTabs;

        public PagerAdapter(FragmentManager fm, int tabNos){
            super(fm);
            this.numOfTabs = tabNos;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0: return new AtuFragment();

                case 1: return new AtuPersonnelFragment();

                case 2: return new AtuCertificateFragment();

                default:
                        return null;
            }
        }

        @Override
        public int getCount() {
            return numOfTabs;
        }
    }





}

