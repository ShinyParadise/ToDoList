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
import com.example.todolist.db.models.ListModel;

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
                int id = Integer.parseInt(request.getString(0));
                String description = request.getString(1);
                int fk_list = request.getInt(2);
                int isChecked = request.getInt(3);

                ListItemModel newListItem = new ListItemModel(id, description, isChecked, fk_list);

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
                String.valueOf(listItem.fk_list)
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
                String.valueOf(listItem.is_checked),
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

        ListItemModel listItemModel = new ListItemModel(
                request.getInt(0),
                request.getString(1),
                request.getInt(2),
                request.getInt(3)
        );


        request.close();
        return listItemModel;
    }

    @NonNull
    private ListItemModel getLastInsertedListItem() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor request = db.rawQuery(DatabaseContract.ListItemTable.SELECT_LAST_ITEM, null);

        request.moveToFirst();
        ListItemModel lastList = new ListItemModel(
                request.getInt(0),
                request.getString(1),
                request.getInt(2),
                request.getInt(3)
        );

        request.close();
        return lastList;
    }
}
