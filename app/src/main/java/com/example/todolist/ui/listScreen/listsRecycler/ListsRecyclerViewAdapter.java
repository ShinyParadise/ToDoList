package com.example.todolist.ui.listScreen.listsRecycler;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.databinding.ListsRecyclerViewItemBinding;
import com.example.todolist.dto.ToDoList;
import com.example.todolist.ui.handlers.ListClickHandler;

import java.util.ArrayList;

public class ListsRecyclerViewAdapter extends RecyclerView.Adapter<ListsRecyclerViewAdapter.ViewHolder> {
    private ArrayList<ToDoList> toDoLists;
    private ListClickHandler clickListener;

    public ListsRecyclerViewAdapter() {
        toDoLists = new ArrayList<>();
    }

    public void setOnItemClickListener(ListClickHandler clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ListsRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ListsRecyclerViewItemBinding binding = ListsRecyclerViewItemBinding.inflate(inflater);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ToDoList list = toDoLists.get(position);

        if (!(list.getHeader().isEmpty() || list.getDescription().isEmpty())) {
            holder.binding.setList(list);
            holder.binding.listsItemView.setOnClickListener(v -> {
                clickListener.onItemClick(list);
            });

            holder.binding.executePendingBindings();
        }
    }

    public void updateToDoLists(ArrayList<ToDoList> toDoLists) {
        this.toDoLists = toDoLists;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return toDoLists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ListsRecyclerViewItemBinding binding;

        public ViewHolder(@NonNull ListsRecyclerViewItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
