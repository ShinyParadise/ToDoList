package com.example.todolist.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.todolist.db.models.ListModel;

import java.util.ArrayList;

/*
* Repository for ToDoLists
* use cases:
* 1) показать все списки дел
* 2) показать конкретный список дел и его пункты
* 3) добавить новый список
* 4) удалить список
*/
public class ListRepository {
    private static ToDoListDatabaseHelper databaseHelper;

    public ListRepository(Context context) {
        databaseHelper = new ToDoListDatabaseHelper(context.getApplicationContext());
    }

    public void insertToDoListWithEntries(String listName, String listDescription, String[] fkEntries) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        SQLiteStatement insertStatement = db.compileStatement(DatabaseContract.ToDoListTable.INSERT_VALUES);

        for (String entry: fkEntries) {
            String[] args = {listName, listDescription, entry};
            insertStatement.bindAllArgsAsStrings(args);
            insertStatement.executeInsert();
        }
    }

    public void insertToDoListWithoutEntries(String listName, String listDescription) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        SQLiteStatement insertStatement = db.compileStatement(DatabaseContract.ToDoListTable.INSERT_VALUES);

        String[] args = { listName, listDescription, DatabaseContract.NULL };
        insertStatement.bindAllArgsAsStrings(args);
        insertStatement.executeInsert();
    }

    public void insertEntries(String entryDescription) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        SQLiteStatement insertStatement = db.compileStatement(DatabaseContract.EntryTable.INSERT_VALUES);

        insertStatement.bindString(1, entryDescription);
        insertStatement.executeInsert();
    }

    public ArrayList<ListModel> getAllLists() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor request = db.rawQuery(DatabaseContract.ToDoListTable.SELECT_ALL_LISTS, null);
        ArrayList<ListModel> lists = new ArrayList<>();

        if (request.moveToFirst()){
            ListModel newList = new ListModel();
            newList.id = Integer.parseInt(request.getString(0));
            newList.name = request.getString(1);
            newList.description = request.getString(2);
            newList.fkEntries = Integer.parseInt(request.getString(3));

            lists.add(newList);
            while(request.moveToNext()) {
                newList.id = Integer.parseInt(request.getString(0));
                newList.name = request.getString(1);
                newList.description = request.getString(2);
                newList.fkEntries = Integer.parseInt(request.getString(3));

                lists.add(newList);
            }
        }

        request.close();
        return lists;
    }
}
