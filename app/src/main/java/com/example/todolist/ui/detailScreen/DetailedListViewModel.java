package com.example.todolist.ui.detailscreen;

import androidx.lifecycle.ViewModel;

import com.example.todolist.dto.ListItem;
import com.example.todolist.repositories.listitemsrepository.IListItemRepository;
import com.example.todolist.repositories.listrepository.IListRepository;

import java.util.ArrayList;

public class DetailedListViewModel extends ViewModel {
    private final int listID;
    private String listHeader;

    private final IListItemRepository listItemRepository;
    private final IListRepository listRepository;

    private ArrayList<ListItem> listItems;

    public DetailedListViewModel(IListItemRepository listItemRepository,
                                 IListRepository listRepository,
                                 int listID,
                                 String listHeader) {
        this.listID = listID;
        this.listItemRepository = listItemRepository;
        this.listRepository = listRepository;
        this.listHeader = listHeader;
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
    }

    public void fetchListItems() {
        listItems = listItemRepository.getListItems(listID);
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
}
