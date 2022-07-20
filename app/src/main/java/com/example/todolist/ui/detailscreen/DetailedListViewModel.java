package com.example.todolist.ui.detailscreen;

import androidx.lifecycle.ViewModel;

import com.example.todolist.dto.ListItem;
import com.example.todolist.repositories.listrepository.IListRepository;
import com.example.todolist.db.models.ListItemModel;

import java.util.ArrayList;

public class DetailedListViewModel extends ViewModel {
    private int listID;
    private String listHeader;

    private IListRepository listRepository;

    private ArrayList<ListItem> listItems;

    public DetailedListViewModel(IListRepository listRepository, int listID) {
        this.listID = listID;
        this.listRepository = listRepository;
        listHeader = listRepository.getListHeader(listID);
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

    public void insertListItem(String listItemDescription) {
        listRepository.insertListItem(listID, listItemDescription);
    }
}
