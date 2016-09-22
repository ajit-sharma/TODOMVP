package com.androidveteran.android.todomvp.database.table;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.androidveteran.android.todomvp.database.BaseTable;
import com.androidveteran.android.todomvp.database.DatabaseHelper;
import com.androidveteran.android.todomvp.database.model.Todo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ln-202 on 20/6/16.
 */

public class TableTodo extends BaseTable<Todo> {
    /**
     * Table Name
     */
    public static final String TABLE_NAME = "TableTodo";

    /**
     * Column Names
     */
    public static final String COLUMN_ID = "Id";
    public static final String COLUMN_DESCRIPTION = "Description";
    public static final String COLUMN_IS_COMPLETE = "IsComplete";

    /**
     * Create table Syntax
     */
    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_DESCRIPTION + " TEXT, "
            + COLUMN_IS_COMPLETE + " TEXT )";

    /**
     * Drop table Syntax
     */
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public TableTodo(Context context) {
        open(new DatabaseHelper(context));
    }

    @Override
    public List<Todo> getAll() {
        List<Todo> todoList = new ArrayList<>();
        Cursor cursor = mSqLiteDatabase.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                todoList.add(
                        new Todo(
                                cursor.getLong(cursor.getColumnIndex(COLUMN_ID)),
                                cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)),
                                cursor.getInt(cursor.getColumnIndex(COLUMN_IS_COMPLETE))
                        )
                );
            }
        }
        cursor.close();
        return todoList;
    }

    @Override
    public Todo getById(long id) {
        Todo todo = null;
        Cursor cursor = mSqLiteDatabase.query(TABLE_NAME, null, COLUMN_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToNext();
            todo = new Todo(
                    cursor.getLong(cursor.getColumnIndex(COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_IS_COMPLETE))
            );
        }
        cursor.close();
        return todo;
    }

    @Override
    public Todo insert(Todo todo) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_DESCRIPTION, todo.getDescription());
        contentValues.put(COLUMN_IS_COMPLETE, todo.isComplete());
        long id = mSqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if (id == -1) {
            todo = null;
        } else {
            todo.setId(id);
        }
        return todo;
    }

    @Override
    public Todo update(Todo todo) {
        Todo todoTemp = null;
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_DESCRIPTION, todo.getDescription());
        contentValues.put(COLUMN_IS_COMPLETE, todo.isComplete());
        int rowsAffected = mSqLiteDatabase.update(TABLE_NAME, contentValues, COLUMN_ID + "=?", new String[]{String.valueOf(todo.getId())});
        if (rowsAffected > 0) {
            todoTemp = getById(todo.getId());
        }
        return todoTemp;
    }

    @Override
    public Todo delete(long todoId) {
        Todo user = getById(todoId);
        mSqLiteDatabase.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(todoId)});
        return user;
    }
}
