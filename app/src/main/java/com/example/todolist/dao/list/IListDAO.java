package com.example.todolist.dao.list;

import com.example.todolist.db.models.ListModel;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public interface IListDAO {
    ArrayList<ListModel> getAllLists() throws ExecutionException, InterruptedException;
    ListModel getListByID(int listID) throws ExecutionException, InterruptedException;
    ListModel create(ListModel listModel) throws ExecutionException, InterruptedException;
}
