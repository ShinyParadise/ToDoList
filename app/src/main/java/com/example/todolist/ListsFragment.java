package com.example.todolist;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListsFragment extends Fragment {
    private long listsFragmentId;

    private ListsViewModel mViewModel;

    public static ListsFragment newInstance() {
        return new ListsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lists, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ListsViewModel.class);
        // TODO: Use the ViewModel
    }

    public void setListsFragmentId(long id) {
        listsFragmentId = id;
    }
}