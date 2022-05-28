package com.example.todolist.ui.listscreen.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;
import com.example.todolist.db.ToDoList;

import java.util.ArrayList;

public class ToDoRecViewAdapter extends RecyclerView.Adapter<ToDoRecViewAdapter.ViewHolder> {
    private ArrayList<ToDoList> toDoLists;
    private static ClickListener clickListener;

    public ToDoRecViewAdapter(ArrayList<ToDoList> toDoLists) {
        this.toDoLists = toDoLists;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        ToDoRecViewAdapter.clickListener = clickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
        private final TextView listName;
        private final TextView listDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            listName = itemView.findViewById(R.id.listName);
            listDescription = itemView.findViewById(R.id.listDescription);
        }

        public TextView getListNameView() {
            return listName;
        }

        public TextView getListDescription() {
            return listDescription;
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

    @NonNull
    @Override
    public ToDoRecViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);

        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ToDoList list = toDoLists.get(position);

        holder.getListNameView().setText(list.getListName());
        holder.getListDescription().setText(list.getDescription());
    }

    @Override
    public int getItemCount() {
        return toDoLists.size();
    }


}
