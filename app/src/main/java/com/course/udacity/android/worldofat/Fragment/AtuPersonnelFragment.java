package com.course.udacity.android.worldofat.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.course.udacity.android.worldofat.Misc.PersonnelRecyclerView;
import com.course.udacity.android.worldofat.R;

import java.io.IOException;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
<<<<<<< HEAD
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
=======
>>>>>>> 65d0c9f279f6d809b3a05a10e1964cb0fdec4586


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AtuPersonnelFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AtuPersonnelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AtuPersonnelFragment extends BaseContainerFragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private PersonnelRecyclerView mPersonnelRecyclerView;

    private CardView mCardView;

    public AtuPersonnelFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AtuPersonnelFragment newInstance() {
        AtuPersonnelFragment fragment = new AtuPersonnelFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v =  inflater.inflate(R.layout.fragment_atu_personnel, container, false);

        mRecyclerView = v.findViewById(R.id.personnel_recycler_view);
        if(mPersonnelRecyclerView == null) {
            try {
                mPersonnelRecyclerView = new PersonnelRecyclerView(getContext());

<<<<<<< HEAD
=======
                //                mPersonnelRecyclerView.setItemAnimator(slideInUpAnimator);
>>>>>>> 65d0c9f279f6d809b3a05a10e1964cb0fdec4586
            } catch (IOException e) {
                e.printStackTrace();
            }
            mLayoutManager = new LinearLayoutManager(getContext());
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mPersonnelRecyclerView);
        }

        return v;

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