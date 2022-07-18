package com.example.todolist.db.dbo;

public class ListItem {
    private int id;
    private String description;
    private int fk_lists;

    public ListItem(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getAssociatedListID() { return fk_lists; }
}
