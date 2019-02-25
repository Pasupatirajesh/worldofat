package com.course.udacity.android.worldofat;

import android.content.Context;
import android.widget.Toast;

class JobSearchFetcher {


    public static final String ACTION_JOB_SEARCH = "jobfetcher" ;

    public static void executeAction(Context context, Object actionJobSearch) {
        if(ACTION_JOB_SEARCH.equals(actionJobSearch)){
            fetchJobs(context);
        }
    }

    private static void fetchJobs(Context context) {
        Toast.makeText(context, "Hey there from the job fetcher", Toast.LENGTH_LONG).show();
    }
}
