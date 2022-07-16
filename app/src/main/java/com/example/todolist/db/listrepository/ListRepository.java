package com.example.todolist.db.listrepository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.todolist.db.DatabaseContract;
import com.example.todolist.db.ToDoListDatabaseHelper;
import com.example.todolist.db.dbo.List;
import com.example.todolist.db.models.ListItemModel;
import com.example.todolist.db.models.ListModel;

import java.util.ArrayList;


public class ListRepository implements IListRepository {
    private static volatile SQLiteOpenHelper databaseHelper = null;

    public ListRepository(Context context) {
        if (databaseHelper == null) {
            synchronized (ToDoListDatabaseHelper.class) {
                if (databaseHelper == null)
                    databaseHelper = new ToDoListDatabaseHelper(context.getApplicationContext());
            }
        }
    }

    public void insertToDoListWithItems(String listName, String listDescription, String[] listItemsIDs) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        SQLiteStatement insertStatement = db.compileStatement(DatabaseContract.ToDoListTable.INSERT_VALUES);

        for (String entry: listItemsIDs) {
            String[] args = {listName, listDescription, entry};
            insertStatement.bindAllArgsAsStrings(args);
            insertStatement.executeInsert();
        }
    }

    // TODO: fix this to work with null values
    public void insertToDoListWithoutItems(String listName, String listDescription) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        SQLiteStatement insertStatement = db.compileStatement(DatabaseContract.ToDoListTable.INSERT_VALUES);

        String[] args = { listName, listDescription, DatabaseContract.NULL };
        insertStatement.bindAllArgsAsStrings(args);
        insertStatement.executeInsert();
    }

    public void insertListItems(String listItem) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        SQLiteStatement insertStatement = db.compileStatement(DatabaseContract.ListItemTable.INSERT_VALUES);

        insertStatement.bindString(1, listItem);
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
                newList = new ListModel();
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

            listItems.add(newListItem);
            while(request.moveToNext()) {
                newListItem = new ListItemModel();
                newListItem.id = Integer.parseInt(request.getString(0));
                newListItem.description = request.getString(1);

                listItems.add(newListItem);
            }
        }

        request.close();
        return listItems;
    }
}
