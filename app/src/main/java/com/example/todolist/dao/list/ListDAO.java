package com.example.todolist.dao.list;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.todolist.db.DatabaseContract;
import com.example.todolist.db.ToDoListDatabaseHelper;
import com.example.todolist.dto.ToDoList;

import java.util.ArrayList;

public class ListDAO implements IListDAO {
    private static SQLiteOpenHelper databaseHelper = null;

    public ListDAO(Context context) {
        databaseHelper = ToDoListDatabaseHelper.getInstance(context);
    }

    public void insertToDoListWithoutItems(String listName, String listDescription) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        SQLiteStatement insertStatement = db.compileStatement(DatabaseContract.ToDoListTable.INSERT_VALUES);

        String[] args = { listName, listDescription };
        insertStatement.bindAllArgsAsStrings(args);
        insertStatement.executeInsert();
    }

    public ArrayList<ToDoList> getAllLists() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor request = db.rawQuery(DatabaseContract.ToDoListTable.SELECT_ALL_LISTS, null);
        ArrayList<ToDoList> lists = new ArrayList<>();

        if (request.moveToFirst()){
            int id = Integer.parseInt(request.getString(0));
            String name = request.getString(1);
            String description = request.getString(2);

            ToDoList newList = new ToDoList(id, name, description);
            lists.add(newList);
            while(request.moveToNext()) {
                id = Integer.parseInt(request.getString(0));
                name = request.getString(1);
                description = request.getString(2);

                newList = new ToDoList(id, name, description);
                lists.add(newList);
            }
        }

        request.close();
        return lists;
    }

    public String getListHeader(int listID) {
        String header;
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor request = db.rawQuery(DatabaseContract.ToDoListTable.SELECT_LIST_BY_ID,
                new String[] { Integer.toString(listID) }
        );

        if (!request.moveToFirst()) {
            request.close();
            throw new NullPointerException();
        }

        header = request.getString(1);
        request.close();
        return header;
    }

    public int getListID(String listHeader) {
        int listID;
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor request = db.rawQuery(DatabaseContract.ToDoListTable.SELECT_LIST_BY_HEADER,
                new String[] { listHeader }
        );

        if (!request.moveToFirst()) {
            request.close();
            throw new NullPointerException();
        }

        listID = request.getInt(1);
        request.close();

        return listID;
    }
}
