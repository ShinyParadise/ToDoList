package com.example.todolist.ui.detailscreen;

import androidx.lifecycle.ViewModel;

import com.example.todolist.dto.ListItem;
import com.example.todolist.repositories.listrepository.IListRepository;

import java.util.ArrayList;

public class DetailedListViewModel extends ViewModel {
    private final int listID;
    private String listHeader;

    private final IListRepository listRepository;

    private ArrayList<ListItem> listItems;

    public DetailedListViewModel(IListRepository listRepository, int listID) {
        this.listID = listID;
        this.listRepository = listRepository;
        listHeader = listRepository.getListHeader(listID);
        listItems = new ArrayList<>();
    }

    public void insertListItem(String listItemDescription) {
        listRepository.insertListItem(listID, listItemDescription);
    }

    public void changeListItemState(int position) {
        ListItem listItem = listItems.get(position);

        boolean invertedState = !listItem.getState();
        listItem.setState(invertedState);

        listRepository.changeListItemState(listItem.getID(), invertedState);
    }

    public void fetchListItems() {
        listItems = listRepository.getListItems(listID);
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
