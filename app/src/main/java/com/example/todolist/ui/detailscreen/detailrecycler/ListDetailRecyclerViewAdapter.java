package com.example.todolist.ui.detailscreen.detailrecycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;
import com.example.todolist.db.models.ListItemModel;
import com.example.todolist.dto.ListItem;

import java.util.ArrayList;

public class ListDetailRecyclerViewAdapter extends RecyclerView.Adapter<ListDetailRecyclerViewAdapter.ViewHolder> {
    private ArrayList<ListItem> listItems;
    private static ClickListener clickListener;

    public ListDetailRecyclerViewAdapter(ArrayList<ListItem> listItems) {
        this.listItems = listItems;
    }

    public void setListItems(ArrayList<ListItem> listItems) {
        this.listItems = listItems;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }

    public void setOnItemClickListener(ListDetailRecyclerViewAdapter.ClickListener clickListener) {
        ListDetailRecyclerViewAdapter.clickListener = clickListener;
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
            holder.getListNumberView().setText(String.valueOf(position + 1));
        }
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
        private final TextView listItemView;
        private final TextView listNumberView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            listItemView = itemView.findViewById(R.id.detail_description);
            listNumberView = itemView.findViewById(R.id.detail_number);
        }

        public TextView getListItemView() {
            return listItemView;
        }

        public TextView getListNumberView() {
            return listNumberView;
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onItemLongClick(getAdapterPosition(), v);
            return false;
        }
    }
}
