package com.example.todolist.ui.detailScreen;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.todolist.dto.ListItem;
import com.example.todolist.dto.ListItemComparator;
import com.example.todolist.dto.ToDoList;
import com.example.todolist.repositories.listItemsRepository.IListItemRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutorService;

public class DetailedListViewModel extends ViewModel {
    private final int listID;
    private final String listHeader;

    private final IListItemRepository listItemRepository;

    private final MutableLiveData<ArrayList<ListItem>> listItemsLiveData;

    private final ExecutorService executor;

    public DetailedListViewModel(IListItemRepository listItemRepository,
                                 @NonNull ToDoList toDoList,
                                 ExecutorService executor) {
        this.listID = toDoList.getID();
        this.listHeader = toDoList.getHeader();
        this.listItemRepository = listItemRepository;
        this.executor = executor;
        listItemsLiveData = new MutableLiveData<>();
    }

    public void insertListItem(String listItemDescription) {
        executor.execute(() -> {
            listItemRepository.insertListItem(listID, listItemDescription);
        });
    }

    public void changeListItemState(int position) {
        executor.execute(() -> {
            ListItem listItem = listItemsLiveData.getValue().get(position);

            boolean invertedState = !listItem.getState();
            listItem.setState(invertedState);
            listItemRepository.changeListItemState(listItem);

            ArrayList<ListItem> listItems = listItemsLiveData.getValue();
            Collections.sort(listItems, new ListItemComparator());
            listItemsLiveData.postValue(listItems);
        });
    }

    public void fetchListItems() {
        executor.execute(() -> {
            ArrayList<ListItem> listItems = listItemRepository.getListItems(listID);
            Collections.sort(listItems, new ListItemComparator());
            listItemsLiveData.postValue(listItems);
        });
    }

    public LiveData<ArrayList<ListItem>> getListItems() {
        return listItemsLiveData;
    }

    public String getHeader() {
        return listHeader;
    }
}
