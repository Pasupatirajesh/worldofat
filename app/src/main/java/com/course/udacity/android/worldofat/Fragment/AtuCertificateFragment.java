package com.course.udacity.android.worldofat.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.course.udacity.android.worldofat.Jobs;
import com.course.udacity.android.worldofat.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

//import com.course.udacity.android.worldofat.CertificateAdapter;

public class AtuCertificateFragment extends Fragment{
    private static final String TAG = AtuCertificateFragment.class.getSimpleName() ;

    private OnFragmentInteractionListener mListener;

    private android.widget.SearchView searchView;

    private static RecyclerView mRecyclerView;

    private static FirebaseRecyclerAdapter mCertificateAdapter;

    private static String searchString;

    private static TextView mEmptyView;

    private static CharSequence sCharSequence;

    public static final String FIREBASE_PRIMARY_CHILD_NODE = "jobsearch";


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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        assert getArguments() != null;
        Bundle bundle = getArguments();

        searchString = bundle.getString("JobSearch");
        setHasOptionsMenu(true);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        DatabaseReference mRef = FirebaseDatabase.getInstance()
                .getReference("wordlofat-35400").child(FIREBASE_PRIMARY_CHILD_NODE);

        mRef.push().setValue(new Jobs("Occupational Therapist", 3,"NewYork"));

        // Inflate the layout for this fragment
        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_atu_certificate,
                container, false);

        mRecyclerView = v.findViewById(R.id.certificate_rv);

        mEmptyView = v.findViewById(R.id.empty_tv);

        if(searchString == null) {
            mEmptyView.setVisibility(View.VISIBLE);
        }else {

            mEmptyView.setVisibility(View.INVISIBLE);

            mRecyclerView.setHasFixedSize(true);

            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            Query iquery = FirebaseDatabase.getInstance().getReference("wordlofat-35400").child(FIREBASE_PRIMARY_CHILD_NODE)
                    .orderByChild("jobName").equalTo(searchString);

            FirebaseRecyclerOptions<Jobs> options = new FirebaseRecyclerOptions.Builder<Jobs>()
                    .setQuery(iquery, Jobs.class).build();


            mCertificateAdapter = new FirebaseRecyclerAdapter<Jobs, CertificateViewHolder>(options) {

                @NonNull
                @Override
                public CertificateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View v = LayoutInflater.from(getContext()).inflate(R.layout.certifcate_adapter, parent, false);

                    return new CertificateViewHolder(v);
                }

                @Override
                protected void onBindViewHolder(@NonNull CertificateViewHolder holder, int position, @NonNull Jobs model) {
                    holder.mJobName.setText(model.getJobName());
                    holder.mJobLoction.setText(model.getLocation());
                }
            };

            mRecyclerView.setAdapter(mCertificateAdapter);



        }

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }

    @Override
    public void onStart() {
        super.onStart();
//        mCertificateAdapter.startListening();

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
//        mListener = null;
    }


    public class CertificateViewHolder extends RecyclerView.ViewHolder{

        private TextView mJobName;
        private TextView mJobLoction;

        public CertificateViewHolder(View itemView) {
            super(itemView);
            mJobName = itemView.findViewById(R.id.jobname_tv);
            mJobLoction = itemView.findViewById(R.id.jobloc_tv);
        }
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);

    }

    @Override
    public void onStop() {
        super.onStop();
//        mCertificateAdapter.stopListening();

    }
}
