package com.course.udacity.android.worldofat.Misc;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

    private ClickListener mClickListener;
    private GestureDetector mGestureDetector;
    // interface to declare a ClickListener
    public static interface ClickListener{
        public void onClick(View view, int position);
        public void onLongClick(View view,int position);
    }

    // Constructor for the RecyclerTouchListener
    public RecyclerTouchListener(Context context, final RecyclerView rv, final ClickListener clickListener){

        this.mClickListener = clickListener;
        // Instantiate a GestureDetector object
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
//                super.onLongPress(e);
                // get the child view clicked by the user using the MotionEvent
                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if(child !=null && mClickListener !=null){
                    mClickListener.onLongClick(child, rv.getChildAdapterPosition(child));
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

        View child = rv.findChildViewUnder(e.getX(), e.getY());
        if(child!=null && mClickListener !=null && mGestureDetector.onTouchEvent(e)){
            mClickListener.onLongClick(child, rv.getChildAdapterPosition(child));
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }
}

