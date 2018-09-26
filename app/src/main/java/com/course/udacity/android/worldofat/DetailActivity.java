package com.course.udacity.android.worldofat;

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
import android.widget.SearchView;

import com.course.udacity.android.worldofat.Fragment.AtuCertificateFragment;
import com.course.udacity.android.worldofat.Fragment.AtuFragment;
import com.course.udacity.android.worldofat.Fragment.AtuPersonnelFragment;
import com.course.udacity.android.worldofat.Fragment.BaseContainerFragment;

public class DetailActivity extends AppCompatActivity implements AtuFragment.OnFragmentInteractionListener, AtuPersonnelFragment.OnFragmentInteractionListener,
        AtuCertificateFragment.OnFragmentInteractionListener, BlankFragment.OnFragmentInteractionListener {

    private TabLayout mTabLayout;
    private static final String TAG = DetailActivity.class.getSimpleName();
    private SearchView searchView;
    private static BaseContainerFragment sBaseContainerFragment = new BaseContainerFragment();
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


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

