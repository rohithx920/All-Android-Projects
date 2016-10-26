package com.example.rohith.p_database;

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
    public long saveNote(Note note){
        return noteDao.save(note);
    }
    public boolean updateNote(Note note){
        return noteDao.update(note);
    }
    public boolean deleteNote(Note note){
        return noteDao.delete(note);
    }
    public Note getNote(long id){
        return noteDao.get(id);
    }
    public List<Note> getAllNotes(){
        return noteDao.getAll();
    }
}