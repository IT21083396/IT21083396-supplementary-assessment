package com.example.it21083396supplementaryassessment.Database;

import android.provider.BaseColumns;

public final class FeedReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "UserInfo";
        public static final String COLUMN_1 = "UserName";
        public static final String COLUMN_2 = "dateOfbirth";
        public static final String COLUMN_3 = "password";
        public static final String COLUMN_4 = "gender";
    }
}

