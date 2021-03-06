package com.example.posts;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper  extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 8;
    public static final String DATABASE_NAME = "contactDb";
    public static final String TABLE_TEXT = "textTable";

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_TEXT = "text";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_TEXT + "(" + KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + KEY_NAME + " text," + KEY_TEXT + " text" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_TEXT);

        onCreate(db);

    }
}