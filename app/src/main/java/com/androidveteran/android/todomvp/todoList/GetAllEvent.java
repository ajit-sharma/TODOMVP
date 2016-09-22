package com.androidveteran.android.todomvp.todoList;

import com.androidveteran.android.todomvp.database.model.Todo;

import java.util.List;

/**
 * Created by chint on 9/23/2016.
 */
public class GetAllEvent {
    private List<Todo> todoList;

    public GetAllEvent(List<Todo> todoList) {
        this.todoList = todoList;
    }

    public List<Todo> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<Todo> todoList) {
        this.todoList = todoList;
    }
}
