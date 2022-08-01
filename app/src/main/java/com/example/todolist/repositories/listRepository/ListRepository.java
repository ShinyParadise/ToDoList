package com.example.todolist.repositories.listrepository;

import com.example.todolist.dao.list.IListDAO;
import com.example.todolist.db.models.ListModel;
import com.example.todolist.dto.ToDoList;

import java.util.ArrayList;


public class ListRepository implements IListRepository {
    private final IListDAO listDAO;

    public ListRepository(IListDAO listDAO) {
        this.listDAO = listDAO;
    }

    public void insertToDoList(String listName, String listDescription) {
        ListModel listModel = new ListModel(listName, listDescription);
        listDAO.create(listModel);
    }

    public ArrayList<ToDoList> getAllLists() {
        ArrayList<ToDoList> toDoLists = new ArrayList<>();

        ArrayList<ListModel> listModels = listDAO.getAllLists();
        for (ListModel listModel : listModels) {
            ToDoList list = new ToDoList(listModel.id, listModel.header, listModel.description);
            toDoLists.add(list);
        }

        return toDoLists;
    }

    public String getListHeader(int listID) {
        ListModel listModel = listDAO.getListByID(listID);
        return listModel.header;
    }
}
