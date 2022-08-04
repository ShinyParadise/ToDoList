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

import java.util.ArrayList;

public class ListDAO implements IListDAO {
    private static SQLiteOpenHelper databaseHelper = null;

    public ListDAO(Context context) {
        databaseHelper = ToDoListDatabaseHelper.getInstance(context);
    }

    public ListModel create(@NonNull ListModel listModel) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        SQLiteStatement insertStatement = db.compileStatement(DatabaseContract.ToDoListTable.INSERT_VALUES);

        String[] args = { listModel.header, listModel.description };
        insertStatement.bindAllArgsAsStrings(args);
        insertStatement.executeInsert();

        return getLastInsertedList();
    }

    public ListModel getListByID(int listID) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor request = db.rawQuery(DatabaseContract.ToDoListTable.SELECT_LIST_BY_ID,
                new String[] { Integer.toString(listID) }
        );

        if (!request.moveToFirst()) {
            request.close();
            throw new NullPointerException();
        }

        ListModel listModel = toList(request);

        request.close();
        return listModel;
    }

    public ArrayList<ListModel> getAllLists() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor request = db.rawQuery(DatabaseContract.ToDoListTable.SELECT_ALL_LISTS, null);
        ArrayList<ListModel> lists = new ArrayList<>();

        if (request.moveToFirst()) {
            do {
                ListModel newList = toList(request);
                lists.add(newList);
            } while (request.moveToNext());
        }

        request.close();
        return lists;
    }

    @NonNull
    private ListModel getLastInsertedList() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor request = db.rawQuery(DatabaseContract.ToDoListTable.SELECT_LAST_ITEM, null);


        request.moveToFirst();
        ListModel lastList = toList(request);

        request.close();
        return lastList;
    }

    @NonNull
    private ListModel toList(@NonNull Cursor request) {
        int id = Integer.parseInt(request.getString(0));
        String name = request.getString(1);
        String description = request.getString(2);

        return new ListModel(id, name, description);
    }
}
