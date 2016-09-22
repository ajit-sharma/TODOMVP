package com.androidveteran.android.todomvp.todoList;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.androidveteran.android.todomvp.R;
import com.androidveteran.android.todomvp.base.BaseRecyclerAdapter;
import com.androidveteran.android.todomvp.createTodo.AddEditTodoDialogFragment;
import com.androidveteran.android.todomvp.database.model.Todo;
import com.androidveteran.android.todomvp.util.ProgressDialogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TodoListActivity extends AppCompatActivity implements TodoView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.rv_todo_list)
    RecyclerView mRecyclerView;
    private TodoPresenter mTodoPresenter;

    @OnClick(R.id.fab)
    void onAddClick() {
        showAddEditDialogFragment(null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);
        ButterKnife.bind(this);

        mTodoPresenter = new TodoPresenter(this);
        mTodoPresenter.onCreate();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mTodoPresenter.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTodoPresenter.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mTodoPresenter.onStop();
    }

    @Override
    public void showProgressDialog() {
        ProgressDialogUtils.getInstance(this).show();
    }

    @Override
    public void hideProgressDialog() {
        ProgressDialogUtils.getInstance(this).hide();
    }

    @Override
    public void setToolbar() {
        setSupportActionBar(mToolbar);
    }

    @Override
    public void showAddEditDialogFragment(Todo todo) {
        AddEditTodoDialogFragment addEditTodoDialogFragment = AddEditTodoDialogFragment.getInstance(todo);
        addEditTodoDialogFragment.show(getSupportFragmentManager(), AddEditTodoDialogFragment.class.getSimpleName());
    }

    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public void setAdapter(BaseRecyclerAdapter baseRecyclerAdapter) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(baseRecyclerAdapter);
    }
}
