package com.example.todolist.ui.startup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.todolist.R;
import com.example.todolist.databinding.MainActivityBinding;
import com.example.todolist.ui.listScreen.ListsFragment;

public class MainActivity extends AppCompatActivity {
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        binding.setLifecycleOwner(this);

        actionBar = getSupportActionBar();
        changeActionBarTitle("Lists");

        ListsFragment listsFragment = new ListsFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_view, listsFragment)
                .commit();
    }

    public void changeActionBarTitle(String newTitle) {
        actionBar.setTitle(newTitle);
    }
}