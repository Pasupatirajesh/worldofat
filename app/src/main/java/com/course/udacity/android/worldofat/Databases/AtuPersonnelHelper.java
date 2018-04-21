package com.course.udacity.android.worldofat.Databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.course.udacity.android.worldofat.Databases.AtuPersonnelContract.AtuPersonnelEntry;

/**
 * Created by SSubra27 on 4/2/18.
 */

public class AtuPersonnelHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "AtuPersonnelHelper.db";

    private static final int DATABASE_VERSION = 1;

    public AtuPersonnelHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public AtuPersonnelHelper(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_ATU_PERSONNEL_TABLE = "CREATE TABLE "+
                AtuPersonnelEntry.TABLE_NAME+ " ("+
                AtuPersonnelEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                AtuPersonnelEntry.PERSON_NAME+ " TEXT,"+
                AtuPersonnelEntry.PERSON_EMAIL+ " TEXT,"+
                AtuPersonnelEntry.PERSON_IMAGE+ " TEXT"+ ");";

        db.execSQL(SQL_CREATE_ATU_PERSONNEL_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(AtuPersonnelEntry.TABLE_NAME);
        onCreate(db);

    }
}
