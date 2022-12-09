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

public class OrderDBHandler extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "JuiceBar.db";

    public OrderDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public OrderDBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
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
            "CREATE TABLE " + OrderFeedReader.OrderFeedEntry.TABLE_NAME + " (" +
                    OrderFeedReader.OrderFeedEntry._ID + " INTEGER PRIMARY KEY," +
                    OrderFeedReader.OrderFeedEntry.COLUMN_1 + " TEXT," +
                    OrderFeedReader.OrderFeedEntry.COLUMN_2 + " TEXT," +
                    OrderFeedReader.OrderFeedEntry.COLUMN_3 + " TEXT," +
                    OrderFeedReader.OrderFeedEntry.COLUMN_4 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + OrderFeedReader.OrderFeedEntry.TABLE_NAME;

    public long addInfo(String OrNo, String UserName, String ItemNo, String Price){
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(OrderFeedReader.OrderFeedEntry.COLUMN_1, OrNo);
        values.put(OrderFeedReader.OrderFeedEntry.COLUMN_2, UserName);
        values.put(OrderFeedReader.OrderFeedEntry.COLUMN_3, ItemNo);
        values.put(OrderFeedReader.OrderFeedEntry.COLUMN_4, Price);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(OrderFeedReader.OrderFeedEntry.TABLE_NAME, null, values);
        return newRowId;
    }
    public boolean updateInfo(String OrNo, String UserName, String ItemNo, String Price){
        SQLiteDatabase db = getWritableDatabase();

        // New value for one column
        ContentValues values = new ContentValues();
        values.put(OrderFeedReader.OrderFeedEntry.COLUMN_2, UserName);
        values.put(OrderFeedReader.OrderFeedEntry.COLUMN_3, ItemNo);
        values.put(OrderFeedReader.OrderFeedEntry.COLUMN_4, Price);

        // Which row to update, based on the title
        String selection = OrderFeedReader.OrderFeedEntry.COLUMN_1 + " LIKE ?";
        String[] selectionArgs = { OrNo };

        int Count = db.update(
                OrderFeedReader.OrderFeedEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        if(Count >0){
            return true;
        }
        else
            return false;
    }
    public void deleteInfo(String OrNo){
        SQLiteDatabase db = getWritableDatabase();
        // Define 'where' part of query.
        String selection = OrderFeedReader.OrderFeedEntry.COLUMN_1 + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { OrNo };
        // Issue SQL statement.
        int deletedRows = db.delete(OrderFeedReader.OrderFeedEntry.TABLE_NAME, selection, selectionArgs);
    }
    public List readAllInfo(){
        String OrNo = OrderFeedReader.OrderFeedEntry.COLUMN_1;
        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                OrderFeedReader.OrderFeedEntry.COLUMN_1,
                OrderFeedReader.OrderFeedEntry.COLUMN_2,
                OrderFeedReader.OrderFeedEntry.COLUMN_3,
                OrderFeedReader.OrderFeedEntry.COLUMN_4,
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = OrderFeedReader.OrderFeedEntry.COLUMN_1 + " = ?";
        String[] selectionArgs = { OrNo };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                OrderFeedReader.OrderFeedEntry.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                OrderFeedReader.OrderFeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null ,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        List OrNos = new ArrayList<>();
        while(cursor.moveToNext()) {
            String user = cursor.getString(
                    cursor.getColumnIndexOrThrow(OrderFeedReader.OrderFeedEntry.COLUMN_1));
            OrNos.add(user);
        }
        cursor.close();
        return OrNos;
    }
    public List readAllInfo(String OrNo){

        SQLiteDatabase db = getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                OrderFeedReader.OrderFeedEntry.COLUMN_1,
                OrderFeedReader.OrderFeedEntry.COLUMN_2,
                OrderFeedReader.OrderFeedEntry.COLUMN_3,
                OrderFeedReader.OrderFeedEntry.COLUMN_4,
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = OrderFeedReader.OrderFeedEntry.COLUMN_1 + " = LIKE";
        String[] selectionArgs = { OrNo };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                OrderFeedReader.OrderFeedEntry.COLUMN_1 + " ASC";

        Cursor cursor = db.query(
                OrderFeedReader.OrderFeedEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                selection,              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
        List Order = new ArrayList<>();
        while(cursor.moveToNext()) {
            String user = cursor.getString(
                    cursor.getColumnIndexOrThrow(OrderFeedReader.OrderFeedEntry.COLUMN_1));
            String UserName = cursor.getString(
                    cursor.getColumnIndexOrThrow(OrderFeedReader.OrderFeedEntry.COLUMN_2));
            String ItemNo = cursor.getString(
                    cursor.getColumnIndexOrThrow(OrderFeedReader.OrderFeedEntry.COLUMN_3));
            String Price = cursor.getString(
                    cursor.getColumnIndexOrThrow(OrderFeedReader.OrderFeedEntry.COLUMN_4));
            Order.add(user);//0
            Order.add(UserName);//1
            Order.add(ItemNo);//2
            Order.add(Price);//3
        }
        cursor.close();
        return Order;
    }

}
