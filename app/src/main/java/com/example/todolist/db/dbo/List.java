package com.example.todolist.db.dbo;

import java.util.ArrayList;

public class List {
    private int id;
    private String header;
    private String description;
    private ArrayList<ListItem> listItems;

    public List(int id, String header, String description) {
        this.id = id;
        this.header = header;
        this.description = description;
        listItems = new ArrayList<>();
    }

    public List(int id, String header, String description, ArrayList<ListItem> listItems) {
        this.id = id;
        this.header = header;
        this.description = description;
        this.listItems = listItems;
    }

    public int getId() {
        return id;
    }

    public String getHeader() {
        return header;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<ListItem> getListItems() {
        return listItems;
    }

    public void setListItems(ArrayList<ListItem> listItems) {
        this.listItems = listItems;
    }
}
