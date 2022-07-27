package com.example.todolist.repositories.listitemsrepository;

import android.content.Context;

import com.example.todolist.dao.listitem.IListItemDAO;
import com.example.todolist.dao.listitem.ListItemDAO;
import com.example.todolist.dto.ListItem;

import java.util.ArrayList;

// TODO: Репозиторий просто дублирует все методы DAO?
public class ListItemRepository implements IListItemRepository {
    private final IListItemDAO listItemDAO;

    public ListItemRepository(Context context) {
        // TODO: Какой принцип SOLID здесь нарушен?
        listItemDAO = new ListItemDAO(context);
    }

    public ArrayList<ListItem> getListItems(int listID) {
        return listItemDAO.getAllListItems(listID);
    }


    public void insertListItem(int listID, String listItem) {
        listItemDAO.insertListItem(listID, listItem);
    }

    public void changeListItemState(int listItemID, boolean newState) {
        listItemDAO.changeListItemState(listItemID, newState);
    }

    public String getListHeader(int listID) {
        return listItemDAO.getListHeader(listID);
    }
}
