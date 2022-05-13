package com.example.todolist.models.contracts;

import android.provider.BaseColumns;

public class ToDoListContract {
    private ToDoListContract() {}

    public static class ToDoListEntry implements BaseColumns {
        public static final String TABLE_NAME = "ToDoLists";
        public static final String COLUMN_HEADER = "header";
        public static final String COLUMN_DESCRIPTION = "description";
        // Список (родитель) -> пункт списка (ребенок)
        // связь "one-to-many"
        public static final String COLUMN_FK_PARAGRAPHS = "fk_paragraphs";
    }
}
