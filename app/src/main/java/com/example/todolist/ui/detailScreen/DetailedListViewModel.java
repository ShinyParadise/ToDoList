package com.example.todolist.ui.detailScreen;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.todolist.dto.ListItem;
import com.example.todolist.dto.ListItemComparator;
import com.example.todolist.dto.ToDoList;
import com.example.todolist.repositories.listItemsRepository.IListItemRepository;
import com.example.todolist.repositories.listRepository.IListRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutorService;

public class DetailedListViewModel extends ViewModel {
    private final int listID;
    private final String listHeader;

    private final IListItemRepository listItemRepository;

    private ArrayList<ListItem> listItems;

    private final ExecutorService executor;

    public DetailedListViewModel(IListItemRepository listItemRepository,
                                 @NonNull ToDoList toDoList,
                                 ExecutorService executor) {
        this.listID = toDoList.getID();
        this.listHeader = toDoList.getHeader();
        this.listItemRepository = listItemRepository;
        this.executor = executor;
        listItems = new ArrayList<>();
    }

    public void insertListItem(String listItemDescription) {
        executor.submit(() -> listItemRepository.insertListItem(listID, listItemDescription));
    }

    public void changeListItemState(int position) {
        executor.submit(() -> {
            ListItem listItem = listItems.get(position);

            boolean invertedState = !listItem.getState();
            listItem.setState(invertedState);

            listItemRepository.changeListItemState(listItem);

            Collections.sort(listItems, new ListItemComparator());
        });
    }

    public void fetchListItems() {
        executor.submit(() -> {
            listItems = listItemRepository.getListItems(listID);
            Collections.sort(listItems, new ListItemComparator());
        });
    }

    public ArrayList<ListItem> getListItems() {
        return listItems;
    }

    public String getHeader() {
        return listHeader;
    }
}
