package com.example.todolist.dto;

import java.time.ZonedDateTime;
import java.util.Objects;

public class ListItem {
    private final int id;
    private String description;
    private boolean isChecked = false;
    private final int listID;
    private ZonedDateTime updatedAt;

    public ListItem(int id, String description, int listID) {
        this.id = id;
        this.description = description;
        this.listID = listID;
    }

    public ListItem(int id,
                    String description,
                    int isChecked,
                    int listID,
                    ZonedDateTime updatedAt) {
        this.id = id;
        this.description = description;
        this.isChecked = isChecked != 0;
        this.listID = listID;
        this.updatedAt = updatedAt;
    }

    public int getID() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setState(boolean newState) {
        isChecked = newState;
    }

    public boolean getState() {
        return isChecked;
    }

    public int getListID() { return listID; }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListItem listItem = (ListItem) o;

        if (id != listItem.id) return false;
        if (listID != listItem.listID) return false;
        if (isChecked != listItem.isChecked) return false;
        if (updatedAt != listItem.updatedAt) return false;
        return description.equals(listItem.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, isChecked, listID, updatedAt);
    }
}
