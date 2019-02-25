package com.course.udacity.android.worldofat.Activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.course.udacity.android.worldofat.Fragment.AtuJobSearchFragment;
import com.course.udacity.android.worldofat.Fragment.AtuFragment;
import com.course.udacity.android.worldofat.Fragment.AtuPersonnelFragment;
import com.course.udacity.android.worldofat.Fragment.BlankFragment;
import com.course.udacity.android.worldofat.Fragment.JobApplyWindowFragment;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.course.udacity.android.worldofat.Fragment.AtuCertificateFragment;
import com.course.udacity.android.worldofat.Fragment.AtuFragment;
import com.course.udacity.android.worldofat.Fragment.AtuPersonnelFragment;
import com.course.udacity.android.worldofat.Fragment.BlankFragment;

import com.course.udacity.android.worldofat.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import com.google.android.material.tabs.TabLayout;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;


import static com.course.udacity.android.worldofat.App.getContext;

public class DetailActivity extends AppCompatActivity implements AtuFragment.OnFragmentInteractionListener, AtuPersonnelFragment.OnFragmentInteractionListener,
        AtuJobSearchFragment.OnFragmentInteractionListener, BlankFragment.OnFragmentInteractionListener, JobApplyWindowFragment.OnApplyCompletedListener {

    private TabLayout mTabLayout;
    DetailActivity detailActivity;
    private AdView adView;


    private static final String AD_MOB_APPID = "ca-app-pub-3940256099942544~3347511713";


    private static final String TAG = DetailActivity.class.getSimpleName();
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);


        mTabLayout = findViewById(R.id.detail_view_tablayout);

        mTabLayout.addTab(mTabLayout.newTab().setText(getString(com.course.udacity.android.worldofat.R.string.atu_name)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(com.course.udacity.android.worldofat.R.string.atu_personnel_string)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(com.course.udacity.android.worldofat.R.string.atu_job_search)));



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

    @Override
    public Activity onApplyCompleted() {

        return this;


    }

    public class PagerAdapter extends FragmentStatePagerAdapter {

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


                case 2: return new AtuJobSearchFragment();

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

