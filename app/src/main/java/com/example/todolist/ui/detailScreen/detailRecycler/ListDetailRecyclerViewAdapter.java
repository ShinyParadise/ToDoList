package com.example.todolist.ui.detailScreen.detailRecycler;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.databinding.DetailsRecyclerViewItemBinding;
import com.example.todolist.dto.ListItem;
import com.example.todolist.ui.handlers.ItemClickHandler;

import java.util.ArrayList;

public class ListDetailRecyclerViewAdapter extends RecyclerView.Adapter<ListDetailRecyclerViewAdapter.ViewHolder> {
    private ArrayList<ListItem> listItems;
    private ItemClickHandler clickListener;

    public ListDetailRecyclerViewAdapter() {
        listItems = new ArrayList<>();
    }

    public void updateListItems(ArrayList<ListItem> listItems) {
        this.listItems = listItems;
        notifyDataSetChanged();
    }

    public void setOnClickListener(ItemClickHandler clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ListDetailRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        DetailsRecyclerViewItemBinding binding = DetailsRecyclerViewItemBinding.inflate(inflater);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListItem listItem = listItems.get(position);

        if (!listItem.getDescription().isEmpty()) {
            holder.binding.setItem(listItem);
            holder.binding.setItemPosition(position);

            holder.binding.detailsItemView.setOnClickListener(v -> {
                clickListener.onItemClick(position);
            });

            holder.binding.executePendingBindings();
        }
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public DetailsRecyclerViewItemBinding binding;

        public ViewHolder(@NonNull DetailsRecyclerViewItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
