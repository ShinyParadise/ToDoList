package com.example.todolist.repositories.listrepository;

import com.example.todolist.dto.ToDoList;

import java.util.ArrayList;

public interface IListRepository {
    void insertToDoList(String listName, String listDescription);
    ArrayList<ToDoList> getAllLists();
    String getListHeader(int listID);
}
