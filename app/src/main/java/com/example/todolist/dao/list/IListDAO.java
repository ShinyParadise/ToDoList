package com.example.todolist.dao.list;

import com.example.todolist.dto.ToDoList;

import java.util.ArrayList;

public interface IListDAO {
    void insertToDoListWithoutItems(String listName, String listDescription);
    ArrayList<ToDoList> getAllLists();
    String getListHeader(int listID);
    int getListID(String listHeader);
}
