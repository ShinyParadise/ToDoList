package com.example.todolist.db.models;

public class ListItemModel {
    public ListItemModel(int listID, String listItemDescription) {
        this.listID = listID;
        description = listItemDescription;
    }

    public ListItemModel(int id, String description, int state, int listID, long updatedAt) {
        this.id = id;
        this.description = description;
        isChecked = state;
        this.listID = listID;
        this.updatedAt = updatedAt;
    }

    public int id;
    public String description;
    public int isChecked = 0;
    public int listID;
    public long updatedAt;
}
