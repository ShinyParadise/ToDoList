package com.example.todolist.ui.listScreen;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.todolist.dao.list.ListDAO;
import com.example.todolist.dto.ToDoList;
import com.example.todolist.repositories.listRepository.IListRepository;
import com.example.todolist.repositories.listRepository.ListRepository;

import java.util.ArrayList;

public class ListViewModel extends ViewModel {
    private final IListRepository listRepository;

    private ArrayList<ToDoList> toDoLists;

    public ListViewModel(Context context) {
        listRepository = new ListRepository(new ListDAO(context));
    }

    public void fetchLists() {
        toDoLists = new ArrayList<>(listRepository.getAllLists());
    }

    public void insertNewList(String listName, String listDescription) {
        listRepository.insertToDoList(listName, listDescription);
    }

    public ArrayList<ToDoList> getLists() {
        return toDoLists;
    }

    public String getListHeader(int listID) {
        return listRepository.getListHeader(listID);
    }
}
