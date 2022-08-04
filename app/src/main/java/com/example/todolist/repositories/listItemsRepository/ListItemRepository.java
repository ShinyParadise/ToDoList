package com.example.todolist.repositories.listItemsRepository;

import androidx.annotation.NonNull;

import com.example.todolist.dao.listItem.IListItemDAO;
import com.example.todolist.db.models.ListItemModel;
import com.example.todolist.dto.ListItem;

import java.util.ArrayList;

public class ListItemRepository implements IListItemRepository {
    private final IListItemDAO listItemDAO;

    public ListItemRepository(IListItemDAO listItemDAO) {
        this.listItemDAO = listItemDAO;
    }

    public ArrayList<ListItem> getListItems(int listID) {
        ArrayList<ListItemModel> listItemModels = listItemDAO.getAllListItems(listID);

        return convertListItemModelsToListItems(listItemModels);
    }

    public ListItem insertListItem(int listID, String listItemDescription) {
        ListItemModel listItemModel = new ListItemModel(listID, listItemDescription);
        listItemModel = listItemDAO.create(listItemModel);

        return convertListItemModelToListItem(listItemModel);
    }

    public void changeListItemState(@NonNull ListItem listItem) {
        ListItemModel itemModel = convertListItemToModel(listItem);

        listItemDAO.update(itemModel);
    }

    @NonNull
    private ListItemModel convertListItemToModel(@NonNull ListItem listItem) {
        return new ListItemModel(
                listItem.getID(),
                listItem.getDescription(),
                listItem.getState(),
                listItem.getListID()
        );
    }

    @NonNull
    private ListItem convertListItemModelToListItem(@NonNull ListItemModel itemModel) {
        return new ListItem(
                itemModel.id,
                itemModel.description,
                itemModel.is_checked,
                itemModel.fk_list
        );
    }

    @NonNull
    private ArrayList<ListItem> convertListItemModelsToListItems(@NonNull ArrayList<ListItemModel> listItemModels) {
        ArrayList<ListItem> listItems = new ArrayList<>();

        for (ListItemModel itemModel : listItemModels) {
            ListItem listItem = convertListItemModelToListItem(itemModel);
            listItems.add(listItem);
        }
        return listItems;
    }
}
