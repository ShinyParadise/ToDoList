package com.example.todolist.ui.detailScreen;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.dao.listItem.ListItemDAO;
import com.example.todolist.databinding.FragmentDetailedListBinding;
import com.example.todolist.dto.ListItem;
import com.example.todolist.dto.ToDoList;
import com.example.todolist.repositories.listItemsRepository.ListItemRepository;
import com.example.todolist.ui.app.ToDoListApp;
import com.example.todolist.ui.detailScreen.detailRecycler.ListDetailRecyclerViewAdapter;
import com.example.todolist.ui.handlers.ItemClickHandler;
import com.example.todolist.ui.startup.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DetailedListFragment extends Fragment {
    private static final String TAG = "DetailedListFragment";

    private final DetailedListViewModel detailsViewModel;

    private RecyclerView detailsRecyclerView;
    private ListDetailRecyclerViewAdapter adapter;

    private FloatingActionButton btnAddDetail;

    public DetailedListFragment(@NonNull ToDoList toDoList) {
        detailsViewModel = new DetailedListViewModel(
                new ListItemRepository(new ListItemDAO(getContext())),
                toDoList,
                ToDoListApp.getAppExecutor()
        );
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentDetailedListBinding binding = FragmentDetailedListBinding.inflate(inflater);
        View rootView = binding.getRoot();

        initiateViews(binding);

        try {
            ((MainActivity) requireActivity()).changeActionBarTitle(detailsViewModel.getHeader());
        } catch (IllegalStateException exception) {
            Log.e(TAG, "On change action bar title: ", exception);
        }

        initiateRecyclerView();
        initiateObserver();

        detailsViewModel.fetchListItems();

        btnAddDetail.setOnClickListener(this::onAddDetailClick);

        return rootView;
    }

    private void initiateObserver() {
        Observer<ArrayList<ListItem>> observer = listItems -> {
            adapter.updateListItems(listItems);
        };

        detailsViewModel.listItemsLiveData.observe(getViewLifecycleOwner(), observer);
    }

    private void initiateRecyclerView() {
        adapter = new ListDetailRecyclerViewAdapter();

        adapter.setOnClickListener(detailsViewModel::changeListItemState);

        detailsRecyclerView.setAdapter(adapter);
        detailsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initiateViews(@NonNull FragmentDetailedListBinding binding) {
        detailsRecyclerView = binding.fragmentDetailsRecyclerView;
        btnAddDetail = binding.fragmentDetailsBtnAddDetail;
    }

    private void onAddDetailClick(View v) {
        AddListItemDialog addListItemDialog = new AddListItemDialog();
        addListItemDialog.setOkButtonListener(detailsViewModel::insertListItem);
        addListItemDialog.show(getParentFragmentManager(), AddListItemDialog.TAG);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            ((MainActivity) requireActivity()).changeActionBarTitle("Lists");
        } catch (NullPointerException exception) {
            Log.e(TAG, "on change action bar title: ", exception);
        }
    }
}