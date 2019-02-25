package com.course.udacity.android.worldofat;

import android.util.Log;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

public class FirebaseWorkManager {
    private static final String TAG = FirebaseWorkManager.class.getSimpleName();
    private WorkManager mWorkManager;
    private OneTimeWorkRequest mOneTimeWorkRequest;
//    private LifecycleRegistry mLifecycleRegistry;
    private Data mData;
    private static LiveData<List<WorkInfo>> mSavedWorkInfo;



    private String outputString;


    public FirebaseWorkManager(){
        mWorkManager = WorkManager.getInstance();

//        mLifecycleRegistry = new LifecycleRegistry(this::getLifecycle);
//        mLifecycleRegistry.markState(Lifecycle.State.CREATED);
        mSavedWorkInfo = mWorkManager.getWorkInfosByTagLiveData(FirebaseWorker.WORK_RESULT_KEY);

    }

    public void setInputData(Data data){
        this.mData = data;

    }

    public void startJobSearch() {
        if (mData != null) {
            mOneTimeWorkRequest =
                new OneTimeWorkRequest.Builder(FirebaseWorker.class)
                        .addTag(FirebaseWorker.WORK_RESULT_KEY).setInputData(mData).build();


        mWorkManager.enqueue(mOneTimeWorkRequest);


    }

    }

    public String getOutputString() {
        return outputString;
    }

    public void setOutputString(String s) {
        this.outputString = s;
    }



//    @NonNull
//    @Override
//    public Lifecycle getLifecycle() {
//        return mLifecycleRegistry;
//    }

    public LiveData<List<WorkInfo>> getOutputWorkInfo(){
        Log.i(TAG, mSavedWorkInfo+"");
        return mSavedWorkInfo;
    }
}
