package com.androidveteran.android.todomvp.createTodo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import com.androidveteran.android.todomvp.R;
import com.androidveteran.android.todomvp.base.BaseDialogFragment;
import com.androidveteran.android.todomvp.database.model.Todo;
import com.androidveteran.android.todomvp.util.ProgressDialogUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by chint on 9/22/2016.
 */

public class AddEditTodoDialogFragment extends BaseDialogFragment implements AddEditTodoView {

    private static String ARG_TODO = "Todo";

    @BindView(R.id.et_add_edit_todo)
    AppCompatEditText mAppCompatEditText;

    AddEditTodoPresenter mAddEditTodoPresenter;

    public static AddEditTodoDialogFragment getInstance(Todo todo) {
        AddEditTodoDialogFragment addEditTodoDialogFragment = new AddEditTodoDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_TODO, todo);
        addEditTodoDialogFragment.setArguments(bundle);
        return addEditTodoDialogFragment;
    }

    @OnClick(R.id.btn_save)
    void onSaveClick() {
        mAddEditTodoPresenter.save(mAppCompatEditText.getText().toString());
    }

    @OnClick(R.id.btn_cancel)
    void onCancelClick() {
        closeDialog();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAddEditTodoPresenter = new AddEditTodoPresenter(this);
        mAddEditTodoPresenter.onCreate(getArguments());
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.dialog_fragment_add_edit_todo;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAddEditTodoPresenter.onViewCreated();
    }

    @Override
    public void showProgressDialog() {
        ProgressDialogUtils.getInstance(getActivity()).show();
    }

    @Override
    public void hideProgressDialog() {
        ProgressDialogUtils.getInstance(getActivity()).show();
    }

    @Override
    public void setTodo(String description) {
        mAppCompatEditText.setText(description);
    }

    @Override
    public void setError(String error) {
        mAppCompatEditText.requestFocus();
        mAppCompatEditText.setError(error);
    }

    @Override
    public Context getActivityContext() {
        return getActivity();
    }

    @Override
    public void closeDialog() {
        dismiss();
    }
}
