package com.example.it21083396supplementaryassessment.Database;

import android.provider.BaseColumns;


    public final class OrderFeedReader {
        // To prevent someone from accidentally instantiating the contract class,
        // make the constructor private.
        private OrderFeedReader() {}

        /* Inner class that defines the table contents */
        public static class OrderFeedEntry implements BaseColumns {
            public static final String TABLE_NAME = "PlaceOrder";
            public static final String COLUMN_1 = "OrNo";
            public static final String COLUMN_2 = "UserName";
            public static final String COLUMN_3 = "ItemNo";
            public static final String COLUMN_4 = "Price";
        }
    }


