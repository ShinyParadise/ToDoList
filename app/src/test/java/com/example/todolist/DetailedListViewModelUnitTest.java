package com.example.todolist;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.example.todolist.dto.ListItem;
import com.example.todolist.dto.ToDoList;
import com.example.todolist.repositories.listItemsRepository.IListItemRepository;
import com.example.todolist.ui.detailScreen.DetailedListViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class DetailedListViewModelUnitTest {
    private final int testId = 1;
    private final int checked = 1;
    private final int unchecked = 0;

    private IListItemRepository mockedListItemRepository;
    private DetailedListViewModel sut;
    private ExecutorService pool;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Before
    public void setup() {
        int threads = 4;
        mockedListItemRepository = mock(IListItemRepository.class);
        pool = Executors.newFixedThreadPool(threads);
        sut = new DetailedListViewModel(
                mockedListItemRepository,
                new ToDoList(testId, "", ""),
                pool
        );

        Observer<ArrayList<ListItem>> mockObserver = mock(Observer.class);
        sut.listItemsLiveData.observeForever(mockObserver);
    }

    @Test
    public void test_init_data() {
        ArrayList<ListItem> listItems = sut.listItemsLiveData.getValue();

        assert listItems != null;
        assertTrue(listItems.isEmpty());
    }

    @Test
    public void test_repository_called_on_list_items_fetch() {
        sut.fetchListItems();

        verify(mockedListItemRepository).getListItems(testId);
    }

    @Test
    public void test_item_selection_inverts_item_selected_state() {
        // Test items
        ListItem testItem1 = new ListItem(1, "asdas", checked, testId, ZonedDateTime.now());
        ListItem testItem2 = new ListItem(2, "asdassdas", unchecked, testId, ZonedDateTime.now());

        when(mockedListItemRepository.getListItems(testId)).thenReturn(new ArrayList<ListItem>() {
            {
                add(testItem1);
                add(testItem2);
            }
        });

        sut.fetchListItems();
        sut.changeListItemState(1);
        pool.shutdownNow();

        ArrayList<ListItem> listItems = sut.listItemsLiveData.getValue();

        assert listItems != null;

        assertFalse(listItems.get(1).getState());
    }

    @Test
    public void test_item_sort_on_fetch_items() {
        ListItem testItem1 = new ListItem(1, "", checked, testId, ZonedDateTime.now());
        ListItem testItem2 = new ListItem(2, "", testId);
        ListItem testItem3 = new ListItem(3, "", unchecked, testId, ZonedDateTime.now().plus(1, ChronoUnit.SECONDS));

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
        ArrayList<ListItem> listItems = sut.listItemsLiveData.getValue();

        assert listItems != null;
        assertEquals(correctOrder, listItems);
    }
}