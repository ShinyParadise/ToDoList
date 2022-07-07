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
        public static final String COLUMN_FK_ENTRIES    = "fk_entries";

        public static final String CREATE_TABLE = String.format(
                "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT, %s TEXT, %s INTEGER NULL, " +
                        "FOREIGN KEY (%s) REFERENCES %s(%s) ON DELETE CASCADE" +
                        ");",
                TABLE_NAME,
                _ID,
                COLUMN_HEADER,
                COLUMN_DESCRIPTION,
                COLUMN_FK_ENTRIES,
                COLUMN_FK_ENTRIES,
                EntryTable.TABLE_NAME,
                EntryTable._ID
        );

        public static final String SELECT_ALL_LISTS = String.format(
                "SELECT %s, %s, %s, %s FROM %s GROUP BY %s",
                _ID, COLUMN_HEADER, COLUMN_DESCRIPTION, COLUMN_FK_ENTRIES,
                TABLE_NAME, COLUMN_HEADER
        );

        public static final String SELECT_LIST_BY_ID = "SELECT * FROM " + TABLE_NAME + " WHERE _id = (?)";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static final String INSERT_VALUES = "INSERT INTO " + TABLE_NAME + " ("
                + COLUMN_HEADER + ", " + COLUMN_DESCRIPTION + ", " + COLUMN_FK_ENTRIES
                + ") " + "VALUES (?, ?, ?)";
    }

    public static abstract class EntryTable implements BaseColumns {
        public static final String TABLE_NAME           = "entries";
        public static final String COLUMN_DESCRIPTION   = "description";

        public static final String CREATE_TABLE = String.format(
                "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT);\n",
                TABLE_NAME, _ID, COLUMN_DESCRIPTION
        );

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static final String INSERT_VALUES = "INSERT INTO " + TABLE_NAME + " ("
                + COLUMN_DESCRIPTION + ") " + "VALUES (?)";
    }

    public static String SELECT_LIST_WITH_ENTRIES = "SELECT * FROM " + ToDoListTable.TABLE_NAME +
            " INNER JOIN " + EntryTable.TABLE_NAME + " ON " + ToDoListTable.TABLE_NAME + "." +
            ToDoListTable.COLUMN_FK_ENTRIES + " = " + EntryTable._ID + " WHERE " +
            ToDoListTable._ID + " = (?)";

    public static String NULL = "NULL";
}
