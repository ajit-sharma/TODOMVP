package com.androidveteran.android.todomvp.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.androidveteran.android.todomvp.database.model.Todo;
import com.androidveteran.android.todomvp.database.table.TableTodo;
import com.androidveteran.android.todomvp.todoList.GetAllEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class TodoIntentService extends IntentService {
    private static final String ACTION_GET_ALL = "GetAll";
    private static final String ACTION_ADD = "Add";
    private static final String ACTION_EDIT = "Edit";
    private static final String ACTION_DELETE = "Delete";

    private static final String EXTRA_TODO = "Todo";

    public TodoIntentService() {
        super("TodoIntentService");

    }

    public static void getAll(Context context) {
        Intent intent = new Intent(context, TodoIntentService.class);
        intent.setAction(ACTION_GET_ALL);
        context.startService(intent);
    }

    public static void add(Context context, Todo todo) {
        Intent intent = new Intent(context, TodoIntentService.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_TODO, todo);
        context.startService(intent);
    }

    public static void edit(Context context, Todo todo) {
        Intent intent = new Intent(context, TodoIntentService.class);
        intent.setAction(ACTION_EDIT);
        intent.putExtra(EXTRA_TODO, todo);
        context.startService(intent);
    }

    public static void delete(Context context, Todo todo) {
        Intent intent = new Intent(context, TodoIntentService.class);
        intent.setAction(ACTION_DELETE);
        intent.putExtra(EXTRA_TODO, todo);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_GET_ALL.equals(action)) {
                getAllTodo();
            } else if (ACTION_ADD.equals(action)) {
                addTodo((Todo) intent.getParcelableExtra(EXTRA_TODO));
            } else if (ACTION_EDIT.equals(action)) {
                editTodo((Todo) intent.getParcelableExtra(EXTRA_TODO));
            } else if (ACTION_DELETE.equals(action)) {
                deleteTodo((Todo) intent.getParcelableExtra(EXTRA_TODO));
            }
        }
    }

    private void deleteTodo(Todo todo) {
        TableTodo tableTodo = new TableTodo(this);
        tableTodo.delete(todo.getId());
        tableTodo.close();
        getAllTodo();
    }

    private void addTodo(Todo todo) {
        TableTodo tableTodo = new TableTodo(this);
        tableTodo.insert(todo);
        tableTodo.close();
        getAllTodo();
    }

    private void editTodo(Todo todo) {
        TableTodo tableTodo = new TableTodo(this);
        tableTodo.update(todo);
        tableTodo.close();
        getAllTodo();
    }

    private void getAllTodo() {
        TableTodo tableTodo = new TableTodo(this);
        List<Todo> todoList = tableTodo.getAll();
        tableTodo.close();
        EventBus.getDefault().post(new GetAllEvent(todoList));
    }
}
