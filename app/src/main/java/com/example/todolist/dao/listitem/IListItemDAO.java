package com.example.todolist.dao.listitem;

import com.example.todolist.dto.ListItem;

import java.util.ArrayList;

public interface IListItemDAO {
    ArrayList<ListItem> getAllListItems(int listID);
    void insertListItem(int listID, String listItem);
    void changeListItemState(int listItemID, boolean newState);
    String getListHeader(int listID);
}
