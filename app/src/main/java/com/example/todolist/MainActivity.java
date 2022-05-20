package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.todolist.listscreen.ListsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        ListsFragment listsFragment = new ListsFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, listsFragment)
                .commit();

    }
}