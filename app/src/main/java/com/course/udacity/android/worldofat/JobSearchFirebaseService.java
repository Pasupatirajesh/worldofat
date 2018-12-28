package com.course.udacity.android.worldofat;

import android.content.Context;
import android.os.AsyncTask;

import com.firebase.jobdispatcher.JobParameters;

public class JobSearchFirebaseService extends com.firebase.jobdispatcher.JobService {
    //  Create an AsynTask Variable;
    AsyncTask mBackgroundTask;

    @Override
    public boolean onStartJob(final JobParameters job) {
        mBackgroundTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                Context context = JobSearchFirebaseService.this;
                JobSearchFetcher.executeAction(context,JobSearchFetcher.ACTION_JOB_SEARCH_FETCHER );
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                jobFinished(job, false);
            }
        };
        mBackgroundTask.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        if(mBackgroundTask!=null) mBackgroundTask.cancel(true);
        return true;
    }
}
