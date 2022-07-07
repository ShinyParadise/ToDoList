package com.example.todolist.ui.listscreen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.todolist.R;
import com.example.todolist.db.models.ListModel;
import com.example.todolist.ui.listscreen.recycler.ListsRecyclerViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListsFragment extends Fragment implements AddListDialog.AddListDialogListener {
    private RecyclerView listsRecyclerView;
    private ListsRecyclerViewAdapter adapter;

    private FloatingActionButton btnAddList;

    private ListViewModel listViewModel;

    public static ListsFragment newInstance() {
        return new ListsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lists, container, false);

        listViewModel = new ListViewModel(getContext());

        listsRecyclerView = rootView.findViewById(R.id.lists_recycler_view);
        btnAddList = rootView.findViewById(R.id.addListButton);

        listViewModel.initiateLists();
        listViewModel.fetchLists();

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

        btnAddList.setOnClickListener(v -> {
            AddListDialog addListDialog = new AddListDialog();
            addListDialog.listener = this;
            addListDialog.show(getParentFragmentManager(), "AddListDialog");
        });

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        listViewModel.fetchLists();
        adapter.setToDoLists(listViewModel.getLists());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
    }
}