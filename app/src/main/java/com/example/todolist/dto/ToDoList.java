package com.example.todolist.dto;

import java.util.ArrayList;
import java.util.Objects;

public class ToDoList {
    private final int id;
    private String header;
    private String description;
    private ArrayList<ListItem> listItems;

    public ToDoList(int id, String header, String description) {
        this.id = id;
        this.header = header;
        this.description = description;
        listItems = new ArrayList<>();
    }

    public int getID() {
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

    public void setHeader(String header) {
        this.header = header;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ToDoList toDoList = (ToDoList) o;

        if (id != toDoList.id) return false;
        if (!header.equals(toDoList.header)) return false;
        if (!Objects.equals(listItems, toDoList.listItems)) return false;
        return Objects.equals(description, toDoList.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, header, description, listItems);
    }
}
