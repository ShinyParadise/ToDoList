package com.example.todolist.db.listrepository;

import com.example.todolist.db.models.ListModel;

import java.util.ArrayList;

public interface IListRepository {
    void insertToDoListWithEntries(String listName, String listDescription, String[] fkEntries);
    void insertToDoListWithoutEntries(String listName, String listDescription);
    void insertEntries(String entryDescription);
    ArrayList<ListModel> getAllLists();
}
