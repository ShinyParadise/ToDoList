package com.example.todolist;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.todolist.repositories.listrepository.IListRepository;
import com.example.todolist.db.models.ListItemModel;
import com.example.todolist.db.models.ListModel;
import com.example.todolist.ui.detailscreen.DetailedListViewModel;

import java.util.ArrayList;

public class DetailedListViewModelUnitTest {
    private final int testId = 1;
    private final String testHeader = "header";
    private ListRepositoryMock listRepository = new ListRepositoryMock();
    private DetailedListViewModel sut = new DetailedListViewModel(listRepository, testId);


    @Test
    public void test_repository_called_on_list_items_fetch() {
        sut.fetchListItems();

        assertEquals(testId, listRepository.fetchListID);
    }

    @Test
    public void test_repository_fetched_correct_header() {
        sut.fetchHeader();

        assertEquals(testHeader, sut.getHeader());
    }


    private class ListRepositoryMock implements IListRepository {
        public int fetchListID;
        public String header;

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
            if (listID == testId)
                header = testHeader;
            else
                header = null;

            return header;
        }

        @Override
        public int getListID(String listHeader) {
            return 0;
        }
    }
}