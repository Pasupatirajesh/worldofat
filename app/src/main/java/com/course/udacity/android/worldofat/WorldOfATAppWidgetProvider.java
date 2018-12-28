import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.course.udacity.android.worldofat.Activity.IntroActivity;
import com.course.udacity.android.worldofat.R;

public class WorldOfATAppWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        final int N = appWidgetIds.length;

        for (int i =0; i<N; i++){
            int appWidgetId = appWidgetIds[i];

            //Create an Intent to launch the IntoActivity of the Application
            Intent intent = new Intent(context, IntroActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            // Getting the layout for the AppWidget and attach an on-click listener
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.appwidget_layout);
            remoteViews.setOnClickPendingIntent(R.id.app_widget_imageview, pendingIntent);

            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }
    }
}
