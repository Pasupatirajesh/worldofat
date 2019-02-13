package com.course.udacity.android.worldofat;

<<<<<<< HEAD
import android.app.Application;
import android.content.Context;
=======
>>>>>>> 65d0c9f279f6d809b3a05a10e1964cb0fdec4586
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

<<<<<<< HEAD
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.core.content.ContextCompat.getSystemService;
import static androidx.core.content.ContextCompat.startActivity;

public class CertificateViewHolder extends RecyclerView.ViewHolder {

    public TextView mJobName;
    public TextView mJobLoction;
    public Button mApplyButton;


    public CertificateViewHolder(View itemView) {
        super(itemView);
        mJobName = itemView.findViewById(R.id.jobname_tv);
        mJobLoction = itemView.findViewById(R.id.jobloc_tv);
        mApplyButton = itemView.findViewById(R.id.apply_button);
    }
}
=======
import androidx.recyclerview.widget.RecyclerView;

public class CertificateViewHolder extends RecyclerView.ViewHolder {

        public TextView mJobName;
        public TextView mJobLoction;
        public Button mApplyButton;


        public CertificateViewHolder(View itemView) {
            super(itemView);

            mJobName = itemView.findViewById(R.id.jobname_tv);
            mJobLoction = itemView.findViewById(R.id.jobloc_tv);
            mApplyButton = itemView.findViewById(R.id.apply_button);

            mApplyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent emailIntent = new Intent(Intent.ACTION_SEND);

                    emailIntent.setType("plain/text");

//                    .startActivity(Intent.createChooser(emailIntent, getResources().getText(R.string.send_to)));

                }
            });

        }
    }
>>>>>>> 65d0c9f279f6d809b3a05a10e1964cb0fdec4586
