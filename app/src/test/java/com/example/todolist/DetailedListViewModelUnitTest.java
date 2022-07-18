package com.example.todolist;

import org.junit.Test;

import static org.junit.Assert.*;

import android.content.Context;

import com.example.todolist.db.listrepository.IListRepository;
import com.example.todolist.db.listrepository.ListRepository;
import com.example.todolist.db.models.ListItemModel;
import com.example.todolist.db.models.ListModel;
import com.example.todolist.ui.detailscreen.DetailedListViewModel;

import java.util.ArrayList;

public class DetailedListViewModelUnitTest {
    private int testId = 1;
    private ListRepositoryMock listRepository = new ListRepositoryMock();
    private DetailedListViewModel sut = new DetailedListViewModel(listRepository, testId);

    @Test
    public void test_repository_called_on_list_items_fetch() {
        sut.fetchListItems();

        sut.getListItems();

        assertEquals(testId, listRepository.fetchListID);
    }

    // TODO: test for fetching header

    private class ListRepositoryMock implements IListRepository {
        public int fetchListID;

        @Override
        public void insertToDoListWithoutItems(String listName, String listDescription) {

        }

        @Override
        public void insertListItems(String listName, String[] listItems) {

        }

        @Override
        public ArrayList<ListModel> getAllLists() {
            return null;
        }

        @Override
        public ArrayList<ListItemModel> getListItems(int listID) {
            fetchListID = listID;
            return new ArrayList<>();
        }

        @Override
        public String getListHeader(int listID) {
            return null;
        }

        @Override
        public int getListID(String listHeader) {
            return 0;
        }
    }
}