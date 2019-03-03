package com.course.udacity.android.worldofat;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class JobWidgetService extends RemoteViewsService{
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new JobWidgetRemoteViewsFactory(getApplicationContext());
    }
}
