package com.androidveteran.android.todomvp.todoList;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.androidveteran.android.todomvp.base.BaseRecyclerAdapter;
import com.androidveteran.android.todomvp.database.model.Todo;

/**
 * Created by chint on 9/22/2016.
 */

public interface TodoView {
    void showProgressDialog();

    void hideProgressDialog();

    void setToolbar();

    void showAddEditDialogFragment(Todo todo);

    Context getActivityContext();

    void setAdapter(BaseRecyclerAdapter baseRecyclerAdapter);
}
