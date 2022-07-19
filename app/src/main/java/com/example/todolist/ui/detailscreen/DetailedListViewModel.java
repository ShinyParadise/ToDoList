package com.example.todolist.ui.detailscreen;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.todolist.db.listrepository.IListRepository;
import com.example.todolist.db.listrepository.ListRepository;
import com.example.todolist.db.models.ListItemModel;

import java.util.ArrayList;

public class DetailedListViewModel extends ViewModel {
    private int listID;
    private String listHeader;

    private IListRepository listRepository;

    private ArrayList<ListItemModel> listItems;

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

    public ArrayList<ListItemModel> getListItems() {
        return listItems;
    }

    public String getHeader() {
        return listHeader;
    }
}
