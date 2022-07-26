package com.example.todolist;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.example.todolist.dto.ListItem;
import com.example.todolist.repositories.listitemsrepository.IListItemRepository;
import com.example.todolist.repositories.listrepository.IListRepository;
import com.example.todolist.ui.detailscreen.DetailedListViewModel;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

public class DetailedListViewModelUnitTest {
    private final int testId = 1;

    private IListItemRepository mockedListItemRepository;
    private DetailedListViewModel sut;

    @Before
    public void setup() {
        mockedListItemRepository = mock(IListItemRepository.class);
        sut = new DetailedListViewModel(mockedListItemRepository, testId, "");
    }

    @Test
    public void test_init_data() {
        assertTrue(sut.getListItems().isEmpty());
    }

    @Test
    public void test_repository_called_on_list_items_fetch() {
        sut.fetchListItems();

        verify(mockedListItemRepository).getListItems(testId);
    }

    @Test
    public void test_repository_fetched_correct_header() {
        String testHeader = "header";
        IListRepository mockedListRepository = mock(IListRepository.class);

        when(mockedListRepository.getListHeader(testId)).thenReturn(testHeader);

        sut.fetchHeader();

        assertEquals(testHeader, sut.getHeader());
    }

    @Test
    public void test_item_selection_inverts_item_selected_state() {
        when(mockedListItemRepository.getListItems(testId)).thenReturn(new ArrayList<ListItem>() {
            {
                add(new ListItem(1, "asdas", 2));
                add(new ListItem(2, "asdassdas", 3));
            }
        });
        sut.fetchListItems();

        sut.changeListItemState(0);

        assertTrue(sut.getListItems().get(0).getState());
    }
}