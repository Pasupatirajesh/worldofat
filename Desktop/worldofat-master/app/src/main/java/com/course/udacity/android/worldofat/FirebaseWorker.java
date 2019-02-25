package com.course.udacity.android.worldofat;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.gson.Gson;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class FirebaseWorker extends Worker {

    private static final String TAG = FirebaseWorker.class.getSimpleName();
    private static final String FIREBASE_PRIMARY_CHILD_NODE = "jobsearch";
    public static final String WORK_PARAM_KEY = "workerparamkey";
    public static final String WORK_RESULT_KEY = "output";
    public Context mContext;

    public FirebaseWorker(@NonNull Context context, WorkerParameters workerParams) {
        super(context, workerParams);
        this.mContext = context;

    }

    @NonNull
    @Override
    public Worker.Result doWork() {

        try {
            Data inputData = getInputData();

            String query = inputData.getString(WORK_PARAM_KEY);

            Log.i(TAG, "The input Data is "+query);

            Query iquery = FirebaseDatabase.getInstance()
                    .getReference("wordlofat-35400")
                    .child(FIREBASE_PRIMARY_CHILD_NODE)
                    .orderByChild("jobName")
                    .equalTo(query)
                    .limitToFirst(7);


            String outputString = serializeToJson(iquery);
            Data outputData = new Data.Builder().putString(WORK_PARAM_KEY,outputString).build();
            Log.i(TAG, "The outputData is " + outputString+"");
            return Result.success(outputData);


        } catch (Throwable throwable) {
            Log.e(TAG, "Error Completing Job fetch", throwable);
            return Result.failure();
        }
    }

    public static String serializeToJson(Query query){
        Gson gson = new Gson();
        return gson.toJson(query);

    }

    public static Query deserializeFromJson(String jsonString){
        Gson gson = new Gson();
        Query iquery = gson.fromJson(jsonString, Query.class);
        return iquery;
    }
}











