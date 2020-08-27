package com.keatssalazar.waitlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.keatssalazar.waitlist.data.GrocerylistContract;
import com.keatssalazar.waitlist.data.GrocerylistDbHelper;
import com.keatssalazar.waitlist.data.TestUtil;

public class MainActivity extends AppCompatActivity {

    private GroceryListAdapter mAdapter;
    private SQLiteDatabase mDb;
    private EditText mNewItem;
    private EditText mNewWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView gRecyclerView = findViewById(R.id.all_grocery_list_view);
        mNewItem = findViewById(R.id.item_name_edit_text);
        mNewWeight = findViewById(R.id.weight_edit_text);

        gRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        gRecyclerView.setHasFixedSize(true);

        GrocerylistDbHelper dbHelper = new GrocerylistDbHelper(this);

        mDb = dbHelper.getWritableDatabase();


        Cursor mCursor = getAllItem();

        mAdapter = new GroceryListAdapter(this, mCursor);

        gRecyclerView.setAdapter(mAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                long id = (long) viewHolder.itemView.getTag();

                removeGuest(id);

                mAdapter.swapCursor(getAllItem());

            }
        }).attachToRecyclerView(gRecyclerView);


    }

    public void addToGroceryList(View view) {

        if (mNewItem.getText().toString().length() == 0) {
            Toast.makeText(this, "Item Required", Toast.LENGTH_SHORT).show();
            return;
        } else if (mNewWeight.getText().toString().length() == 0) {
            Toast.makeText(this, "Weight Required", Toast.LENGTH_SHORT).show();
            return;
        }
        String item = mNewItem.getText().toString();
        float weight = Float.parseFloat(mNewWeight.getText().toString());


        addNewItem(item, weight);

        mAdapter.swapCursor(getAllItem());

        mNewWeight.clearFocus();
        mNewItem.getText().clear();
        mNewWeight.getText().clear();

    }

    private long addNewItem(String item, float weight) {
        ContentValues cv = new ContentValues();
        cv.put(GrocerylistContract.GrocerylistEntry.COLUMN_ITEM_NAME, item);
        cv.put(GrocerylistContract.GrocerylistEntry.COLUMN_WEIGHT, weight);

        return mDb.insert(GrocerylistContract.GrocerylistEntry.TABLE_NAME, null, cv);

    }

    private boolean removeGuest(long id) {
        return mDb.delete(GrocerylistContract.GrocerylistEntry.TABLE_NAME, GrocerylistContract.GrocerylistEntry._ID + "=" + id, null) > 0;
    }


    private Cursor getAllItem() {
        return mDb.query(GrocerylistContract.GrocerylistEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                GrocerylistContract.GrocerylistEntry.COLUMN_TIMESTAMP);
    }
}