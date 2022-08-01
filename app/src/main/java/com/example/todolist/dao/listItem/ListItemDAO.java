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

        if (request.moveToFirst()){
            int id = Integer.parseInt(request.getString(0));
            String description = request.getString(1);
            int fk_list = request.getInt(2);
            int isChecked = request.getInt(3);

            ListItemModel newListItem = new ListItemModel(id, description, fk_list);
            if (isChecked == 1) newListItem.is_checked = true;

            listItems.add(newListItem);

            while (request.moveToNext()) {
                id = Integer.parseInt(request.getString(0));
                description = request.getString(1);
                fk_list = request.getInt(2);
                isChecked = request.getInt(3);

                newListItem = new ListItemModel(id, description, fk_list);
                if (isChecked == 1) newListItem.is_checked = true;
                listItems.add(newListItem);
            }
        }

        request.close();
        return listItems;
    }

    public void create(ListItemModel listItem) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        SQLiteStatement insertStatement = db.compileStatement(DatabaseContract.ListItemTable.INSERT_VALUES);

        String[] args = {
                listItem.description,
                String.valueOf(listItem.fk_list)
        };

        insertStatement.bindAllArgsAsStrings(args);
        insertStatement.executeInsert();
    }

    public ListItemModel update(@NonNull ListItemModel listItem) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        SQLiteStatement updateStatement = db.compileStatement(
                DatabaseContract.ListItemTable.UPDATE_LIST_ITEM
        );

        String newStateString = (listItem.is_checked) ? "1" : "0";

        String[] args = {
                listItem.description,
                newStateString,
                String.valueOf(listItem.id)
        };
        updateStatement.bindAllArgsAsStrings(args);

        updateStatement.executeUpdateDelete();

        return listItem;
    }

    public ListItemModel getListItemByID(int listItemID) {
        ListItemModel listItemModel = new ListItemModel();

        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor request = db.rawQuery(DatabaseContract.ListItemTable.SELECT_LIST_ITEM_BY_ID,
                new String[] { Integer.toString(listItemID) }
        );

        if (!request.moveToFirst()) {
            request.close();
            throw new NullPointerException();
        }

        listItemModel.id = request.getInt(0);
        listItemModel.description = request.getString(1);
        listItemModel.is_checked = request.getInt(2) != 0;
        listItemModel.fk_list = request.getInt(3);

        request.close();
        return listItemModel;
    }
}
