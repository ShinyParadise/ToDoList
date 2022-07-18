package com.example.todolist.db.models;

public class ListItemModel {
    public int id;
    public String description;
    public int fk_lists;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListItemModel)) return false;

        ListItemModel that = (ListItemModel) o;

        if (id != that.id) return false;
        if (fk_lists != that.fk_lists) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + fk_lists;
        return result;
    }
}
