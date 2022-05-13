package com.example.todolist.models.contracts;

import android.provider.BaseColumns;

public class ParagraphContract {
    private ParagraphContract() {}

    public static class ParagraphEntry implements BaseColumns {
        public static final String TABLE_NAME = "paragraphs";
        public static final String COLUMN_DESCRIPTION = "description";
    }
}
