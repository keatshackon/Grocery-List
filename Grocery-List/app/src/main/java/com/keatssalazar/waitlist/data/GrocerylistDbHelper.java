package com.keatssalazar.waitlist.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.keatssalazar.waitlist.data.GrocerylistContract.GrocerylistEntry;

import androidx.annotation.Nullable;


public class GrocerylistDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "grocerylist.db";
    private static final int DATABASE_VERSION = 1;


    public GrocerylistDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_GROCERY_LIST_TABLE = "CREATE TABLE " + GrocerylistEntry.TABLE_NAME + " (" +
                GrocerylistEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                GrocerylistEntry.COLUMN_ITEM_NAME + " TEXT NOT NULL, " +
                GrocerylistEntry.COLUMN_WEIGHT + " REAL NOT NULL," +
                GrocerylistEntry.COLUMN_MEASURE + " TEXT NOT NULL, " +
                GrocerylistEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                "); ";

        db.execSQL(SQL_CREATE_GROCERY_LIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + GrocerylistEntry.TABLE_NAME);
        onCreate(db);

    }
}
