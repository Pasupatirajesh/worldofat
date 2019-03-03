package com.course.udacity.android.worldofat.Fragment;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.course.udacity.android.worldofat.CertificateViewHolder;
import com.course.udacity.android.worldofat.FirebaseWorkManager;
import com.course.udacity.android.worldofat.FirebaseWorker;
import com.course.udacity.android.worldofat.Misc.ActionModeImplementation;
import com.course.udacity.android.worldofat.Misc.RecyclerTouchListener;
import com.course.udacity.android.worldofat.Model.Jobs;
import com.course.udacity.android.worldofat.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.infoedge.android.arandomizer.DroidGenerator;

import java.util.ArrayList;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.Data;
import androidx.work.WorkInfo;


//import com.course.udacity.android.worldofat.CertificateAdapter;

public class AtuCertificateFragment extends BaseContainerFragment{

    private static final String TAG = AtuCertificateFragment.class.getSimpleName() ;

    private OnFragmentInteractionListener mListener;

    private RecyclerView mRecyclerView;

    private static FirebaseRecyclerAdapter mCertificateAdapter;

    private static String searchString;

    public static final String FIREBASE_PRIMARY_CHILD_NODE = "jobsearch";

    private SearchView mSearchView;

    private ProgressBar sProgressBar;

    private TextView mHintsTexView;

    private String outString="";

    private  FirebaseWorkManager mManager;

    public AtuCertificateFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static AtuCertificateFragment newInstance(String searchjobs) {

        Bundle args = new Bundle();
        args.putString("JobSearch", searchjobs);
        AtuCertificateFragment fragment = new AtuCertificateFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @RequiresApi(api = 28)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_atu_certificate,
                container, false);

        mHintsTexView = v.findViewById(R.id.job_hints_textview);

        mHintsTexView.setVisibility(View.VISIBLE);

        sProgressBar = v.findViewById(R.id.intermediate_progressBar);

        sProgressBar.setVisibility(ProgressBar.INVISIBLE);

        // Using the Droid generator library to generate random job names and locations
        DroidGenerator<Jobs> generator = new DroidGenerator<>(Jobs.class);

        ArrayList<Jobs> jobsList = (ArrayList<Jobs>) generator.generate(5);

        for(int i=0; i< jobsList.size(); i++) {

            Jobs j = jobsList.get(i);

            Log.i("JOBSHOOD", j.getJobName() + "");

            DatabaseReference mRef = FirebaseDatabase.getInstance()
                    .getReference("wordlofat-35400").child(FIREBASE_PRIMARY_CHILD_NODE);

            mRef.push().setValue(j);
        }

        return v;

    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mRecyclerView = view.findViewById(R.id.certificate_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));





        Query iquery = FirebaseDatabase.getInstance().getReference("wordlofat-35400").child(FIREBASE_PRIMARY_CHILD_NODE)
                .orderByChild("jobName").equalTo(searchString).limitToFirst(7);


            FirebaseRecyclerOptions<Jobs> options = new FirebaseRecyclerOptions.Builder<Jobs>()
                    .setQuery(iquery, Jobs.class).build();

            if (searchString == null) {
                mHintsTexView.setVisibility(TextView.VISIBLE);
            }
            if (searchString != null) {
                sProgressBar.setVisibility(ProgressBar.VISIBLE);



            mCertificateAdapter = new FirebaseRecyclerAdapter<Jobs, CertificateViewHolder>(options) {
                @NonNull
                @Override
                public CertificateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View v = LayoutInflater.from(getContext()).inflate(R.layout.certifcate_adapter, parent, false);

                    return new CertificateViewHolder(v);
                }

                @Override
                protected void onBindViewHolder(@NonNull CertificateViewHolder viewHolder, int i, @NonNull Jobs model) {
                    viewHolder.mJobName.setText(model.getJobName());
                    viewHolder.mJobLoction.setText(model.getLocation());

                }

                @Override
                public void onDataChanged() {
                    super.onDataChanged();
                    sProgressBar.setVisibility(ProgressBar.INVISIBLE);
                    mHintsTexView.setVisibility(TextView.INVISIBLE);
                }
            };
        } else {
            mHintsTexView.setText(R.string.no_jobs_feedback);
        }


        mRecyclerView.setAdapter(mCertificateAdapter);

//        JobSearchUtilities.scheduleJobSearchService(getContext());

        registerForContextMenu(mRecyclerView);

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(
                getContext(), mRecyclerView, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(View view, int position) {
                Log.i(TAG, "Click from onClick");
            }

            @Override
            public void onLongClick(final View view, final int pos) {

                ActionModeImplementation actionModeImplementation = new ActionModeImplementation(getContext());
                Objects.requireNonNull(getActivity()).startActionMode(actionModeImplementation);

                actionModeImplementation.setDataCopiedListener(new ActionModeImplementation.dataCopiedListener() {
                    @Override
                    public String onDataCopied() {
                        TextView jobNameText = Objects.requireNonNull(mRecyclerView.findViewHolderForAdapterPosition(pos)).itemView.findViewById(R.id.jobname_tv);
                        return jobNameText.getText().toString();
                    }
                });

                }

                }));

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_detail, menu);

        SearchManager searchManager = (SearchManager)Objects.requireNonNull(getActivity()).getSystemService(Context.SEARCH_SERVICE);

        mSearchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        assert searchManager != null;
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

        mSearchView.setQueryHint("Search Jobs");

        mSearchView.setIconifiedByDefault(false);

        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.action_search):


                    mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            searchString = query;
                            Toast.makeText(getContext(), searchString, Toast.LENGTH_LONG).show();


                            assert getFragmentManager() != null;
                            final FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            final Fragment newFragment = new AtuCertificateFragment();
                            Bundle args = new Bundle();
                            args.putString("query_string", searchString);
                            newFragment.setArguments(args);
                            transaction.replace(R.id.fragment_atu_certificate, newFragment);
                            transaction.addToBackStack(null);
                            transaction.commit();
                            InputMethodManager im =(InputMethodManager) Objects.requireNonNull(getContext()).getSystemService(Context.INPUT_METHOD_SERVICE);
                            assert im != null;
                            im.hideSoftInputFromWindow(mSearchView.getWindowToken(),0);
                            return true;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {

                            return false;
                        }
                    });

                    default:
                    break;
        }
        mSearchView.setFocusable(false);


        return super.onOptionsItemSelected(item);

    }
    @Override
    public void onStart() {
        super.onStart();
        mCertificateAdapter.startListening();

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onStop() {
        super.onStop();
        mCertificateAdapter.stopListening();

    }




    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);

    }


}
