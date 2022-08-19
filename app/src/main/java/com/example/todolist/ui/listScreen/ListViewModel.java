package com.example.todolist.ui.listScreen;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.todolist.dto.ToDoList;
import com.example.todolist.repositories.listRepository.IListRepository;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

public class ListViewModel extends ViewModel {
    private final IListRepository listRepository;

    private final ExecutorService executor;

    private final MutableLiveData<ArrayList<ToDoList>> toDoLists;

    public ListViewModel(IListRepository listRepository, ExecutorService executor) {
        this.listRepository = listRepository;
        this.executor = executor;
        toDoLists = new MutableLiveData<>();
    }

    public void fetchLists() {
        executor.execute(() -> {
            toDoLists.postValue(listRepository.getAllLists());
        });
    }

    public void insertNewList(String listName, String listDescription) {
        executor.execute(() -> {
            listRepository.insertToDoList(listName, listDescription);
        });
    }

    public LiveData<ArrayList<ToDoList>> getLists() {
        return toDoLists;
    }
}
