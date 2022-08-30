package com.example.todolist.ui.listScreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;
import com.example.todolist.dao.list.ListDAO;
import com.example.todolist.databinding.FragmentListsBinding;
import com.example.todolist.dto.ToDoList;
import com.example.todolist.repositories.listRepository.ListRepository;
import com.example.todolist.ui.app.ToDoListApp;
import com.example.todolist.ui.detailScreen.DetailedListFragment;
import com.example.todolist.ui.listScreen.listsRecycler.ListsRecyclerViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListsFragment extends Fragment {
    private RecyclerView listsRecyclerView;
    private ListsRecyclerViewAdapter adapter;

    private FloatingActionButton btnAddList;

    private ListViewModel listViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentListsBinding binding = FragmentListsBinding.inflate(inflater);

        View rootView = binding.getRoot();

        initiateViews(binding);

        initiateRecyclerView();
        initiateViewModel();

        listViewModel.fetchLists();

        btnAddList.setOnClickListener(this::onAddClick);

        return rootView;
    }

    private void initiateViewModel() {
        listViewModel = new ListViewModel(new ListRepository(
                new ListDAO(getContext())),
                ToDoListApp.getAppExecutor()
        );

        Observer<ArrayList<ToDoList>> observer = toDoLists -> {
            adapter.updateToDoLists(toDoLists);
        };

        listViewModel.toDoListsLiveData.observe(getViewLifecycleOwner(), observer);
    }

    private void initiateRecyclerView() {
        adapter = new ListsRecyclerViewAdapter();
        adapter.setOnItemClickListener((list) -> {
            DetailedListFragment detailedListFragment = new DetailedListFragment(list);

            getParentFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_view, detailedListFragment)
                .setReorderingAllowed(true)
                .addToBackStack("fragment_lists")
                .commit();
        });

        listsRecyclerView.setAdapter(adapter);
        listsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initiateViews(@NonNull FragmentListsBinding binding) {
        listsRecyclerView = binding.fragmentListsRecyclerView;
        btnAddList = binding.fragmentListsAddListButton;
    }

    private void onAddClick(View v) {
        AddListDialog addListDialog = new AddListDialog();
        setDialogListener(addListDialog);
        addListDialog.show(getParentFragmentManager(), AddListDialog.TAG);
    }

    private void setDialogListener(@NonNull AddListDialog addListDialog) {
        addListDialog.setOkButtonListener((listName, listDescription) -> {
            listViewModel.insertNewList(listName, listDescription);
        });
    }
}