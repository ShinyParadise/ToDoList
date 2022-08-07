package com.example.todolist.db.models;

public class ListItemModel {
    public ListItemModel(int listID, String listItemDescription) {
        fk_list = listID;
        description = listItemDescription;
    }

    public ListItemModel(int id, String description, int state, int listID, long last_update) {
        this.id = id;
        this.description = description;
        is_checked = state;
        fk_list = listID;
        this.last_update = last_update;
    }

    public int id;
    public String description;
    public int is_checked = 0;
    public int fk_list;
    public long last_update;
}
