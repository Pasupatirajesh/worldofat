package com.course.udacity.android.worldofat;

import android.content.Context;

public class JobSearchFetcher {

    public static final String ACTION_JOB_SEARCH_FETCHER = "jobsearchfetcher";

    public static void executeAction(Context context, String action){
        if(ACTION_JOB_SEARCH_FETCHER.equals(action)){
            fetchJobs(context);
        }
    }

    private static void fetchJobs(Context context) {
         // Shd contain logic to fetch json results from Firebase

    }
}
