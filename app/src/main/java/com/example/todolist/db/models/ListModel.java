package com.example.todolist.db.models;

public class ListModel {
    public ListModel(int id, String header, String description) {
        this.id = id;
        this.header = header;
        this.description = description;
    }

    public ListModel(String listName, String listDescription) {
        header = listName;
        description = listDescription;
    }

    public ListModel() {

    }

    public int id;
    public String header;
    public String description;
}
