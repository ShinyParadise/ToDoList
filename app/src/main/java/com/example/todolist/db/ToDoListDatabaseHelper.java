package com.example.todolist.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.sql.SQLOutput;


public class ToDoListDatabaseHelper extends SQLiteOpenHelper {
    ToDoListDatabaseHelper(Context context) {
        super(context, DatabaseContract.DATABASE_NAME, null, DatabaseContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseContract.EntryTable.CREATE_TABLE);
        db.execSQL(DatabaseContract.ToDoListTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL(DatabaseContract.ToDoListTable.DELETE_TABLE);
            db.execSQL(DatabaseContract.EntryTable.DELETE_TABLE);
            onCreate(db);
        }
    }
}
