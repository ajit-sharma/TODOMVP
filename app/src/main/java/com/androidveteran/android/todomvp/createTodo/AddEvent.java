package com.androidveteran.android.todomvp.createTodo;

import com.androidveteran.android.todomvp.database.model.Todo;

/**
 * Created by chint on 9/23/2016.
 */

public class AddEvent {
    private Todo mTodo;

    public AddEvent(Todo mTodo) {
        this.mTodo = mTodo;
    }

    public Todo getmTodo() {
        return mTodo;
    }

    public void setmTodo(Todo mTodo) {
        this.mTodo = mTodo;
    }
}
