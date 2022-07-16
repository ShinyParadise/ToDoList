package com.example.todolist.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class ToDoListDatabaseHelper extends SQLiteOpenHelper {
    private static ToDoListDatabaseHelper listHelperInstance = null;

    private ToDoListDatabaseHelper(Context context) {
        super(context, DatabaseContract.DATABASE_NAME, null, DatabaseContract.DATABASE_VERSION);
    }

    public static ToDoListDatabaseHelper getInstance(Context context) {
        if (listHelperInstance == null) {
            listHelperInstance = new ToDoListDatabaseHelper(context);
        }

        return listHelperInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseContract.ListItemTable.CREATE_TABLE);
        db.execSQL(DatabaseContract.ToDoListTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL(DatabaseContract.ToDoListTable.DELETE_TABLE);
            db.execSQL(DatabaseContract.ListItemTable.DELETE_TABLE);
            onCreate(db);
        }
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }


}
