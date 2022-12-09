package com.example.it21083396supplementaryassessment.Database;

import android.provider.BaseColumns;

public class FeedbackFeedReader {
    private FeedbackFeedReader() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "FeedBack";
        public static final String COLUMN_1 = "UserName";
        public static final String COLUMN_2 = "ItemName";
        public static final String COLUMN_3 = "Count";
        public static final String COLUMN_4 = "gender";
    }
}
