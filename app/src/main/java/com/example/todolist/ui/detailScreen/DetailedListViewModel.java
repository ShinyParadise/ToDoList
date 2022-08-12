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

public class DetailedListViewModel extends ViewModel {
    private final int listID;
    private String listHeader;

    private final IListItemRepository listItemRepository;
    private final IListRepository listRepository;

    private ArrayList<ListItem> listItems;

    public DetailedListViewModel(IListItemRepository listItemRepository,
                                 IListRepository listRepository,
                                 @NonNull ToDoList toDoList) {
        this.listID = toDoList.getID();
        this.listHeader = toDoList.getHeader();
        this.listItemRepository = listItemRepository;
        this.listRepository = listRepository;
        listItems = new ArrayList<>();
    }

    public void insertListItem(String listItemDescription) {
        listItemRepository.insertListItem(listID, listItemDescription);
    }

    public void changeListItemState(int position) {
        ListItem listItem = listItems.get(position);

        boolean invertedState = !listItem.getState();
        listItem.setState(invertedState);

        listItemRepository.changeListItemState(listItem);

        Collections.sort(listItems, new ListItemComparator());
    }

    public void fetchListItems() {
        listItems = listItemRepository.getListItems(listID);
        Collections.sort(listItems, new ListItemComparator());
    }

    public ArrayList<ListItem> getListItems() {
        return listItems;
    }

    public String getHeader() {
        return listHeader;
    }
}
