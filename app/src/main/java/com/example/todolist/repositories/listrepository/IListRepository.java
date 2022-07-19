package com.example.todolist.repositories.listrepository;

import com.example.todolist.db.models.ListItemModel;
import com.example.todolist.db.models.ListModel;
import com.example.todolist.dto.ListItem;
import com.example.todolist.dto.ToDoList;

import java.util.ArrayList;

public interface IListRepository {
    void insertToDoListWithoutItems(String listName, String listDescription);
    void insertListItems(String listName, String[] listItems);
    ArrayList<ToDoList> getAllLists();
    ArrayList<ListItem> getListItems(int listID);
    String getListHeader(int listID);
    int getListID(String listHeader);
}
