package com.example.todolist.utils;

import androidx.annotation.NonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class SynchronousExecutorService implements ExecutorService {
    private boolean shutdown;

    @Override
    public void shutdown() {shutdown = true;}

    @Override
    public List<Runnable> shutdownNow() {shutdown = true; return Collections.emptyList();}

    @Override
    public boolean isShutdown() {shutdown = true; return shutdown;}

    @Override
    public boolean isTerminated() {return shutdown;}

    @Override
    public boolean awaitTermination(final long timeout, final TimeUnit unit) {return true;}

    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return null;
    }

    @Override
    public <T> Future<T> submit(Runnable task, T result) {
        return null;
    }

    @Override
    public Future<?> submit(Runnable task) {
        return null;
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) {
        return null;
    }

    @Override
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) {
        return null;
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) {
        return null;
    }

    @Override
    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) {
        return null;
    }

    @Override
    public void execute(@NonNull final Runnable command) {command.run();}
}
