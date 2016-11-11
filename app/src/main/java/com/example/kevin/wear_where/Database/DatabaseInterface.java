package com.example.kevin.wear_where.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseInterface {
    private SQLiteDatabase database;
    private DatabaseCreator dbCreator;
    private String[] allColumns = {"_id", "min", "max"};

    public DatabaseInterface(Context context) {
        dbCreator = new DatabaseCreator(context);
    }

    public void open() throws SQLException {
        database = dbCreator.getWritableDatabase();
    }

    public void close() {
        dbCreator.close();
    }

    public TempRange createRange(int min, int max) {
        ContentValues values = new ContentValues();
        values.put("min", min);
        values.put("max", max);
        long insertId = database.insert("ranges", null, values);
        Cursor cursor = database.query("ranges", allColumns, "_id" + " = " + insertId, null, null, null, null);
        TempRange range = cursorToRange(cursor);
        cursor.close();
        return range;
    }

    public void updateRange(TempRange range, int min, int max){
        long id = range.getId();
        ContentValues dataToInsert = new ContentValues();
        dataToInsert.put("min", min);
        dataToInsert.put("max", max);
        database.update("counts", dataToInsert, "_id" + " = " + id, null);
    }

    public void deleteRange(TempRange range) {
        long id = range.getId();
        database.delete("ranges", "_id" + " = " + id, null);
    }

    public TempRange getWarmRange() {
        TempRange range = null;

        Cursor cursor = database.query("ranges", allColumns, null, null, null, null, null);

        if (cursor.moveToPosition(0)) {
            range = cursorToRange(cursor);
        }
        // make sure to close the cursor
        cursor.close();
        return range;
    }

    public TempRange getChillyRange() {
        TempRange range = null;

        Cursor cursor = database.query("ranges", allColumns, null, null, null, null, null);

        if (cursor.moveToPosition(1)) {
            range = cursorToRange(cursor);
        }
        // make sure to close the cursor
        cursor.close();
        return range;
    }

    private TempRange cursorToRange(Cursor cursor) {
        TempRange range = new TempRange();
        range.setId(cursor.getLong(0));
        range.setRange(cursor.getInt(1), cursor.getInt(2));
        return range;
    }
}