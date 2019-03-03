package com.course.udacity.android.worldofat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.course.udacity.android.worldofat.Model.Jobs;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.parceler.Parcels;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

class JobWidgetRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private Context mContext;
    private ArrayList<Jobs> mJobs;

    public JobWidgetRemoteViewsFactory(Context applicationContext) {
        mContext = applicationContext;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);

        Gson gson = new Gson();
        Type type = new TypeToken<List<Jobs>>() {}.getType();
        String gsonString = sharedPreferences.getString("Jobslist_Widget", "");
        mJobs = gson.fromJson(gsonString, type);


    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mJobs.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        Jobs jobs = mJobs.get(position);

        RemoteViews itemView = new RemoteViews(mContext.getPackageName(), R.layout.widget_job_list_item);
        itemView.setTextViewText(R.id.widget_job_name, jobs.getJobName());
        itemView.setTextViewText(R.id.widget_job_loc, jobs.getLocation());

        Intent intent = new Intent();
        intent.putExtra(JobWidgetProvider.EXTRA_ITEM, Parcels.wrap(jobs));
        itemView.setOnClickFillInIntent(R.id.widget_job_list, intent);

        return itemView;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
