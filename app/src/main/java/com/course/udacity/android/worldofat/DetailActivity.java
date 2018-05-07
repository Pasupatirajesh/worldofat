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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.course.udacity.android.worldofat.Fragment.AtuCertificateFragment;
import com.course.udacity.android.worldofat.Fragment.AtuFragment;
import com.course.udacity.android.worldofat.Fragment.AtuPersonnelFragment;

public class DetailActivity extends AppCompatActivity implements AtuFragment.OnFragmentInteractionListener, AtuPersonnelFragment.OnFragmentInteractionListener,
        AtuCertificateFragment.OnFragmentInteractionListener {


    private TabLayout mTabLayout;
    private SearchView searchView;

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

//        Intent intent = getIntent();
//        if(Intent.ACTION_SEARCH.equals(intent.getAction())){
//            String query = intent.getStringExtra(SearchManager.QUERY);
//            queryJob(query);
//        }
    }

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
//                            queryJob(query);
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

//    private void queryJob(String s) {
//        String key = FirebaseDatabase.getInstance().getReference().getKey();
//        Query jobq = FirebaseDatabase.getInstance().getReference().child("name").endAt(s, "LAv2RMSkoWXb2KY6gEL");
//
//       jobq.addListenerForSingleValueEvent(new ValueEventListener() {
//           @Override
//           public void onDataChange(DataSnapshot dataSnapshot) {
//               for(DataSnapshot dataSnap : dataSnapshot.getChildren()) {
//
//                   Jobs answer = dataSnap.getValue(Jobs.class);
//                   Log.i("ANSWER", answer.getJobName());
//                   Toast.makeText(getApplicationContext(), answer.getJobName(), Toast.LENGTH_SHORT).show();
//               }
//               }
//
//           @Override
//           public void onCancelled(DatabaseError databaseError) {
//
//           }
//
//       });
//    }

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



