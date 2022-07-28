package com.example.todolist.repositories.listitemsrepository;

import androidx.annotation.NonNull;

import com.example.todolist.dao.listitem.IListItemDAO;
import com.example.todolist.db.models.ListItemModel;
import com.example.todolist.dto.ListItem;

import java.util.ArrayList;

public class ListItemRepository implements IListItemRepository {
    private final IListItemDAO listItemDAO;

    public ListItemRepository(IListItemDAO listItemDAO) {
        this.listItemDAO = listItemDAO;
    }

    public ArrayList<ListItem> getListItems(int listID) {
        ArrayList<ListItem> listItems = new ArrayList<>();
        
        ArrayList<ListItemModel> listItemModels = listItemDAO.getAllListItems(listID);
        for (ListItemModel itemModel : listItemModels) {
            ListItem listItem = new ListItem(
                    itemModel.id,
                    itemModel.description,
                    itemModel.is_checked,
                    itemModel.fk_list
            );
            listItems.add(listItem);
        }
        
        return listItems;
    }


    public void insertListItem(int listID, String listItemDescription) {
        ListItemModel listItemModel = new ListItemModel(listID, listItemDescription);
        listItemDAO.create(listItemModel);
    }

    public void changeListItemState(@NonNull ListItem listItem) {
        ListItemModel itemModel = new ListItemModel(
                listItem.getID(),
                listItem.getDescription(),
                listItem.getState(),
                listItem.getListID()
        );

        listItemDAO.update(itemModel);
    }
}
