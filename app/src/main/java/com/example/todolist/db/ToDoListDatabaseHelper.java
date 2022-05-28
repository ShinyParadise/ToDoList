package com.example.todolist.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.todolist.db.contracts.ParagraphContract;
import com.example.todolist.db.contracts.ToDoListContract;


public class ToDoListDatabaseHelper extends SQLiteOpenHelper {
    public static final String dbName = "todolist_db";
    public static final int dbVersion = 1;
    public static final String createTDListsTableScript = String.format(
            "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "%s TEXT, %s TEXT, FOREIGN KEY (%s) REFERENCES %s(%s) " +
                    ");",
            ToDoListContract.ToDoListEntry.TABLE_NAME,
            ToDoListContract.ToDoListEntry._ID,
            ToDoListContract.ToDoListEntry.COLUMN_HEADER,
            ToDoListContract.ToDoListEntry.COLUMN_DESCRIPTION,
            ToDoListContract.ToDoListEntry.COLUMN_FK_PARAGRAPHS,
            ParagraphContract.ParagraphEntry.TABLE_NAME,
            ParagraphContract.ParagraphEntry._ID
    );

    public static final String createParagraphsTableScript = String.format(
            "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT);\n",
            ParagraphContract.ParagraphEntry.TABLE_NAME,
            ParagraphContract.ParagraphEntry._ID,
            ParagraphContract.ParagraphEntry.COLUMN_DESCRIPTION
    );

    public static final String dbCreateScript = createParagraphsTableScript +
            createTDListsTableScript;

    // TODO: разобраться с курсорами, доделать конструктор и начальную инициализацию
    ToDoListDatabaseHelper(Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(dbCreateScript);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // TODO: написать методы для создания, удаления и изменения списков дел
}
