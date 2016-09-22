package com.androidveteran.android.todomvp.database;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by ln-202 on 20/6/16.
 */

public abstract class BaseTable<T>{

    protected SQLiteDatabase mSqLiteDatabase;

    protected void open(DatabaseHelper databaseHelper) {
        mSqLiteDatabase = databaseHelper.getWritableDatabase();
    }

    public void close() {
        mSqLiteDatabase.close();
    }

    protected abstract List<T> getAll();

    protected abstract  T getById(long id);

    protected abstract T insert(T t);

    protected abstract T update(T t);

    protected abstract T delete(long id);
}
