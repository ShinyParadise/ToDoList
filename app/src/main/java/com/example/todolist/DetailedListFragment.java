package com.example.todolist;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DetailedListFragment extends Fragment {
    private long detailedListFragmentId;

    private DetailedListViewModel mViewModel;

    public static DetailedListFragment newInstance() {
        return new DetailedListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detailed_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetailedListViewModel.class);
        // TODO: Use the ViewModel
    }

    public void setDetailedListFragmentId(long id) {
        detailedListFragmentId = id;
    }
}