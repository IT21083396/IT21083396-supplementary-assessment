package com.example.it21083396supplementaryassessment.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FeedbackDBHandler extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "JuiceBar.db";

    public FeedbackDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public FeedbackDBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedbackFeedReader.FeedEntry.TABLE_NAME + " (" +
                    FeedbackFeedReader.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedbackFeedReader.FeedEntry.COLUMN_1 + " TEXT," +
                    FeedbackFeedReader.FeedEntry.COLUMN_2 + " TEXT," +
                    FeedbackFeedReader.FeedEntry.COLUMN_3 + " TEXT," +
                    FeedbackFeedReader.FeedEntry.COLUMN_4 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedbackFeedReader.FeedEntry.TABLE_NAME;

    public long addInfo(String UserName, String ItemName, String count, String gender){
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedbackFeedReader.FeedEntry.COLUMN_1, UserName);
        values.put(FeedbackFeedReader.FeedEntry.COLUMN_2, ItemName);
        values.put(FeedbackFeedReader.FeedEntry.COLUMN_3, count);
        values.put(FeedbackFeedReader.FeedEntry.COLUMN_4, gender);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(FeedbackFeedReader.FeedEntry.TABLE_NAME, null, values);
        return newRowId;
    }
    public boolean updateInfo(String UserName, String ItemName, String count, String gender){
        SQLiteDatabase db = getWritableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(FeedbackFeedReader.FeedEntry.COLUMN_2, ItemName);
        values.put(FeedbackFeedReader.FeedEntry.COLUMN_3, count);
        values.put(FeedbackFeedReader.FeedEntry.COLUMN_4, gender);

        // Which row to update, based on the title
        String selection = FeedbackFeedReader.FeedEntry.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { UserName };

        int Count = db.update(
                FeedbackFeedReader.FeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        if(Count >0){
            return true;
        }
        else
            return false;
    }
    public void deleteInfo(String UserName){
        SQLiteDatabase db = getWritableDatabase();
        // Define 'where' part of query.
        String selection = FeedbackFeedReader.FeedEntry.COLUMN_1 + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { UserName };
        // Issue SQL statement.
        int deletedRows = db.delete(FeedbackFeedReader.FeedEntry.TABLE_NAME, selection, selectionArgs);
    }
    public List readAllInfo(){
        String UserName = FeedbackFeedReader.FeedEntry.COLUMN_1;
        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                FeedbackFeedReader.FeedEntry.COLUMN_1,
                FeedbackFeedReader.FeedEntry.COLUMN_2,
                FeedbackFeedReader.FeedEntry.COLUMN_3,
                FeedbackFeedReader.FeedEntry.COLUMN_4,
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = FeedbackFeedReader.FeedEntry.COLUMN_1 + " = ?";
        String[] selectionArgs = { UserName };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                FeedbackFeedReader.FeedEntry.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                FeedbackFeedReader.FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null ,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        List UserNames = new ArrayList<>();
        while(cursor.moveToNext()) {
            String user = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedbackFeedReader.FeedEntry.COLUMN_1));
            UserNames.add(user);
        }
        cursor.close();
        return UserNames;
    }
    public List readAllInfo(String UserName){

        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                FeedbackFeedReader.FeedEntry.COLUMN_1,
                FeedbackFeedReader.FeedEntry.COLUMN_2,
                FeedbackFeedReader.FeedEntry.COLUMN_3,
                FeedbackFeedReader.FeedEntry.COLUMN_4,
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = FeedbackFeedReader.FeedEntry.COLUMN_1 + " = LIKE";
        String[] selectionArgs = { UserName };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                FeedbackFeedReader.FeedEntry.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                FeedbackFeedReader.FeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        List FeedBack = new ArrayList<>();
        while(cursor.moveToNext()) {
            String user = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedbackFeedReader.FeedEntry.COLUMN_1));
            String ItemName = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedbackFeedReader.FeedEntry.COLUMN_2));
            String count = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedbackFeedReader.FeedEntry.COLUMN_3));
            String gender = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedbackFeedReader.FeedEntry.COLUMN_4));
            FeedBack.add(user);//0
            FeedBack.add(ItemName);//1
            FeedBack.add(count);//2
            FeedBack.add(gender);//3
        }
        cursor.close();
        return FeedBack;
    }

}
