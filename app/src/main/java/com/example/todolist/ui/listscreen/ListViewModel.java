package com.example.todolist.ui.listscreen;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.todolist.dto.ToDoList;
import com.example.todolist.repositories.listrepository.IListRepository;
import com.example.todolist.repositories.listrepository.ListRepository;
import com.example.todolist.db.models.ListModel;

import java.util.ArrayList;

public class ListViewModel extends ViewModel {
    private IListRepository listRepository;

    private ArrayList<ToDoList> toDoLists;

    public ListViewModel(Context context) {
        listRepository = new ListRepository(context);
    }

    public void initiateLists() {
        listRepository.insertToDoListWithoutItems("123", "321");
        listRepository.insertToDoListWithoutItems("abc", "def");
    }

    public void fetchLists() {
        toDoLists = new ArrayList<>(listRepository.getAllLists());
    }

    public void insertNewList(String listName, String listDescription) {
        listRepository.insertToDoListWithoutItems(listName, listDescription);
    }

    public ArrayList<ToDoList> getLists() {
        return toDoLists;
    }
}
