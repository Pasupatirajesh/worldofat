package com.course.udacity.android.worldofat;

import android.content.Context;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;

public class JobSearchUtilities  {

    private static final String JOB_SEARCH_SERVICE_TAG = "job_search_service_tag";

    private static boolean sInitialized;

    synchronized public static void scheduleJobSearchService(final Context context) {
        if(sInitialized) return;

        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);
        Job constraintJobSearch = dispatcher.newJobBuilder()
                .setService(JobSearchFirebaseService.class)
                .setTag(JOB_SEARCH_SERVICE_TAG)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .setRecurring(true)
                .setReplaceCurrent(true)
                .build();

        dispatcher.schedule(constraintJobSearch);
        sInitialized = true;

    }
}
