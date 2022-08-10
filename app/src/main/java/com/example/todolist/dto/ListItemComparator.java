package com.example.todolist.dto;

import androidx.annotation.NonNull;

import java.util.Comparator;

public class ListItemComparator implements Comparator<ListItem> {
    public int compare(@NonNull ListItem firstItem, ListItem secondItem) {
        if (!firstItem.getState() && secondItem.getState())
            return -1;

        if (firstItem.getState() == secondItem.getState()) {
            return -firstItem.getUpdatedAt().compareTo(secondItem.getUpdatedAt());
        }

        return 0;
    }
}
