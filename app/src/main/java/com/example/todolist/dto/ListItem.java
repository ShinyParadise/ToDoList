package com.example.todolist.dto;

public class ListItem {
    private int id;
    private String description;
    private boolean isChecked = false;
    private int fk_list;

    public ListItem(int id, String description, int fk_list) {
        this.id = id;
        this.description = description;
        this.fk_list = fk_list;
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

    public int getAssociatedListID() { return fk_list; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListItem listItem = (ListItem) o;

        if (id != listItem.id) return false;
        if (fk_list != listItem.fk_list) return false;
        return description.equals(listItem.description);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + description.hashCode();
        result = 31 * result + fk_list;
        return result;
    }
}
