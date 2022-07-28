package com.example.todolist.db.models;

public class ListItemModel {
    public ListItemModel() {}

    public ListItemModel(int listID, String listItemDescription) {
        fk_list = listID;
        description = listItemDescription;
    }

    public ListItemModel(int id, String description, int fk_list) {
        this.id = id;
        this.description = description;
        this.fk_list = fk_list;
        is_checked = false;
    }

    public ListItemModel(int id, String description, boolean state, int listID) {
        this.id = id;
        this.description = description;
        is_checked = state;
        fk_list = listID;
    }

    public int id;
    public String description;
    public boolean is_checked;
    public int fk_list;
}
