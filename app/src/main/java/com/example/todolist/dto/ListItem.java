package com.example.todolist.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class ListItem {
    private final int id;
    private String description;
    private boolean isChecked = false;
    private final int listID;
    private LocalDateTime updatedAt;

    public ListItem(int id, String description, int listID) {
        this.id = id;
        this.description = description;
        this.listID = listID;
    }

    public ListItem(int id,
                    String description,
                    int is_checked,
                    int fk_list,
                    LocalDateTime updatedAt) {
        this.id = id;
        this.description = description;
        this.isChecked = is_checked != 0;
        this.listID = fk_list;
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
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
        if (updatedAt.isEqual(listItem.updatedAt)) return false;
        return description.equals(listItem.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, isChecked, listID, updatedAt);
    }
}
