package com.course.udacity.android.worldofat.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.course.udacity.android.worldofat.Jobs;
import com.course.udacity.android.worldofat.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AtuCertificateFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 *
 * Use the {@link AtuCertificateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AtuCertificateFragment extends Fragment {
    private TextView mTextView;
    private TextView mLocationTextView;
    private OnFragmentInteractionListener mListener;


    public static final String FIREBASE_PRIMARY_CHILD_NODE = "jobsearch";

    public AtuCertificateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AtuCertificateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AtuCertificateFragment newInstance(String param1, String param2) {
        AtuCertificateFragment fragment = new AtuCertificateFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//         Trying to use the jrogen library to generate random objects.
//        mJobsArrayList.add(MyClass.getRandomJobsObject());
//        Log.i("TAG", mJobsArrayList.get(0).getJobName()+"");

//         Another approach that I tried to do was using it this way, this approach, I tried using by importing the library into the app module,
//         this also threw a Configuration Exception.
//         Error log says its unable to find file at src/ pathName. // Java.io.FileNotFoundException
//        try {
//            Iterator<com.course.udacity.android.worldofat.JobGenerator> iterator = JROFactory.create(com.course.udacity.android.worldofat.JobGenerator.class).iterator();
//            while (iterator.hasNext()){
//                Jobs j = iterator.next().getJobs();
//                Log.i("TAG",j.getJobId()+"");
////
//////             Trying to write the random objects to a Firebase Database and retrieve later to display to user.
//            }
//
//        }catch (ConfigurationException exception){
//
//        }





    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_atu_certificate,
                container, false);
        mTextView = v.findViewById(R.id.jobs_disp_tv);
        mLocationTextView = v.findViewById(R.id.textView);

        DatabaseReference mRef = FirebaseDatabase.getInstance()
                .getReference("wordlofat-35400").child(FIREBASE_PRIMARY_CHILD_NODE);

        mRef.push().setValue(new Jobs("Occupational Therapist", 3,"NewYork"));

        Query query = FirebaseDatabase.getInstance().getReference("wordlofat-35400").child(FIREBASE_PRIMARY_CHILD_NODE)
                .orderByChild("jobName").equalTo("Occupational Therapist");

        Toast.makeText(getActivity(), query.toString(), Toast.LENGTH_LONG).show();

//        query.addListenerForSingleValueEvent(valueEventListener);

        return v;
    }



    @Override
    public void onStart() {
        super.onStart();

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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
