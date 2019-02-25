package com.course.udacity.android.worldofat;

import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;

public class JobSearchFirebaseService extends JobService {

    private static AsyncTask mBackgraoundTask;
    @Override
    public boolean onStartJob(JobParameters params) {
        mBackgraoundTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                Context context = JobSearchFirebaseService.this;
                JobSearchFetcher.executeAction(context, JobSearchFetcher.ACTION_JOB_SEARCH);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
            }


        };
        mBackgraoundTask.execute();
        return true;




    }

    @Override
    public boolean onStopJob(JobParameters params) {
        if(mBackgraoundTask!=null) mBackgraoundTask.cancel(true);
        return true;
    }
}
