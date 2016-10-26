package com.example.rohith.inclass06;

/**
 * Created by Rohith on 6/16/2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DatabaseHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "notes.db";
    static final int DATABASE_VERSION = 5;

    DatabaseHelper(Context mContext){
        super(mContext, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        TopAppsTable.onCreate(db);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        TopAppsTable.onUpgrade(db, oldVersion, newVersion);
    }
}