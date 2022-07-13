package com.example.todolist.ui.detailscreen.detailrecycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;
import com.example.todolist.db.models.ListItemModel;

import java.util.ArrayList;

public class ListDetailRecyclerViewAdapter extends RecyclerView.Adapter<ListDetailRecyclerViewAdapter.ViewHolder> {
    private ArrayList<ListItemModel> entries;
    private static ListDetailRecyclerViewAdapter.ClickListener clickListener;

    public ListDetailRecyclerViewAdapter(ArrayList<ListItemModel> entries) {
        this.entries = entries;
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
        ListItemModel entry = entries.get(position);

        if (!entry.description.isEmpty()) {
            holder.getListItemView().setText(entry.description);
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
        private final TextView listItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            listItem = itemView.findViewById(R.id.detail_description);
        }

        public TextView getListItemView() {
            return listItem;
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
