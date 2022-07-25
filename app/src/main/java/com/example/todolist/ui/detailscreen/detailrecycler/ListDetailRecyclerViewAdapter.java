package com.example.todolist.ui.detailscreen.detailrecycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;
import com.example.todolist.dto.ListItem;

import java.util.ArrayList;

public class ListDetailRecyclerViewAdapter extends RecyclerView.Adapter<ListDetailRecyclerViewAdapter.ViewHolder> {
    private ArrayList<ListItem> listItems;
    private static CheckListener checkListener;

    public ListDetailRecyclerViewAdapter(ArrayList<ListItem> listItems) {
        this.listItems = listItems;
    }

    public void setListItems(ArrayList<ListItem> listItems) {
        this.listItems = listItems;
    }

    public interface CheckListener {
        void onCheckChanged(int position);
    }

    public void setOnCheckChangedListener(CheckListener checkListener) {
        ListDetailRecyclerViewAdapter.checkListener = checkListener;
    }

    @NonNull
    @Override
    public ListDetailRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.details_recycler_view_item, parent, false);

        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItem listItem = listItems.get(position);

        if (!listItem.getDescription().isEmpty()) {
            holder.getListItemView().setText(listItem.getDescription());
            holder.getListItemView().setChecked(listItem.getState());
        }
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox listItemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            listItemView = itemView.findViewById(R.id.list_item);
            listItemView.setOnClickListener((buttonView) ->
                    checkListener.onCheckChanged(getAbsoluteAdapterPosition())
            );
        }

        public CheckBox getListItemView() {
            return listItemView;
        }
    }
}
