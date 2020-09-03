package com.keatssalazar.waitlist.data;

import android.provider.BaseColumns;

public class GrocerylistContract {

    public static final class GrocerylistEntry implements BaseColumns{

        public static final String TABLE_NAME = "grocerylist";
        public static final String COLUMN_ITEM_NAME = "itemName";
        public static final String COLUMN_WEIGHT = "weight";
        public static final String COLUMN_MEASURE = "measure";
        public static final String COLUMN_TIMESTAMP = "timestamp";

    }

}
