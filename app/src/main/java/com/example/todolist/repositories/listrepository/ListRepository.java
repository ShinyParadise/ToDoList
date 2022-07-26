package com.example.todolist.repositories.listrepository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.todolist.dao.list.IListDAO;
import com.example.todolist.dao.list.ListDAO;
import com.example.todolist.db.DatabaseContract;
import com.example.todolist.db.ToDoListDatabaseHelper;
import com.example.todolist.dto.ListItem;
import com.example.todolist.dto.ToDoList;

import java.util.ArrayList;


public class ListRepository implements IListRepository {
    private final IListDAO listDAO;

    public ListRepository(Context context) {
        listDAO = new ListDAO(context);
    }

    public void insertToDoListWithoutItems(String listName, String listDescription) {
        listDAO.insertToDoListWithoutItems(listName, listDescription);
    }

    public ArrayList<ToDoList> getAllLists() {
        return listDAO.getAllLists();
    }

    public String getListHeader(int listID) {
        return listDAO.getListHeader(listID);
    }

    public int getListID(String listName) {
        return listDAO.getListID(listName);
    }
}
