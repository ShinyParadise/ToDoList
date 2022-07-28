package com.example.todolist.dao.list;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.NonNull;

import com.example.todolist.db.DatabaseContract;
import com.example.todolist.db.ToDoListDatabaseHelper;
import com.example.todolist.db.models.ListModel;
import com.example.todolist.dto.ToDoList;

import java.util.ArrayList;

public class ListDAO implements IListDAO {
    private static SQLiteOpenHelper databaseHelper = null;

    public ListDAO(Context context) {
        databaseHelper = ToDoListDatabaseHelper.getInstance(context);
    }

    public void create(@NonNull ListModel listModel) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        SQLiteStatement insertStatement = db.compileStatement(DatabaseContract.ToDoListTable.INSERT_VALUES);

        String[] args = { listModel.header, listModel.description };
        insertStatement.bindAllArgsAsStrings(args);
        insertStatement.executeInsert();
    }

    public ListModel getListByID(int listID) {
        ListModel listModel = new ListModel();

        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor request = db.rawQuery(DatabaseContract.ToDoListTable.SELECT_LIST_BY_ID,
                new String[] { Integer.toString(listID) }
        );

        if (!request.moveToFirst()) {
            request.close();
            throw new NullPointerException();
        }

        listModel.id = Integer.parseInt(request.getString(0));
        listModel.header = request.getString(1);
        listModel.description = request.getString(2);

        request.close();
        return listModel;
    }

    public ArrayList<ListModel> getAllLists() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor request = db.rawQuery(DatabaseContract.ToDoListTable.SELECT_ALL_LISTS, null);
        ArrayList<ListModel> lists = new ArrayList<>();

        if (request.moveToFirst()){
            int id = Integer.parseInt(request.getString(0));
            String name = request.getString(1);
            String description = request.getString(2);

            ListModel newList = new ListModel(id, name, description);
            lists.add(newList);

            while (request.moveToNext()) {
                id = Integer.parseInt(request.getString(0));
                name = request.getString(1);
                description = request.getString(2);

                newList = new ListModel(id, name, description);
                lists.add(newList);
            }
        }

        request.close();
        return lists;
    }
}
