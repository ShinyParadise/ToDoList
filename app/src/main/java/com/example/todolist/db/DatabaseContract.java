package com.example.todolist.db;

import android.provider.BaseColumns;

public final class DatabaseContract {
    public static final int    DATABASE_VERSION   = 2;
    public static final String DATABASE_NAME      = "todolist.db";

    private DatabaseContract() {}

    public static abstract class ToDoListTable implements BaseColumns {
        public static final String TABLE_NAME           = "todo_lists";
        public static final String COLUMN_HEADER        = "header";
        public static final String COLUMN_DESCRIPTION   = "description";

        public static final String TABLE_PREFIX = TABLE_NAME + TABLE_SEPARATOR;

        public static final String ID_WITH_TABLE_PREFIX = TABLE_PREFIX + _ID;

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
        public static final String COLUMN_LAST_UPDATE   = "last_update";

        public static final String CREATE_TABLE = String.format(
                "CREATE TABLE %s (" +
                        "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT, " +
                        "%s BOOLEAN NOT NULL DEFAULT 0 CHECK (%s IN (0, 1)), " +
                        "%s INTEGER DEFAULT NULL, " +
                        "%s INTEGER, FOREIGN KEY(%s) REFERENCES %s(%s));\n",
                TABLE_NAME,
                _ID,
                COLUMN_DESCRIPTION,
                COLUMN_IS_CHECKED,
                COLUMN_IS_CHECKED,
                COLUMN_LAST_UPDATE,
                COLUMN_FK_LIST,
                COLUMN_FK_LIST,
                ToDoListTable.TABLE_NAME,
                ToDoListTable._ID
        );

        public static final String TABLE_PREFIX = TABLE_NAME + TABLE_SEPARATOR;

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static final String INSERT_VALUES = String.format(
                "INSERT INTO %s (%s, %s) VALUES (?, ?);",
                TABLE_NAME,
                COLUMN_DESCRIPTION,
                COLUMN_FK_LIST
        );

        public static final String UPDATE_LIST_ITEM = String.format(
                "UPDATE %s SET %s = (?), %s = (?), %s = (?) WHERE %s = (?)",
                TABLE_NAME,
                COLUMN_DESCRIPTION,
                COLUMN_IS_CHECKED,
                COLUMN_LAST_UPDATE,
                _ID
        );

        public static final String UPDATE_LIST_ITEM_NO_TIME = String.format(
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

        public static final String SELECT_LAST_ITEM = String.format(
                "SELECT * FROM %s ORDER BY %s DESC LIMIT 1",
                TABLE_NAME,
                _ID
        );
    }

    public static final String COLUMN_SEPARATOR = ", ";
    public static final String TABLE_SEPARATOR = ".";

    public static final String LIST_ITEM_TABLE_COLS =
            ListItemTable.TABLE_PREFIX + ListItemTable._ID +
            COLUMN_SEPARATOR +
            ListItemTable.TABLE_PREFIX + ListItemTable.COLUMN_DESCRIPTION +
            COLUMN_SEPARATOR +
            ListItemTable.TABLE_PREFIX + ListItemTable.COLUMN_IS_CHECKED +
            COLUMN_SEPARATOR +
            ListItemTable.TABLE_PREFIX + ListItemTable.COLUMN_FK_LIST +
            COLUMN_SEPARATOR +
            ListItemTable.TABLE_PREFIX + ListItemTable.COLUMN_LAST_UPDATE;

    public static final String LISTS_AND_ITEMS_INNER_JOIN =
            ToDoListTable.TABLE_NAME +
            " INNER JOIN " +
            ListItemTable.TABLE_NAME +
            " ON " +
            ToDoListTable.TABLE_PREFIX + ToDoListTable._ID +
            " = " +
            ListItemTable.TABLE_PREFIX + ListItemTable.COLUMN_FK_LIST;

    public static final String SELECT_SINGLE_LIST_ITEMS = String.format(
            "SELECT %s FROM %s WHERE %s = (?);",
            LIST_ITEM_TABLE_COLS,
            LISTS_AND_ITEMS_INNER_JOIN,
            ToDoListTable.ID_WITH_TABLE_PREFIX
    );

    public static final String NULL = "NULL";
}
