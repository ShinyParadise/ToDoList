package com.example.todolist.ui.listScreen.listsRecycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;
import com.example.todolist.dto.ToDoList;

import java.util.ArrayList;

public class ListsRecyclerViewAdapter extends RecyclerView.Adapter<ListsRecyclerViewAdapter.ViewHolder> {
    private ArrayList<ToDoList> toDoLists;
    private static ClickListener clickListener;

    public ListsRecyclerViewAdapter(ArrayList<ToDoList> toDoLists) {
        this.toDoLists = toDoLists;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        ListsRecyclerViewAdapter.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ListsRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lists_recycler_view_item, parent, false);

        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ToDoList list = toDoLists.get(position);

        if (!(list.getHeader().isEmpty() || list.getDescription().isEmpty())) {
            holder.getListNameView().setText(list.getHeader());
            holder.getListDescriptionView().setText(list.getDescription());
        }
    }

    public void setToDoLists(ArrayList<ToDoList> toDoLists) {
        this.toDoLists = toDoLists;
    }

    @Override
    public int getItemCount() {
        return toDoLists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
        private final TextView listNameView;
        private final TextView listDescriptionView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            listNameView = itemView.findViewById(R.id.recycler_list_name);
            listDescriptionView = itemView.findViewById(R.id.recycler_list_description);
        }

        public TextView getListNameView() {
            return listNameView;
        }

        public TextView getListDescriptionView() {
            return listDescriptionView;
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
