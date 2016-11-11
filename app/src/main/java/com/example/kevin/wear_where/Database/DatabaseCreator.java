package com.example.kevin.wear_where.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseCreator extends SQLiteOpenHelper{

    private static final String DATABASE_CREATE = "create table ranges(_id integer primary key autoincrement, min integer not null, max integer not null);";
    private static final String DATABASE_NAME = "range.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseCreator(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS counts");
        onCreate(db);
    }
}