package com.example.todolist.ui.startup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;

import com.example.todolist.R;
import com.example.todolist.ui.listscreen.AddListDialog;
import com.example.todolist.ui.listscreen.ListsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        ListsFragment listsFragment = new ListsFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_view, listsFragment)
                .commit();

    }
}