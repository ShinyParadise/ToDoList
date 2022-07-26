package com.example.todolist.repositories.listrepository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.todolist.db.DatabaseContract;
import com.example.todolist.db.ToDoListDatabaseHelper;
import com.example.todolist.dto.ListItem;
import com.example.todolist.dto.ToDoList;

import java.util.ArrayList;


public class ListRepository implements IListRepository {
    private static SQLiteOpenHelper databaseHelper = null;

    public ListRepository(Context context) {
        databaseHelper = ToDoListDatabaseHelper.getInstance(context);
    }

    public void insertListItem(int listID, String listItem) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        SQLiteStatement insertStatement = db.compileStatement(DatabaseContract.ListItemTable.INSERT_VALUES);

        String[] args = { listItem, String.valueOf(listID) };
        insertStatement.bindAllArgsAsStrings(args);
        insertStatement.executeInsert();
    }

    public void changeListItemState(int listItemID, boolean newState) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        SQLiteStatement updateStatement = db.compileStatement(
                DatabaseContract.ListItemTable.UPDATE_LIST_ITEM_STATE
        );

        String newStateString = (newState) ? "1" : "0";

        String[] args = { newStateString, String.valueOf(listItemID) };
        updateStatement.bindAllArgsAsStrings(args);
        updateStatement.executeUpdateDelete();
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

    public ArrayList<ListItem> getListItems(int listID) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor request = db.rawQuery(
                DatabaseContract.SELECT_SINGLE_LIST_ITEMS,
                new String[] { Integer.toString(listID) });
        ArrayList<ListItem> listItems = new ArrayList<>();

        if (request.moveToFirst()){
            int id = Integer.parseInt(request.getString(0));
            String description = request.getString(1);
            int fk_list = request.getInt(2);
            int isChecked = request.getInt(3);

            ListItem newListItem = new ListItem(id, description, fk_list);
            if (isChecked == 1) newListItem.setState(true);

            listItems.add(newListItem);

            while(request.moveToNext()) {
                id = Integer.parseInt(request.getString(0));
                description = request.getString(1);
                fk_list = request.getInt(2);
                isChecked = request.getInt(3);

                newListItem = new ListItem(id, description, fk_list);
                if (isChecked == 1) newListItem.setState(true);
                listItems.add(newListItem);
            }
        }

        request.close();
        return listItems;
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
