package com.example.todolist.ui.detailscreen;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.todolist.R;
import com.example.todolist.repositories.listrepository.IListRepository;
import com.example.todolist.ui.detailscreen.detailrecycler.ListDetailRecyclerViewAdapter;
import com.example.todolist.ui.startup.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailedListFragment extends Fragment {
    // todo не игнорируй подсказки студии, они обычно только улучшают твой код
    private DetailedListViewModel detailsViewModel;

    private RecyclerView detailsRecyclerView;
    private ListDetailRecyclerViewAdapter adapter;

    private FloatingActionButton btnAddDetail;

    public DetailedListFragment(IListRepository listRepository, int listID) {
        detailsViewModel = new DetailedListViewModel(listRepository, listID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detailed_list, container, false);

        initiateViews(rootView);

        // todo Как исправить это место?
        ((MainActivity)getActivity()).changeActionBarTitle(detailsViewModel.getHeader());

        detailsViewModel.fetchListItems();
        initiateRecyclerView();

        btnAddDetail.setOnClickListener(this::onAddDetailClick);

        return rootView;
    }

    private void initiateRecyclerView() {
        adapter = new ListDetailRecyclerViewAdapter(detailsViewModel.getListItems());

        adapter.setOnCheckChangedListener((position) ->
                detailsViewModel.changeListItemState(position)
        );

        detailsRecyclerView.setAdapter(adapter);
        detailsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initiateViews(View rootView) {
        detailsRecyclerView = rootView.findViewById(R.id.fragment_details_recycler_view);
        btnAddDetail = rootView.findViewById(R.id.fragment_details_btn_add_detail);
    }

    private void onAddDetailClick(View v) {
        AddListItemDialog addListItemDialog = new AddListItemDialog();
        setDialogListener(addListItemDialog);
        addListItemDialog.show(getParentFragmentManager(), AddListItemDialog.TAG);
    }

    private void setDialogListener(AddListItemDialog addListItemDialog) {
        addListItemDialog.listener = new AddListItemDialog.AddListItemDialogListener() {
            @Override
            public void onDialogPositiveClick(String listItemDescription) {
                detailsViewModel.insertListItem(listItemDescription);
                detailsViewModel.fetchListItems();
                adapter.setListItems(detailsViewModel.getListItems());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onDialogNegativeClick() {

            }
        };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // todo Как исправить это место?
        ((MainActivity)getActivity()).changeActionBarTitle("Lists");
    }
}
