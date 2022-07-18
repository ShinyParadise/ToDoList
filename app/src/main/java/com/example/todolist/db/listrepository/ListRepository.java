package com.example.todolist.db.listrepository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.todolist.db.DatabaseContract;
import com.example.todolist.db.ToDoListDatabaseHelper;
import com.example.todolist.db.models.ListItemModel;
import com.example.todolist.db.models.ListModel;

import java.util.ArrayList;


public class ListRepository implements IListRepository {
    private static volatile SQLiteOpenHelper databaseHelper = null;

    public ListRepository(Context context) {
        databaseHelper = ToDoListDatabaseHelper.getInstance(context);
    }

    public void insertListItems(String listName, String[] listItems) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        SQLiteStatement insertStatement = db.compileStatement(DatabaseContract.ToDoListTable.INSERT_VALUES);

        String listID = String.valueOf(getListID(listName));

        for (String listItem: listItems) {
            String[] args = { listItem, listID };
            insertStatement.bindAllArgsAsStrings(args);
            insertStatement.executeInsert();
        }
    }

    public void insertToDoListWithoutItems(String listName, String listDescription) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        SQLiteStatement insertStatement = db.compileStatement(DatabaseContract.ToDoListTable.INSERT_VALUES);

        String[] args = { listName, listDescription };
        insertStatement.bindAllArgsAsStrings(args);
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

            lists.add(newList);
            while(request.moveToNext()) {
                newList = new ListModel();
                newList.id = Integer.parseInt(request.getString(0));
                newList.name = request.getString(1);
                newList.description = request.getString(2);

                lists.add(newList);
            }
        }

        request.close();
        return lists;
    }

    @Override
    public ArrayList<ListItemModel> getListItems(int listID) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor request = db.rawQuery(
                DatabaseContract.SELECT_SINGLE_LIST_ITEMS,
                new String[] { Integer.toString(listID) });
        ArrayList<ListItemModel> listItems = new ArrayList<>();

        if (request.moveToFirst()){
            ListItemModel newListItem = new ListItemModel();
            newListItem.id = Integer.parseInt(request.getString(0));
            newListItem.description = request.getString(1);
            newListItem.fk_lists = request.getInt(2);

            listItems.add(newListItem);
            while(request.moveToNext()) {
                newListItem = new ListItemModel();
                newListItem.id = Integer.parseInt(request.getString(0));
                newListItem.description = request.getString(1);
                newListItem.fk_lists = request.getInt(2);

                listItems.add(newListItem);
            }
        }

        request.close();
        return listItems;
    }

    @Override
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

    public int getListID(String listName) {
        int listID;
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor request = db.rawQuery(DatabaseContract.ToDoListTable.SELECT_LIST_BY_HEADER,
                new String[] { listName }
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
