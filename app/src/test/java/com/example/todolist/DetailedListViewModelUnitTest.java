package com.example.todolist;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.example.todolist.dto.ListItem;
import com.example.todolist.dto.ToDoList;
import com.example.todolist.repositories.listItemsRepository.IListItemRepository;
import com.example.todolist.repositories.listRepository.IListRepository;
import com.example.todolist.ui.detailScreen.DetailedListViewModel;
import static org.mockito.Mockito.*;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class DetailedListViewModelUnitTest {
    private final int testId = 1;

    private IListItemRepository mockedListItemRepository;
    private IListRepository mockedListRepository;
    private DetailedListViewModel sut;

    @Before
    public void setup() {
        mockedListItemRepository = mock(IListItemRepository.class);
        mockedListRepository = mock(IListRepository.class);
        sut = new DetailedListViewModel(
                mockedListItemRepository,
                mockedListRepository,
                new ToDoList(testId, "", ""));
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

    @Test
    public void test_item_sort_on_fetch_items() {
        int checked = 1;

        ListItem testItem1 = new ListItem(1, "", checked, testId, ZonedDateTime.now());
        ListItem testItem2 = new ListItem(2, "", testId);
        ListItem testItem3 = new ListItem(3,
                "",
                checked,
                testId,
                ZonedDateTime.now().plus(1, ChronoUnit.SECONDS));
        ArrayList<ListItem> correctOrder = new ArrayList<>();
        correctOrder.add(testItem2);
        correctOrder.add(testItem3);
        correctOrder.add(testItem1);

        when(mockedListItemRepository.getListItems(testId)).thenReturn(new ArrayList<ListItem>() {
            {
                add(testItem1);
                add(testItem2);
                add(testItem3);
            }
        });


        sut.fetchListItems();

        assertEquals(correctOrder, sut.getListItems());
    }
}