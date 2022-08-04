package com.example.todolist.repositories.listRepository;

import com.example.todolist.dto.ToDoList;

import java.util.ArrayList;

public interface IListRepository {
    ToDoList insertToDoList(String listName, String listDescription);
    ArrayList<ToDoList> getAllLists();
    String getListHeader(int listID);
}
