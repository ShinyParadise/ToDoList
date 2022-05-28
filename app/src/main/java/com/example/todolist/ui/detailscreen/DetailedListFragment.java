package com.example.todolist.ui.detailscreen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todolist.R;

public class DetailedListFragment extends Fragment {
    private long detailedListFragmentId;

    public static DetailedListFragment newInstance() {
        return new DetailedListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detailed_list, container, false);
    }

    public void setDetailedListFragmentId(long id) {
        detailedListFragmentId = id;
    }
}