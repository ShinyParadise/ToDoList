package com.example.todolist.db.listrepository;

import com.example.todolist.db.models.ListItemModel;
import com.example.todolist.db.models.ListModel;

import java.util.ArrayList;

public interface IListRepository {
    void insertToDoListWithItems(String listName, String listDescription, String[] listItemsIDs);
    void insertToDoListWithoutItems(String listName, String listDescription);
    void insertListItems(String listItem);
    ArrayList<ListModel> getAllLists();
    ArrayList<ListItemModel> getListItems(String header);
    String getListHeader(int listID);
}
