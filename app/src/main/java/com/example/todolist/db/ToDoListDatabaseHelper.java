package com.example.todolist.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;


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
    public void onCreate(@NonNull SQLiteDatabase db) {
        db.execSQL(DatabaseContract.ListItemTable.CREATE_TABLE);
        db.execSQL(DatabaseContract.ToDoListTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL(DatabaseContract.ListItemTable.MIGRATE_TO_VERSION_2);
        }
    }

    @Override
    public void onConfigure(@NonNull SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }
}
