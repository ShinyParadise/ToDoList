package com.example.todolist.dao.listItem;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.NonNull;

import com.example.todolist.db.DatabaseContract;
import com.example.todolist.db.ToDoListDatabaseHelper;
import com.example.todolist.db.models.ListItemModel;

import java.util.ArrayList;

public class ListItemDAO implements IListItemDAO {
    private static SQLiteOpenHelper databaseHelper = null;

    public ListItemDAO(Context context) {
        databaseHelper = ToDoListDatabaseHelper.getInstance(context);
    }

    public ArrayList<ListItemModel> getAllListItems(int listID) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor request = db.rawQuery(
                DatabaseContract.SELECT_SINGLE_LIST_ITEMS,
                new String[] { Integer.toString(listID) });

        ArrayList<ListItemModel> listItems = new ArrayList<>();

        if (request.moveToFirst()) {
            do {
                ListItemModel newListItem = toListItem(request);
                listItems.add(newListItem);
            } while (request.moveToNext());
        }

        request.close();
        return listItems;
    }

    public ListItemModel create(@NonNull ListItemModel listItem) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        SQLiteStatement insertStatement = db.compileStatement(DatabaseContract.ListItemTable.INSERT_VALUES);

        String[] args = {
                listItem.description,
                String.valueOf(listItem.listID)
        };

        insertStatement.bindAllArgsAsStrings(args);
        insertStatement.executeInsert();

        return getLastInsertedListItem();
    }

    public ListItemModel update(@NonNull ListItemModel listItem) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        SQLiteStatement updateStatement = db.compileStatement(
                DatabaseContract.ListItemTable.UPDATE_LIST_ITEM
        );

        String[] args = {
                listItem.description,
                String.valueOf(listItem.isChecked),
                String.valueOf(listItem.updatedAt),
                String.valueOf(listItem.id)
        };
        updateStatement.bindAllArgsAsStrings(args);

        updateStatement.executeUpdateDelete();

        return getListItemByID(listItem.id);
    }

    public ListItemModel getListItemByID(int listItemID) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor request = db.rawQuery(DatabaseContract.ListItemTable.SELECT_LIST_ITEM_BY_ID,
                new String[] { Integer.toString(listItemID) }
        );

        if (!request.moveToFirst()) {
            request.close();
            throw new NullPointerException();
        }

        ListItemModel listItemModel = toListItem(request);

        request.close();
        return listItemModel;
    }

    @NonNull
    private ListItemModel getLastInsertedListItem() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor request = db.rawQuery(DatabaseContract.ListItemTable.SELECT_LAST_ITEM, null);

        request.moveToFirst();
        ListItemModel lastList = toListItem(request);

        request.close();
        return lastList;
    }

    @NonNull
    private ListItemModel toListItem(@NonNull Cursor request) {
        int id = request.getInt(0);
        String description = request.getString(1);
        int isChecked = request.getInt(2);
        int fk_list = request.getInt(3);
        long last_update = request.getLong(4);

        return new ListItemModel(id, description, isChecked, fk_list, last_update);
    }
}
