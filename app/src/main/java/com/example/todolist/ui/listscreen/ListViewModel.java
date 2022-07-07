package com.example.todolist.ui.listscreen;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.todolist.db.ListRepository;
import com.example.todolist.db.models.ListModel;

import java.util.ArrayList;

public class ListViewModel extends ViewModel {
    private ListRepository listRepository;

    private ArrayList<ListModel> toDoLists;

    public ListViewModel(Context context) {
        listRepository = new ListRepository(context);
    }

    public void initiateLists() {
        listRepository.insertEntries("do something");
        listRepository.insertEntries("do something else");
        String[] exampleEntries = { "1", "2" };
        listRepository.insertToDoListWithEntries("123", "321", exampleEntries);
        listRepository.insertToDoListWithEntries("abc", "def", exampleEntries);
    }

    public void fetchLists() {
        toDoLists = new ArrayList<>(listRepository.getAllLists());
    }

    public void insertNewList(String listName, String listDescription) {
        // пофиксить добавление с null значениями внешнего ключа
        listRepository.insertToDoListWithEntries(listName, listDescription, new String[] {"1", "2"});
    }

    public ArrayList<ListModel> getLists() {
        return toDoLists;
    }
}
