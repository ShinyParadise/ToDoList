package com.example.todolist.ui.detailscreen;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.todolist.R;

public class DetailedListFragment extends Fragment {
    private DetailedListViewModel detailsViewModel;

    public DetailedListFragment(int listID) {
        detailsViewModel = new DetailedListViewModel(getContext(), listID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detailed_list, container, false);
    }
}