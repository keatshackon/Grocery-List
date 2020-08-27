package com.keatssalazar.waitlist.data;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.keatssalazar.waitlist.data.GrocerylistContract;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {

    public static void insertFakeData(SQLiteDatabase db){
        if(db == null){
            return;
        }
        List<ContentValues> list = new ArrayList<ContentValues>();

        ContentValues cv = new ContentValues();
        cv.put(GrocerylistContract.GrocerylistEntry.COLUMN_ITEM_NAME,"Chicken");
        cv.put(GrocerylistContract.GrocerylistEntry.COLUMN_WEIGHT,2);
        list.add(cv);


        cv = new ContentValues();
        cv.put(GrocerylistContract.GrocerylistEntry.COLUMN_ITEM_NAME, "Rice");
        cv.put(GrocerylistContract.GrocerylistEntry.COLUMN_WEIGHT, 5);
        list.add(cv);

        cv = new ContentValues();
        cv.put(GrocerylistContract.GrocerylistEntry.COLUMN_ITEM_NAME, "Pulse");
        cv.put(GrocerylistContract.GrocerylistEntry.COLUMN_WEIGHT, 4);
        list.add(cv);

        cv = new ContentValues();
        cv.put(GrocerylistContract.GrocerylistEntry.COLUMN_ITEM_NAME, "Bread");
        cv.put(GrocerylistContract.GrocerylistEntry.COLUMN_WEIGHT, 2);
        list.add(cv);

        cv = new ContentValues();
        cv.put(GrocerylistContract.GrocerylistEntry.COLUMN_ITEM_NAME, "potatoes");
        cv.put(GrocerylistContract.GrocerylistEntry.COLUMN_WEIGHT, 5);
        list.add(cv);


        try{
            db.beginTransaction();
            //clear the table first
            db.delete (GrocerylistContract.GrocerylistEntry.TABLE_NAME,null,null);
            //go through the list and add one by one
            for(ContentValues c:list){
                db.insert(GrocerylistContract.GrocerylistEntry.TABLE_NAME, null, c);
            }
            db.setTransactionSuccessful();

        }catch (SQLException e){

        }
        finally {
            db.endTransaction();
        }



    }

}
