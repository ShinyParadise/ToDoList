package com.example.todolist.dto;

import java.util.ArrayList;
import java.util.Objects;

public class ToDoList {
    private int id;
    private String header;
    private String description;
    private ArrayList<ListItem> listItems;

    public ToDoList(int id, String header, String description) {
        this.id = id;
        this.header = header;
        this.description = description;
        listItems = new ArrayList<>();
    }

    public ToDoList(int id, String header, String description, ArrayList<ListItem> listItems) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ToDoList toDoList = (ToDoList) o;

        if (id != toDoList.id) return false;
        if (!header.equals(toDoList.header)) return false;
        return Objects.equals(description, toDoList.description);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + header.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
