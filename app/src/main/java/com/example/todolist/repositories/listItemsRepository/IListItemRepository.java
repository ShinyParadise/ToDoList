package com.example.todolist.repositories.listitemsrepository;

import com.example.todolist.dto.ListItem;

import java.util.ArrayList;

public interface IListItemRepository {
    ArrayList<ListItem> getListItems(int listID);
    void insertListItem(int listID, String listItem);
    void changeListItemState(ListItem listItem);
}
