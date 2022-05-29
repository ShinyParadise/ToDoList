package com.example.todolist.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


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

    }

    // TODO: написать методы для создания, удаления и изменения списков дел
}
