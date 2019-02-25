package com.course.udacity.android.worldofat;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.course.udacity.android.worldofat.Activity.IntroActivity;

import java.text.DateFormat;
import java.util.Date;

import static android.appwidget.AppWidgetManager.ACTION_APPWIDGET_UPDATE;
import static android.appwidget.AppWidgetManager.EXTRA_APPWIDGET_IDS;

public class WorldOfATAppWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context,
                                AppWidgetManager appWidgetManager, int appWidgetId){

        String timeString = DateFormat.getTimeInstance(
                DateFormat.SHORT).format(new Date());

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                R.layout.appwidget_layout);

        remoteViews.setTextViewText(R.id.id_value, timeString);

        //creating an Intent with the Appwidgetmanager's action_appwidget_update action

        Intent intentUpdate = new Intent(context, WorldOfATAppWidgetProvider.class);
        intentUpdate.setAction(ACTION_APPWIDGET_UPDATE);

        //Update the current widget instance only once, by creating an array of uniquwe appwidget ids

        int [] N = new int[]{appWidgetId};
        intentUpdate.putExtra(EXTRA_APPWIDGET_IDS, N);

        //wrapping the intent as a pending intent
        PendingIntent pendingUpdate = PendingIntent.getBroadcast(context,
                appWidgetId, intentUpdate, PendingIntent.FLAG_UPDATE_CURRENT);

        // send the pending intent in response to the user tapping the 'Update'
        remoteViews.setOnClickPendingIntent(R.id.update, pendingUpdate);

            //Create an Intent to launch the IntoActivity of the Application
            Intent intent = new Intent(context, IntroActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            // Getting the layout for the AppWidget and attach an on-click listener
            remoteViews.setOnClickPendingIntent(R.id.app_widget_imageview, pendingIntent);

            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);



    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

            for (int appWidgetId : appWidgetIds){
            updateAppWidget(context, appWidgetManager, appWidgetId);
            Toast.makeText(context, "Widget has been updated", Toast.LENGTH_LONG).show();

        }





    }



    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        final int N = appWidgetIds.length;
        for (int i=0; i<N; i++){

        }
    }
}
