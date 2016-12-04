package com.example.kevin.wear_where.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseInterface {
    private SQLiteDatabase database;
    private DatabaseCreator dbCreator;
    private String[] allColumns = {"_id", "min", "max"};    // Columns used to identify table

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

        // Variable used to hold min & max
        ContentValues values = new ContentValues();
        values.put("min", min);
        values.put("max", max);

        // ID of inserted data
        long insertId = database.insert("ranges", null, values);
        Cursor cursor = database.query("ranges", allColumns, "_id" + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();   // Why do I need this?
        TempRange range = cursorToRange(cursor);
        cursor.close();
        return range;
    }

    public void updateRange(TempRange range, int min, int max){

        // Get ID of TempRange object
        long id = range.getId();

        // Variable used to hold min & max
        ContentValues dataToInsert = new ContentValues();
        dataToInsert.put("min", min);
        dataToInsert.put("max", max);

        database.update("ranges", dataToInsert, "_id" + " = " + id, null);
    }

    public void deleteRange(TempRange range) {
        long id = range.getId();
        database.delete("ranges", "_id" + " = " + id, null);
    }

    public TempRange getWarmRange() {
        TempRange range = null;

        // Move cursor to "ranges" table
        Cursor cursor = database.query("ranges", allColumns, null, null, null, null, null);

        // First row in table has warm range
        if (cursor.moveToPosition(0)) {
            range = cursorToRange(cursor);
        }
        // make sure to close the cursor
        cursor.close();
        return range;
    }

    public TempRange getChillyRange() {
        TempRange range = null;

        // Move cursor to "ranges" table
        Cursor cursor = database.query("ranges", allColumns, null, null, null, null, null);

        // Second row in table has chilly range
        if (cursor.moveToPosition(1)) {
            range = cursorToRange(cursor);
        }
        // make sure to close the cursor
        cursor.close();
        return range;
    }

    // Returns TempRange object with data from row that the cursor is pointing to
    private TempRange cursorToRange(Cursor cursor) {
        TempRange range = new TempRange();
        range.setId(cursor.getLong(0));                         // ID is first column
        range.setRange(cursor.getInt(1), cursor.getInt(2));     // min is second column, max is third column
        return range;
    }
}