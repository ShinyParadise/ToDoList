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

        listModel = listDAO.create(listModel);

        return convertListModelToList(listModel);
    }

    public ArrayList<ToDoList> getAllLists() {
        ArrayList<ToDoList> toDoLists = new ArrayList<>();
        ArrayList<ListModel> listModels = listDAO.getAllLists();

        for (ListModel listModel : listModels) {
            ToDoList list = convertListModelToList(listModel);
            toDoLists.add(list);
        }

        return toDoLists;
    }

    @NonNull
    private ToDoList convertListModelToList(@NonNull ListModel listModel) {
        return new ToDoList(listModel.id, listModel.header, listModel.description);
    }
}
