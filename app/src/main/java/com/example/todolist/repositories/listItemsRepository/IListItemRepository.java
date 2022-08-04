package com.example.todolist.repositories.listItemsRepository;

import com.example.todolist.dto.ListItem;

import java.util.ArrayList;

public interface IListItemRepository {
    ArrayList<ListItem> getListItems(int listID);
    ListItem insertListItem(int listID, String listItem);
    void changeListItemState(ListItem listItem);
}
