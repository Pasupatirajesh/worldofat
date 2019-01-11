package com.course.udacity.android.worldofat;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
