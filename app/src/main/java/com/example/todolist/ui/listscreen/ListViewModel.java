package com.example.todolist.ui.listscreen;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.todolist.db.listrepository.IListRepository;
import com.example.todolist.db.listrepository.ListRepository;
import com.example.todolist.db.models.ListModel;

import java.util.ArrayList;

public class ListViewModel extends ViewModel {
    private IListRepository listRepository;

    private ArrayList<ListModel> toDoLists;

    public ListViewModel(Context context) {
        listRepository = new ListRepository(context);
    }

    public void initiateLists() {
        listRepository.insertListItems("do something");
        listRepository.insertListItems("do something else");
        String[] exampleListItems = { "1", "2" };
        listRepository.insertToDoListWithItems("123", "321", exampleListItems);
        listRepository.insertToDoListWithItems("abc", "def", exampleListItems);
    }

    public void fetchLists() {
        toDoLists = new ArrayList<>(listRepository.getAllLists());
    }

    public void insertNewList(String listName, String listDescription) {
        // пофиксить добавление с null значениями внешнего ключа
        listRepository.insertToDoListWithItems(listName, listDescription, new String[] {"1", "2"});
    }

    public ArrayList<ListModel> getLists() {
        return toDoLists;
    }
}
