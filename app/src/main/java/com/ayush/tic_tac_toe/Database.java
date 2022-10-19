package com.ayush.tic_tac_toe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    private static final String DB_NAME = "game_record";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "player";
    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1="CREATE TABLE "+TABLE_NAME+"(uname string)";
        db.execSQL(query1);

    }
    public void insert(String uname){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("uname",uname);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public String show(){
        String name="nothing";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursorCourses = db.rawQuery("SELECT * FROM player",null);
        if (cursorCourses != null && cursorCourses.moveToFirst()){
            name=cursorCourses.getString(0);
            System.out.println(name+"     inside db");
        }
        System.out.println("show show fkdasjf "+name);
        return name;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS msgg" );
        onCreate(db);
    }
}
