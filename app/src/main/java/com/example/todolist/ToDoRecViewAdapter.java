package com.example.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.models.ToDoList;

import java.util.ArrayList;

public class ToDoRecViewAdapter extends RecyclerView.Adapter<ToDoRecViewAdapter.ViewHolder> {
    private ArrayList<ToDoList> toDoLists;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView listName;
        private final TextView listDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            listName = itemView.findViewById(R.id.listName);
            listDescription = itemView.findViewById(R.id.listDescription);
        }

        public TextView getListNameView() {
            return listName;
        }

        public TextView getListDescription() {
            return listDescription;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);

        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getListNameView().setText(toDoLists.get(position).getListName());
        holder.getListDescription().setText(toDoLists.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return toDoLists.size();
    }
}
