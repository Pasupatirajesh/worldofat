package com.course.udacity.android.worldofat.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.course.udacity.android.worldofat.R;

public class JobApplyWindowFragment extends DialogFragment  {

    private TextView mJobNameDf;

    private TextView mJobDesDf;

    public JobApplyWindowFragment() {

    }

    public interface OnApplyCompletedListener{

        void onApplyCompleted(String inputString);

    }

    public static JobApplyWindowFragment newInstance (String title){
        JobApplyWindowFragment frag = new JobApplyWindowFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.dialog_frag_apply_window, container);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        AlertDialog.Builder alrtdb = new AlertDialog.Builder(getContext());
        String title = getArguments().getString("title");
        alrtdb.setTitle(title);
        alrtdb.setMessage("Apply to this job by emailing your resume");
        alrtdb.setPositiveButton("Apply", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                OnApplyCompletedListener listener = (OnApplyCompletedListener) getTargetFragment();
                listener.onApplyCompleted("hi");
                dismiss();

            }
        });

        alrtdb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(dialog !=null) {
                    dialog.dismiss();
                }
            }



        });

        return alrtdb.create();
    }

    public void sendBackResult(){

        OnApplyCompletedListener listener = (OnApplyCompletedListener) getTargetFragment();
        listener.onApplyCompleted("hi");
        dismiss();


    }
}
