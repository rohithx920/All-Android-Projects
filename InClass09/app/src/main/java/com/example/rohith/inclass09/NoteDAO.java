package com.example.rohith.inclass09;

/**
 * Created by This on 6/23/16.
 */
import android.annotation.TargetApi;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


class NoteDAO { //Data Access Object (DAO)
    private SQLiteDatabase db;

    public NoteDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public long save(TopApps note) {
        ContentValues values = new ContentValues();
        Log.d("yyyyy", note.getAppname());
        values.put(TopAppsTable.App_name, note.getAppname());
        values.put(TopAppsTable.Devloper_name, note.getDevloper_name());
        values.put(TopAppsTable.Price, note.getPrice());
        values.put(TopAppsTable.Release_Date, note.getRelease_Date());
        values.put(TopAppsTable.Category, note.getCategory());
        values.put(TopAppsTable.Image, note.getImage());
        return db.insert(TopAppsTable.TABLE_NAME, null, values);
    }

    public boolean update(TopApps note) {
        ContentValues values = new ContentValues();
        values.put(TopAppsTable.App_name, note.getAppname());
        values.put(TopAppsTable.Devloper_name, note.getDevloper_name());
        values.put(TopAppsTable.Price, note.getPrice());
        values.put(TopAppsTable.Release_Date, note.getRelease_Date());
        values.put(TopAppsTable.Category, note.getCategory());
        values.put(TopAppsTable.Image, note.getImage());
        return db.update(TopAppsTable.TABLE_NAME, values, TopAppsTable.App_name + "=" + note.getAppname(), null) > 0;
    }

    public boolean delete(TopApps note) {
        return db.delete(TopAppsTable.TABLE_NAME, TopAppsTable.App_name + "=?", new String[]{note.getAppname() + ""}) > 0;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public TopApps get(String app_name) {
        TopApps note = null;
        Cursor c = db.query(true, TopAppsTable.TABLE_NAME,
                new String[]{TopAppsTable.App_name, TopAppsTable.Devloper_name, TopAppsTable.Price,
                        TopAppsTable.Release_Date, TopAppsTable.Category, TopAppsTable.Image},
                TopAppsTable.App_name + "=?", new String[]{app_name + ""}, null, null, null, null, null);
        if (c != null && c.moveToFirst()) {

            note = this.buildNoteFromCursor(c);
        }
        if (!c.isClosed()) {
            c.close();
        }
        return note;
    }

    public List<TopApps> getAll() {
        List<TopApps> list = new ArrayList<TopApps>();
        Cursor c = db.query(TopAppsTable.TABLE_NAME,
                new String[]{TopAppsTable.App_name, TopAppsTable.Devloper_name, TopAppsTable.Price,
                        TopAppsTable.Release_Date, TopAppsTable.Category, TopAppsTable.Image},
                null, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
            do {
                TopApps note = this.buildNoteFromCursor(c);
                if (note != null) {
                    list.add(note);
                }
            } while (c.moveToNext());

            if (!c.isClosed()) {
                c.close();
            }
        }
        return list;
    }

    private TopApps buildNoteFromCursor(Cursor c) {
        TopApps note = null;
        if (c != null) {
            note = new TopApps();
            note.setAppname(c.getString(0));
            note.setDevloper_name(c.getString(1));
            note.setPrice(c.getString(2));
            note.setRelease_Date(c.getString(3));
            note.setCategory(c.getString(4));
            note.setImage(c.getString(5));

        }
        return note;
    }
}
