package com.example.todolist.db.models;

public class ListItemModel {
    public ListItemModel(int listID, String listItemDescription) {
        fk_list = listID;
        description = listItemDescription;
    }

    public ListItemModel(int id, String description, boolean state, int listID) {
        this.id = id;
        this.description = description;
        is_checked = state ? 1 : 0;
        fk_list = listID;
    }
    public ListItemModel(int id, String description, int state, int listID) {
        this.id = id;
        this.description = description;
        is_checked = state;
        fk_list = listID;
    }

    public int id;
    public String description;
    public int is_checked = 0;
    public int fk_list;
}
