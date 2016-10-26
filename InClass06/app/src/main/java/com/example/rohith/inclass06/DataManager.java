package com.example.rohith.inclass06;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by Rohith on 6/16/2016.
 */
class DataManager {
    Context mContext;
    DatabaseHelper dbOpenHelper;
    SQLiteDatabase db;
    NoteDAO noteDao;

    public DataManager(Context mContext){
        this.mContext = mContext;
        dbOpenHelper = new DatabaseHelper(mContext);
        db = dbOpenHelper.getWritableDatabase();
        noteDao = new NoteDAO(db);
    }
    public void close(){
        db.close();
    }
    public long saveNote(TopApps note){
        return noteDao.save(note);
    }
    public boolean updateNote(TopApps note){
        return noteDao.update(note);
    }
    public boolean deleteNote(TopApps note){
        return noteDao.delete(note);
    }
    public TopApps getNote(String appname){
        return noteDao.get(appname);
    }
    public List<TopApps> getAllNotes(){
        return noteDao.getAll();
    }
}