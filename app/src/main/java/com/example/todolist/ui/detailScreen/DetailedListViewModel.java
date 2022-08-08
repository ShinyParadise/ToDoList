package com.example.todolist.ui.detailScreen;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.todolist.dto.ListItem;
import com.example.todolist.dto.ToDoList;
import com.example.todolist.repositories.listItemsRepository.IListItemRepository;
import com.example.todolist.repositories.listRepository.IListRepository;

import java.util.ArrayList;

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
        listItems.sort(DetailedListViewModel::listItemsCompare);
    }

    public void fetchListItems() {
        listItems = listItemRepository.getListItems(listID);
        listItems.sort(DetailedListViewModel::listItemsCompare);
    }

    public void fetchHeader() {
        listHeader = listRepository.getListHeader(listID);
    }

    public ArrayList<ListItem> getListItems() {
        return listItems;
    }

    public String getHeader() {
        return listHeader;
    }

    private static int listItemsCompare(ListItem firstItem, ListItem secondItem) {
        if (!firstItem.getState() && secondItem.getState())
            return -1;
        if (firstItem.getLastUpdate().isBefore(secondItem.getLastUpdate())) {
            return 1;
        }
        return 0;
    }
}
