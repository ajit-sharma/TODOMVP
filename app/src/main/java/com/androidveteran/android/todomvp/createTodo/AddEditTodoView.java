package com.androidveteran.android.todomvp.createTodo;

import android.content.Context;

/**
 * Created by chint on 9/22/2016.
 */

public interface AddEditTodoView {
    void showProgressDialog();

    void hideProgressDialog();

    void setTodo(String description);

    void setError(String error);

    Context getActivityContext();

    void closeDialog();
}
