package com.androidveteran.android.todomvp.createTodo;

import android.os.Bundle;
import android.text.TextUtils;

import com.androidveteran.android.todomvp.database.model.Todo;
import com.androidveteran.android.todomvp.service.TodoIntentService;

/**
 * Created by chint on 9/22/2016.
 */

public class AddEditTodoPresenter {
    private AddEditTodoView mAddEditTodoView;
    private Todo mTodo;
    private String ARG_TODO = "Todo";

    public AddEditTodoPresenter(AddEditTodoView mAddEditTodoView) {
        this.mAddEditTodoView = mAddEditTodoView;
    }

    public void onCreate(Bundle bundle) {
        if (bundle != null) {
            mTodo = bundle.getParcelable(ARG_TODO);
        }
    }

    public void onViewCreated() {
        if (mTodo != null)
            mAddEditTodoView.setTodo(mTodo.getDescription());
    }

    public void save(String description) {
        if (TextUtils.isEmpty(description)) {
            mAddEditTodoView.setError("Please enter TODO");
            return;
        }

        if (mTodo == null) {
            mTodo = new Todo();
            mTodo.setDescription(description);
            TodoIntentService.add(mAddEditTodoView.getActivityContext(), mTodo);
            mAddEditTodoView.closeDialog();
        } else {
            mTodo.setDescription(description);
            TodoIntentService.edit(mAddEditTodoView.getActivityContext(), mTodo);
            mAddEditTodoView.closeDialog();
        }
    }
}
