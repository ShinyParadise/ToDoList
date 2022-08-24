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

import com.example.todolist.R;
import com.example.todolist.dao.listItem.ListItemDAO;
import com.example.todolist.dto.ListItem;
import com.example.todolist.dto.ToDoList;
import com.example.todolist.repositories.listItemsRepository.ListItemRepository;
import com.example.todolist.ui.app.ToDoListApp;
import com.example.todolist.ui.detailScreen.detailRecycler.ListDetailRecyclerViewAdapter;
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
        View rootView = inflater.inflate(R.layout.fragment_detailed_list, container, false);

        initiateViews(rootView);

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

        adapter.setOnCheckChangedListener(this::onItemStateChange);

        detailsRecyclerView.setAdapter(adapter);
        detailsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void onItemStateChange(int position) {
        detailsViewModel.changeListItemState(position);
    }

    private void initiateViews(@NonNull View rootView) {
        detailsRecyclerView = rootView.findViewById(R.id.fragment_details_recycler_view);
        btnAddDetail = rootView.findViewById(R.id.fragment_details_btn_add_detail);
    }

    private void onAddDetailClick(View v) {
        AddListItemDialog addListItemDialog = new AddListItemDialog();
        setDialogListener(addListItemDialog);
        addListItemDialog.show(getParentFragmentManager(), AddListItemDialog.TAG);
    }

    private void setDialogListener(@NonNull AddListItemDialog addListItemDialog) {
        addListItemDialog.setOkButtonListener(listItemDescription -> {
            detailsViewModel.insertListItem(listItemDescription);
            detailsViewModel.fetchListItems();
        });
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