package com.course.udacity.android.worldofat;

import android.app.SearchManager;
import android.content.Context;
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.course.udacity.android.worldofat.Fragment.AtuCertificateFragment;
import com.course.udacity.android.worldofat.Fragment.AtuFragment;
import com.course.udacity.android.worldofat.Fragment.AtuPersonnelFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.course.udacity.android.worldofat.Fragment.AtuCertificateFragment.FIREBASE_PRIMARY_CHILD_NODE;

public class DetailActivity extends AppCompatActivity implements AtuFragment.OnFragmentInteractionListener, AtuPersonnelFragment.OnFragmentInteractionListener,
        AtuCertificateFragment.OnFragmentInteractionListener {


    private TabLayout mTabLayout;
    private SearchView searchView;
    private ArrayList<com.course.udacity.android.worldofat.Jobs> mJobsArrayList = new ArrayList<>();
    private static final String TAG = DetailActivity.class.getSimpleName();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

//        Toolbar toolbar = findViewById(R.id.jobsearch_toolbar);
//        setActionBar(toolbar);

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



//        Intent intent = getIntent();
//        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
//            String query = intent.getStringExtra(SearchManager.QUERY);
//            queryJob(query);
//        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_detail, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.action_search):

                if (item.getItemId() == R.id.action_search) {

                    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {

                            queryJob(query);
                            return true;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                            return false;
                        }
                    });

                }
        }

        return super.onOptionsItemSelected(item);

    }

    private void queryJob(String s) {

        Query query = FirebaseDatabase.getInstance().getReference("wordlofat-35400").child(FIREBASE_PRIMARY_CHILD_NODE)
                .orderByChild("jobName").equalTo(s);

//        if(!query.getRef().child(FIREBASE_PRIMARY_CHILD_NODE).child("jobName").toString().equals(s)){
//
//            Log.i(TAG, "Search value doesn't return anything");
//        }else {
            query.addListenerForSingleValueEvent(valueEventListener);
//        }
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            mJobsArrayList.clear();
            if(dataSnapshot.exists()){
                for(DataSnapshot jobSnapshot : dataSnapshot.getChildren()){


                    com.course.udacity.android.worldofat.Jobs j = jobSnapshot.getValue(com.course.udacity.android.worldofat.Jobs.class);
                    mJobsArrayList.add(j);
                    Log.i("mJobsArrayList", mJobsArrayList.get(0).getJobName());

                    Toast.makeText(getApplicationContext(), mJobsArrayList.get(0).getJobName(), Toast.LENGTH_LONG).show();
                }

            }

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

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



