package com.example.todolist.ui.detailScreen;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.todolist.dto.ListItem;
import com.example.todolist.dto.ListItemComparator;
import com.example.todolist.dto.ToDoList;
import com.example.todolist.repositories.listItemsRepository.IListItemRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutorService;

public class DetailedListViewModel extends ViewModel {
    public LiveData<ArrayList<ListItem>> listItemsLiveData;
    private final MutableLiveData<ArrayList<ListItem>> listItemsMutableLiveData;

    private final int listID;
    private final String listHeader;
    public static final String TAG = "DetailsViewModel";

    private final IListItemRepository listItemRepository;

    private final ExecutorService executor;

    public DetailedListViewModel(IListItemRepository listItemRepository,
                                 @NonNull ToDoList toDoList,
                                 ExecutorService executor) {
        this.listID = toDoList.getID();
        this.listHeader = toDoList.getHeader();
        this.listItemRepository = listItemRepository;
        this.executor = executor;
        listItemsMutableLiveData = new MutableLiveData<>(new ArrayList<>());
        listItemsLiveData = (LiveData<ArrayList<ListItem>>) listItemsMutableLiveData;
    }

    public void insertListItem(String listItemDescription) {
        executor.execute(() -> {
            listItemRepository.insertListItem(listID, listItemDescription);
        });
    }

    public void changeListItemState(int position) {
        executor.execute(() -> {
            ArrayList<ListItem> listItems = listItemsMutableLiveData.getValue();
            ListItem listItem;

            if (listItems == null) {
                Log.e(TAG, "on change list item state: list is null");
                return;
            }

            listItem = listItems.get(position);
            boolean invertedState = !listItem.getState();
            listItem.setState(invertedState);
            listItemRepository.changeListItemState(listItem);

            Collections.sort(listItems, new ListItemComparator());
            listItemsMutableLiveData.postValue(listItems);
        });
    }

    public void fetchListItems() {
        executor.execute(() -> {
            ArrayList<ListItem> listItems = listItemRepository.getListItems(listID);
            Collections.sort(listItems, new ListItemComparator());
            listItemsMutableLiveData.postValue(listItems);
        });
    }

    public String getHeader() {
        return listHeader;
    }
}
