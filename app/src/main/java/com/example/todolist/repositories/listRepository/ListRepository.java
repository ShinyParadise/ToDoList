package com.example.todolist.repositories.listRepository;

import androidx.annotation.NonNull;

import com.example.todolist.dao.list.IListDAO;
import com.example.todolist.db.models.ListModel;
import com.example.todolist.dto.ToDoList;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class ListRepository implements IListRepository {
    private final IListDAO listDAO;

    public ListRepository(IListDAO listDAO) {
        this.listDAO = listDAO;
    }

    public ToDoList insertToDoList(String listName, String listDescription) {
        ListModel listModel = new ListModel(listName, listDescription);

        try {
            listModel = listDAO.create(listModel);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return convertListModelToList(listModel);
    }

    public ArrayList<ToDoList> getAllLists() {
        ArrayList<ToDoList> toDoLists = new ArrayList<>();
        ArrayList<ListModel> listModels = new ArrayList<>();

        try {
            listModels = listDAO.getAllLists();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        for (ListModel listModel : listModels) {
            ToDoList list = convertListModelToList(listModel);
            toDoLists.add(list);
        }

        return toDoLists;
    }

    public String getListHeader(int listID) {
        ListModel listModel = new ListModel(listID);

        try {
            listModel = listDAO.getListByID(listID);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        return listModel.header;
    }

    @NonNull
    private ToDoList convertListModelToList(@NonNull ListModel listModel) {
        return new ToDoList(listModel.id, listModel.header, listModel.description);
    }
}
