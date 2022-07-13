package com.example.todolist.ui.detailscreen;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.todolist.db.listrepository.IListRepository;
import com.example.todolist.db.listrepository.ListRepository;
import com.example.todolist.db.models.ListItemModel;

import java.util.ArrayList;

public class DetailedListViewModel extends ViewModel {
    private int listID;

    private IListRepository listRepository;

    public DetailedListViewModel(Context context, int listID) {
        this.listID = listID;
        listRepository = new ListRepository(context);
    }

    public ArrayList<ListItemModel> getListItems() {
        return listRepository.getListItems(listID);
    }
}
