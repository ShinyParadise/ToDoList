package com.example.todolist.ui.listScreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;
import com.example.todolist.dao.list.ListDAO;
import com.example.todolist.dto.ToDoList;
import com.example.todolist.repositories.listRepository.ListRepository;
import com.example.todolist.ui.app.ToDoListApp;
import com.example.todolist.ui.detailScreen.DetailedListFragment;
import com.example.todolist.ui.listScreen.listsRecycler.ListsRecyclerViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

public class ListsFragment extends Fragment {
    private RecyclerView listsRecyclerView;
    private ListsRecyclerViewAdapter adapter;

    private FloatingActionButton btnAddList;

    private ListViewModel listViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lists, container, false);

        initiateViews(rootView);

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

        listViewModel.getLists().observe(getViewLifecycleOwner(), observer);
    }

    private void initiateRecyclerView() {
        adapter = new ListsRecyclerViewAdapter();
        adapter.setOnItemClickListener(new ListsRecyclerViewAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                DetailedListFragment detailedListFragment = new DetailedListFragment(
                        Objects.requireNonNull(listViewModel.getLists().getValue()).get(position)
                );

                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_view, detailedListFragment)
                        .setReorderingAllowed(true)
                        .addToBackStack("fragment_lists")
                        .commit();
            }

            @Override
            public void onItemLongClick(int position, View v) {
                Toast.makeText(getContext(), "long clicked " + position + " item", Toast.LENGTH_SHORT).show();
            }
        });

        listsRecyclerView.setAdapter(adapter);
        listsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initiateViews(@NonNull View rootView) {
        listsRecyclerView = rootView.findViewById(R.id.fragment_lists_recycler_view);
        btnAddList = rootView.findViewById(R.id.fragment_lists_add_list_button);
    }

    private void onAddClick(View v) {
        AddListDialog addListDialog = new AddListDialog();
        setDialogListener(addListDialog);
        addListDialog.show(getParentFragmentManager(), AddListDialog.TAG);
    }

    private void setDialogListener(@NonNull AddListDialog addListDialog) {
        addListDialog.setListener((listName, listDescription) -> {
            listViewModel.insertNewList(listName, listDescription);
            listViewModel.fetchLists();
        }
        );
    }
}