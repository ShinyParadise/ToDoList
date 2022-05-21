package com.example.todolist.listscreen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todolist.R;
import com.example.todolist.ToDoRecViewAdapter;
import com.example.todolist.models.ToDoList;

import java.util.ArrayList;

public class ListsFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<ToDoList> toDoLists;

    public static ListsFragment newInstance() {
        return new ListsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_lists, container, false);

        recyclerView = rootView.findViewById(R.id.recView);
        toDoLists = new ArrayList<>();

        initiateLists(toDoLists);

        // TODO: поменять на запрос с базы данных
        ToDoRecViewAdapter adapter = new ToDoRecViewAdapter(toDoLists);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }

    private void initiateLists(ArrayList<ToDoList> toDoLists) {
        toDoLists.add(new ToDoList("Today", "gotta do it faster"));
        toDoLists.add(new ToDoList("Tomorrow", "ain't gonna do this"));
        toDoLists.add(new ToDoList("Plan", "description"));
        toDoLists.add(new ToDoList("Today", "gotta do it faster"));
        toDoLists.add(new ToDoList("Tomorrow", "ain't gonna do this"));
        toDoLists.add(new ToDoList("Plan", "description"));
        toDoLists.add(new ToDoList("Today", "gotta do it faster"));
        toDoLists.add(new ToDoList("Tomorrow", "ain't gonna do this"));
        toDoLists.add(new ToDoList("Plan", "description"));
        toDoLists.add(new ToDoList("Plan", "description"));
        toDoLists.add(new ToDoList("Today", "gotta do it faster"));
        toDoLists.add(new ToDoList("Tomorrow", "ain't gonna do this"));
        toDoLists.add(new ToDoList("Plan", "description"));
        toDoLists.add(new ToDoList("Today", "gotta do it faster"));
        toDoLists.add(new ToDoList("Tomorrow", "ain't gonna do this"));
        toDoLists.add(new ToDoList("Plan", "description"));
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}