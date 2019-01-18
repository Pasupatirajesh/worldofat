package com.course.udacity.android.worldofat.Databases;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by SSubra27 on 4/2/18.
 */

public class AtuPersonnelContract {


    // <scheme> :// <Content authority> /<path> ( scheme + Content authority + Base content + Path)
    // Base content URI = content_scheme + authority (A unique reference to the provider)
    //
    //

    public static final String AUTHORITY = "com.course.udacity.android.worldofat";

    public static final Uri BASE_CONTENT_URI =Uri.parse("content://" + AUTHORITY);

    public static final String PATH_TASKS = "atuPersonnel";

    public static final String PATH_TASKS_WITH_ID = "atuPersonnelWithId";

    private AtuPersonnelContract(){

    }

    public static class AtuPersonnelEntry implements BaseColumns{

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                                              .appendPath(PATH_TASKS)
                                              .build();

        public static final Uri CONTENT_URI_WITH_ID =BASE_CONTENT_URI.buildUpon()
                                                    .appendPath(PATH_TASKS_WITH_ID)
                                                    .build();

        public static final String TABLE_NAME = "atuPersonnel";
        public static final String PERSON_NAME = "name";
        public static final String PERSON_EMAIL = "email";
        public static final String PERSON_IMAGE = "image";


    }

}
