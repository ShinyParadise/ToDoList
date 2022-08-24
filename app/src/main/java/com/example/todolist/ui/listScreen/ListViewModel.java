package com.example.todolist.ui.listScreen;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.todolist.dto.ToDoList;
import com.example.todolist.repositories.listRepository.IListRepository;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

public class ListViewModel extends ViewModel {
    public LiveData<ArrayList<ToDoList>> toDoListsLiveData;
    private final MutableLiveData<ArrayList<ToDoList>> _toDoListsLiveData;

    private final IListRepository listRepository;

    private final ExecutorService executor;

    public ListViewModel(IListRepository listRepository, ExecutorService executor) {
        this.listRepository = listRepository;
        this.executor = executor;
        _toDoListsLiveData = new MutableLiveData<>(new ArrayList<>());
        toDoListsLiveData = _toDoListsLiveData;
    }

    public void fetchLists() {
        executor.execute(() -> {
            _toDoListsLiveData.postValue(listRepository.getAllLists());
        });
    }

    public void insertNewList(String listName, String listDescription) {
        executor.execute(() -> {
            listRepository.insertToDoList(listName, listDescription);
        });
    }
}
