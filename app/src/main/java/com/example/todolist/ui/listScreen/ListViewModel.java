package com.example.todolist.ui.listScreen;
import androidx.lifecycle.ViewModel;

import com.example.todolist.dto.ToDoList;
import com.example.todolist.repositories.listRepository.IListRepository;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

public class ListViewModel extends ViewModel {
    private final IListRepository listRepository;

    private final ExecutorService executor;

    private ArrayList<ToDoList> toDoLists;

    public ListViewModel(IListRepository listRepository, ExecutorService executor) {
        this.listRepository = listRepository;
        this.executor = executor;
    }

    public void fetchLists() {
        executor.submit(() -> {
            toDoLists = new ArrayList<>(listRepository.getAllLists());
        });
    }

    public void insertNewList(String listName, String listDescription) {
        executor.submit(() -> {
            listRepository.insertToDoList(listName, listDescription);
        });
    }

    public ArrayList<ToDoList> getLists() {
        return toDoLists;
    }
}
