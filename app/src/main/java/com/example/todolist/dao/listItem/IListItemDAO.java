package com.example.todolist.dao.listItem;

import com.example.todolist.db.models.ListItemModel;

import java.util.ArrayList;

public interface IListItemDAO {
    ArrayList<ListItemModel> getAllListItems(int listID);
    ListItemModel create(ListItemModel listItem);
    ListItemModel update(ListItemModel listItem);
    ListItemModel getListItemByID(int listItemID);
}
