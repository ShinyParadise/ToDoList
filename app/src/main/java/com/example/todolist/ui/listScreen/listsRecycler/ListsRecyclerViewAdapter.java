package com.example.todolist.ui.listScreen.listsRecycler;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.databinding.ListsRecyclerViewItemBinding;
import com.example.todolist.dto.ToDoList;

import java.util.ArrayList;

public class ListsRecyclerViewAdapter extends RecyclerView.Adapter<ListsRecyclerViewAdapter.ViewHolder> {
    private ArrayList<ToDoList> toDoLists;
    private ListClickListener clickListener;

    public ListsRecyclerViewAdapter() {
        toDoLists = new ArrayList<>();
    }

    public void setOnItemClickListener(ListClickListener clickListener) {
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
            holder.bind(list);
            holder.binding.setClickListener(clickListener);
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

        public void bind(ToDoList toDoList) {
            binding.setList(toDoList);
            binding.executePendingBindings();
        }
    }
}
