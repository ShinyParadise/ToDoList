package com.example.todolist;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.example.todolist.dto.ListItem;
import com.example.todolist.dto.ToDoList;
import com.example.todolist.repositories.listrepository.IListRepository;
import com.example.todolist.ui.detailscreen.DetailedListViewModel;

import java.util.ArrayList;
import java.util.Collections;

public class DetailedListViewModelUnitTest {
    private final int testId = 1;
    private final String testHeader = "header";

    private ListRepositoryMock listRepository;
    private DetailedListViewModel sut;

    @Before
    public void setup() {
        // TODO: переписать с mockk
        listRepository = new ListRepositoryMock();
        sut = new DetailedListViewModel(listRepository, testId);
    }

    @Test
    public void test_init_data() {
        assertTrue(sut.getListItems().isEmpty());
    }

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

    @Test
    public void test_item_selection_inverts_item_selected_state() {
        listRepository.listItems = new ArrayList<>(
                Collections.singletonList(
                        new ListItem(1, "asd", 1)
                )
        );
        sut.fetchListItems();

        sut.changeListItemState(0, true);

        assertTrue(sut.getListItems().get(0).getState());
    }

    private class ListRepositoryMock implements IListRepository {
        public int fetchListID;
        public String header;
        public ArrayList<ListItem> listItems = new ArrayList<>();

        @Override
        public void insertToDoListWithoutItems(String listName, String listDescription) {

        }

        @Override
        public void insertListItem(int listID, String listItem) {

        }

        @Override
        public void changeListItemState(int id, boolean newState) {

        }

        @Override
        public ArrayList<ToDoList> getAllLists() {
            return null;
        }

        @Override
        public ArrayList<ListItem> getListItems(int listID) {
            fetchListID = listID;
            return listItems;
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