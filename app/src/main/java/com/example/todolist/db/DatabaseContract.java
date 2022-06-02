package com.example.todolist.db;

import android.provider.BaseColumns;

public final class DatabaseContract {
    public static final int    DATABASE_VERSION   = 1;
    public static final String DATABASE_NAME      = "todolist.db";

    private DatabaseContract() {}

    public static abstract class ToDoListTable implements BaseColumns {
        public static final String TABLE_NAME           = "todo_lists";
        public static final String COLUMN_HEADER        = "header";
        public static final String COLUMN_DESCRIPTION   = "description";
        // Список (родитель) -> пункт списка (ребенок)
        // связь "one-to-many"
        public static final String COLUMN_FK_ENTRIES = "fk_entries";

        public static final String CREATE_TABLE = String.format(
                "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT, %s TEXT, FOREIGN KEY (%s) REFERENCES %s(%s) " +
                        ");",
                TABLE_NAME,
                _ID,
                COLUMN_HEADER,
                COLUMN_DESCRIPTION,
                COLUMN_FK_ENTRIES,
                EntryTable.TABLE_NAME,
                EntryTable._ID
        );

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static abstract class EntryTable implements BaseColumns {
        public static final String TABLE_NAME           = "entries";
        public static final String COLUMN_DESCRIPTION   = "description";

        public static final String CREATE_TABLE = String.format(
                "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT);\n",
                TABLE_NAME, _ID, COLUMN_DESCRIPTION
        );

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
