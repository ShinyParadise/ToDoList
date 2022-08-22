package com.example.todolist;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import static org.junit.Assert.*;

import com.example.todolist.dto.ListItem;
import com.example.todolist.dto.ToDoList;
import com.example.todolist.repositories.listItemsRepository.IListItemRepository;
import com.example.todolist.ui.detailScreen.DetailedListViewModel;
import static org.mockito.Mockito.*;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kotlin.jvm.JvmField;


public class DetailedListViewModelUnitTest {
    private final int testId = 1;
    private final int checked = 1;
    private final int unchecked = 0;
    private final int threads = 4;

    @Rule
    @JvmField
    public TestRule rule = new InstantTaskExecutorRule();

    private IListItemRepository mockedListItemRepository;
    private DetailedListViewModel sut;
    private final ExecutorService executorService = Executors.newFixedThreadPool(threads);

    @Before
    public void setup() {
        mockedListItemRepository = mock(IListItemRepository.class);
        sut = new DetailedListViewModel(
                mockedListItemRepository,
                new ToDoList(testId, "", ""),
                executorService
        );
    }

    @Test
    public void test_init_data() {
        assertTrue(sut.getListItems().getValue().isEmpty());
    }

    @Test
    public void test_repository_called_on_list_items_fetch() {
        sut.fetchListItems();

        verify(mockedListItemRepository).getListItems(testId);
    }

    @Test
    public void test_item_selection_inverts_item_selected_state() {
        when(mockedListItemRepository.getListItems(testId)).thenReturn(new ArrayList<ListItem>() {
            {
                add(new ListItem(1, "asdas", checked, testId, ZonedDateTime.now()));
                add(new ListItem(2, "asdassdas", unchecked, testId, ZonedDateTime.now()));
            }
        });
        sut.fetchListItems();

        sut.changeListItemState(0);

        ArrayList<ListItem> listItems = sut.getListItems().getValue();
        assertTrue(listItems.get(0).getState());
    }

    @Test
    public void test_item_sort_on_fetch_items() {
        ListItem testItem1 = new ListItem(1, "", checked, testId, ZonedDateTime.now());
        ListItem testItem2 = new ListItem(2, "", testId);
        ListItem testItem3 = new ListItem(3,
                "",
                checked,
                testId,
                ZonedDateTime.now().plus(1, ChronoUnit.SECONDS)
        );

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

        assertEquals(correctOrder, sut.getListItems().getValue());
    }
}