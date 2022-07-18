package com.example.todolist.db.listrepository;

import com.example.todolist.db.models.ListItemModel;
import com.example.todolist.db.models.ListModel;

import java.util.ArrayList;

public interface IListRepository {
    void insertToDoListWithoutItems(String listName, String listDescription);
    void insertListItems(String listName, String[] listItems);
    ArrayList<ListModel> getAllLists();
    ArrayList<ListItemModel> getListItems(int listID);
    String getListHeader(int listID);
    int getListID(String listHeader);
}
