package com.example.todolist.ui.listscreen.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;
import com.example.todolist.db.models.ListModel;

import java.util.ArrayList;

public class ListsRecyclerViewAdapter extends RecyclerView.Adapter<ListsRecyclerViewAdapter.ViewHolder> {
    private ArrayList<ListModel> toDoLists;
    private static ClickListener clickListener;

    public ListsRecyclerViewAdapter(ArrayList<ListModel> toDoLists) {
        this.toDoLists = toDoLists;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        ListsRecyclerViewAdapter.clickListener = clickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
        private final TextView listName;
        private final TextView listDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            listName = itemView.findViewById(R.id.recycler_list_name);
            listDescription = itemView.findViewById(R.id.recycler_list_description);
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
    public ListsRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lists_recycler_view_item, parent, false);

        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListModel list = toDoLists.get(position);

        if (!(list.name.isEmpty() || list.description.isEmpty())) {
            holder.getListNameView().setText(list.name);
            holder.getListDescription().setText(list.description);
        }
    }

    public void setToDoLists(ArrayList<ListModel> toDoLists) {
        this.toDoLists = toDoLists;
    }

    @Override
    public int getItemCount() {
        return toDoLists.size();
    }
}
