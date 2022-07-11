package com.example.todolist.ui.listscreen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.todolist.R;
import com.example.todolist.ui.listscreen.recycler.ListsRecyclerViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

        //listViewModel.initiateLists();
        listViewModel.fetchLists();

        initiateRecyclerView();

        btnAddList.setOnClickListener(this::onAddClick);


        return rootView;
    }

    private void initiateRecyclerView() {
        adapter = new ListsRecyclerViewAdapter(listViewModel.getLists());
        adapter.setOnItemClickListener(new ListsRecyclerViewAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Toast.makeText(getContext(), "clicked " + position + " item", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onItemLongClick(int position, View v) {
                Toast.makeText(getContext(), "long clicked " + position + " item", Toast.LENGTH_LONG).show();
            }
        });

        listsRecyclerView.setAdapter(adapter);
        listsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initiateViews(View rootView) {
        listViewModel = new ListViewModel(getContext());
        listsRecyclerView = rootView.findViewById(R.id.lists_recycler_view);
        btnAddList = rootView.findViewById(R.id.add_list_button);
    }

    private void onAddClick(View v) {
        AddListDialog addListDialog = new AddListDialog();
        setDialogListener(addListDialog);
        addListDialog.show(getParentFragmentManager(), AddListDialog.TAG);
    }

    private void setDialogListener(AddListDialog addListDialog) {
        addListDialog.listener = new AddListDialog.AddListDialogListener() {
            @Override
            public void onDialogPositiveClick(String listName, String listDescription) {
                listViewModel.insertNewList(listName, listDescription);
                listViewModel.fetchLists();
                adapter.setToDoLists(listViewModel.getLists());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onDialogNegativeClick() {
            }
        };
    }
}