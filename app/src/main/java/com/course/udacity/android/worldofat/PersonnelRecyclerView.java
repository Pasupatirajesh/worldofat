package com.course.udacity.android.worldofat;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.course.udacity.android.worldofat.Databases.AtuPersonnelContract;
import com.course.udacity.android.worldofat.Databases.AtuPersonnelHelper;
import com.course.udacity.android.worldofat.Model.PersonnelModel;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class PersonnelRecyclerView extends RecyclerView.Adapter<PersonnelRecyclerView.PersonnelItemHolder> {

    private Context mContext;
    private PersonnelModel mPersonnelModel;
    private String[] personnelName;
    private String[] personnelEmail;
    private String[] imageString;
    private Cursor mCursor;

    private CardView mCardView;
    int lastPosition = -1;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PersonnelRecyclerView(Context context) throws IOException {

        this.mContext = context;
        AtuPersonnelHelper mAtuPersonnelHelper = new AtuPersonnelHelper(mContext);

        SQLiteDatabase mDb = mAtuPersonnelHelper.getWritableDatabase();

        ContentValues mContentValues = new ContentValues();

        imageString = context!=null ? context.getAssets().list("pictures"): new String[0];

        try{

            mPersonnelModel = new PersonnelModel(context);

            personnelName = mPersonnelModel.getName();
            personnelEmail = mPersonnelModel.getEmail();

            for(int i=0; i<7; i++){
                mContentValues.put(AtuPersonnelContract.AtuPersonnelEntry.PERSON_NAME, personnelName[i]);
                mContentValues.put(AtuPersonnelContract.AtuPersonnelEntry.PERSON_EMAIL,personnelEmail[i]);
                mContentValues.put(AtuPersonnelContract.AtuPersonnelEntry.PERSON_IMAGE, imageString[i]);

                mDb.insert(AtuPersonnelContract.AtuPersonnelEntry.TABLE_NAME, null, mContentValues);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        mCursor = mDb.query(AtuPersonnelContract.AtuPersonnelEntry.TABLE_NAME,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null);


    }

    @Override
    public PersonnelItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.personnel_layout, parent,false);
        return new PersonnelItemHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(PersonnelItemHolder holder, int position) {



        mCursor.moveToFirst();

                mCursor.moveToPosition(position);
                    holder.mNameTextView.setText("XYZ");
                    holder.mEmailTextView.setText("xyz@example.com");

                    Picasso.get().load(R.mipmap.coll_logo).into(holder.mImageView);


                    setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    private void setAnimation(View viewToAnimate, int pos){

        if(pos > lastPosition){
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = pos;
        }
    }


    public class PersonnelItemHolder extends RecyclerView.ViewHolder {

        TextView mNameTextView;
        TextView mEmailTextView;
        ImageView mImageView;

        public PersonnelItemHolder(View itemView) {
            super(itemView);
            mNameTextView = itemView.findViewById(R.id.personnel_name);
            mEmailTextView = itemView.findViewById(R.id.personnel_email);
            mImageView = itemView.findViewById(R.id.personnel_image);

            mCardView =itemView.findViewById(R.id.personnel_cardview);

            mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "HI", Toast.LENGTH_LONG).show();
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setType("text/plain");

                    if (emailIntent.resolveActivity(mContext.getPackageManager()) != null) {
                        mContext.startActivity(emailIntent);
                    }
                }
            });
        }


    }
}
