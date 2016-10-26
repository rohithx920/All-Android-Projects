package com.example.rohith.inclass06;

/**
 * Created by Rohith on 6/16/2016.
 */

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
/*String Appname;
    String Devloper_name;
    String Price;
    String Release_Date;
    String Category;
    String Image;
*/

class TopAppsTable{
    static final String TABLE_NAME = "topapps";
    //static final String Row_ID = "_id";
    static final String App_name = "app_name";
    static final String Devloper_name = "dev_name";
    static final String Price = "price";
    static final String Release_Date = "r_date";
    static final String Category = "category";
    static final String Image = "image";

    static public void onCreate(SQLiteDatabase db){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TopAppsTable.TABLE_NAME + " (");
        //sb.append(TopAppsTable.Row_ID + " integer primary key autoincrement, ");

        sb.append(TopAppsTable.App_name + " text  not null, ");
        sb.append(TopAppsTable.Devloper_name + " text not null, ");
        sb.append(TopAppsTable.Price + " text not null, ");
        sb.append(TopAppsTable.Release_Date + " text not null, ");
        sb.append(TopAppsTable.Category + " text not null, ");
        sb.append(TopAppsTable.Image + " text not null");
        sb.append(");");
        try{
            db.execSQL(sb.toString());
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    static public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TopAppsTable.TABLE_NAME);
        TopAppsTable.onCreate(db);
    }
}
