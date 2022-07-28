package com.example.todolist.dao.list;

import com.example.todolist.db.models.ListModel;

import java.util.ArrayList;

public interface IListDAO {
    ArrayList<ListModel> getAllLists();
    ListModel getListByID(int listID);
    void create(ListModel listModel);
}
