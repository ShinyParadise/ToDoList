package com.example.todolist.dao.list;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.telecom.Call;

import androidx.annotation.NonNull;

import com.example.todolist.db.DatabaseContract;
import com.example.todolist.db.ToDoListDatabaseHelper;
import com.example.todolist.db.models.ListModel;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ListDAO implements IListDAO {
    private static SQLiteOpenHelper databaseHelper = null;

    private final ExecutorService executor = Executors.newCachedThreadPool();

    public ListDAO(Context context) {
        databaseHelper = ToDoListDatabaseHelper.getInstance(context);
    }

    public ListModel create(@NonNull ListModel listModel) throws ExecutionException, InterruptedException {
        Runnable runnable = () -> {
            SQLiteDatabase db = databaseHelper.getWritableDatabase();
            SQLiteStatement insertStatement = db.compileStatement(DatabaseContract.ToDoListTable.INSERT_VALUES);

            String[] args = { listModel.header, listModel.description };
            insertStatement.bindAllArgsAsStrings(args);
            insertStatement.executeInsert();
        };

        executor.submit(runnable);

        return getLastInsertedList();
    }

    public ListModel getListByID(int listID) throws ExecutionException, InterruptedException {
        Callable<ListModel> callable = () -> {
            SQLiteDatabase db = databaseHelper.getReadableDatabase();
            Cursor request = db.rawQuery(DatabaseContract.ToDoListTable.SELECT_LIST_BY_ID,
                    new String[] { Integer.toString(listID) }
            );

            if (!request.moveToFirst()) {
                request.close();
                throw new NullPointerException();
            }

            ListModel listModel = toList(request);

            request.close();
            return listModel;
        };

        Future<ListModel> futureList = executor.submit(callable);

        return futureList.get();
    }

    public ArrayList<ListModel> getAllLists() throws ExecutionException, InterruptedException {
        Callable<ArrayList<ListModel>> callable = () -> {
            SQLiteDatabase db = databaseHelper.getReadableDatabase();
            Cursor request = db.rawQuery(DatabaseContract.ToDoListTable.SELECT_ALL_LISTS, null);
            ArrayList<ListModel> lists = new ArrayList<>();

            if (request.moveToFirst()) {
                do {
                    ListModel newList = toList(request);
                    lists.add(newList);
                } while (request.moveToNext());
            }

            request.close();
            return lists;
        };

        Future<ArrayList<ListModel>> futureLists = executor.submit(callable);

        return futureLists.get();
    }

    @NonNull
    private ListModel getLastInsertedList() throws ExecutionException, InterruptedException {
        Callable<ListModel> callable = () -> {
            SQLiteDatabase db = databaseHelper.getReadableDatabase();
            Cursor request = db.rawQuery(DatabaseContract.ToDoListTable.SELECT_LAST_ITEM, null);

            request.moveToFirst();
            ListModel lastList = toList(request);

            request.close();

            return lastList;
        };

        Future<ListModel> futureLastList = executor.submit(callable);
        return futureLastList.get();
    }

    @NonNull
    private ListModel toList(@NonNull Cursor request) {
        int id = Integer.parseInt(request.getString(0));
        String name = request.getString(1);
        String description = request.getString(2);

        return new ListModel(id, name, description);
    }
}
