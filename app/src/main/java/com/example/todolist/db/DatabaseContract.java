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

        public static final String CREATE_TABLE = String.format(
                "CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT, " +
                        "%s TEXT);",
                TABLE_NAME,
                _ID,
                COLUMN_HEADER,
                COLUMN_DESCRIPTION
        );

        public static final String SELECT_ALL_LISTS = String.format(
                "SELECT %s, %s, %s FROM %s",
                _ID,
                COLUMN_HEADER,
                COLUMN_DESCRIPTION,
                TABLE_NAME
        );

        public static final String SELECT_LIST_BY_ID = String.format(
                "SELECT * FROM %s WHERE %s = (?);",
                TABLE_NAME,
                _ID
        );

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static final String INSERT_VALUES = String.format(
                "INSERT INTO %s (%s, %s) VALUES (?, ?)",
                TABLE_NAME,
                COLUMN_HEADER,
                COLUMN_DESCRIPTION
        );

        public static final String SELECT_LAST_ITEM = String.format(
                "SELECT * FROM %s ORDER BY %s DESC LIMIT 1",
                TABLE_NAME,
                _ID
        );
    }

    public static abstract class ListItemTable implements BaseColumns {
        public static final String TABLE_NAME           = "list_item";
        public static final String COLUMN_DESCRIPTION   = "description";
        public static final String COLUMN_IS_CHECKED    = "is_checked";
        public static final String COLUMN_FK_LIST       = "fk_list";

        public static final String CREATE_TABLE = String.format(
                "CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT, " +
                        "%s BOOLEAN NOT NULL DEFAULT 0 CHECK (%s IN (0, 1)), " +
                        "%s INTEGER, FOREIGN KEY(%s) REFERENCES %s(%s));\n",
                TABLE_NAME,
                _ID,
                COLUMN_DESCRIPTION,
                COLUMN_IS_CHECKED,
                COLUMN_IS_CHECKED,
                COLUMN_FK_LIST,
                COLUMN_FK_LIST,
                ToDoListTable.TABLE_NAME,
                ToDoListTable._ID
        );

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static final String INSERT_VALUES = String.format(
                "INSERT INTO %s (%s, %s) VALUES (?, ?);",
                TABLE_NAME,
                COLUMN_DESCRIPTION,
                COLUMN_FK_LIST
        );

        public static final String UPDATE_LIST_ITEM = String.format(
                "UPDATE %s SET %s = (?), %s = (?) WHERE %s = (?)",
                TABLE_NAME,
                COLUMN_DESCRIPTION,
                COLUMN_IS_CHECKED,
                _ID
        );

        public static final String SELECT_LIST_ITEM_BY_ID = String.format(
                "SELECT * FROM %s WHERE %s = (?)",
                TABLE_NAME,
                _ID
        );
    }

    public static String SELECT_SINGLE_LIST_ITEMS = String.format(
            "SELECT %s.%s, %s.%s, %s.%s, %s.%s FROM %s INNER JOIN %s ON %s.%s = %s.%s WHERE %s.%s = (?);",
            ListItemTable.TABLE_NAME, ListItemTable._ID,
            ListItemTable.TABLE_NAME, ListItemTable.COLUMN_DESCRIPTION,
            ListItemTable.TABLE_NAME, ListItemTable.COLUMN_FK_LIST,
            ListItemTable.TABLE_NAME, ListItemTable.COLUMN_IS_CHECKED,
            ToDoListTable.TABLE_NAME,
            ListItemTable.TABLE_NAME,
            ToDoListTable.TABLE_NAME, ToDoListTable._ID,
            ListItemTable.TABLE_NAME, ListItemTable.COLUMN_FK_LIST,
            ToDoListTable.TABLE_NAME, ToDoListTable._ID
    );

    public static String NULL = "NULL";
}
