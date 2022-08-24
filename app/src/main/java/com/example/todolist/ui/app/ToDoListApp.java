package com.example.todolist.ui.app;

import android.app.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ToDoListApp extends Application {
    private static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();

    private static final ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_CORES);

    public static ExecutorService getAppExecutor() {
        return executor;
    }
}
