package com.androidveteran.android.todomvp.todoList;

import android.view.View;
import android.widget.CompoundButton;

import com.androidveteran.android.todomvp.R;
import com.androidveteran.android.todomvp.base.BaseRecyclerAdapter;
import com.androidveteran.android.todomvp.database.model.Todo;
import com.androidveteran.android.todomvp.service.TodoIntentService;
import com.androidveteran.android.todomvp.util.LogUtils;
import com.androidveteran.android.todomvp.viewholder.TodoViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by chint on 9/22/2016.
 */

public class TodoPresenter {

    private TodoView mTodoView;
    public CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Todo todo = (Todo) buttonView.getTag();
            todo.setComplete(isChecked ? 1 : 0);
            TodoIntentService.edit(mTodoView.getActivityContext(), todo);
        }
    };
    public View.OnClickListener onDescriptionClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mTodoView.showAddEditDialogFragment((Todo) v.getTag());
        }
    };
    public View.OnClickListener onDeleteClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TodoIntentService.delete(mTodoView.getActivityContext(), (Todo) v.getTag());
        }
    };
    private BaseRecyclerAdapter mBaseRecyclerAdapter;

    public TodoPresenter(TodoListActivity mTodoListActivity) {
        this.mTodoView = mTodoListActivity;
    }

    public void onCreate() {
        mTodoView.setToolbar();
        mBaseRecyclerAdapter = new BaseRecyclerAdapter<Todo, TodoViewHolder>(TodoViewHolder.class, R.layout.list_item) {
            @Override
            protected void populateViewHolder(TodoViewHolder viewHolder, Todo model, int position) {

                viewHolder.getmAppCompatCheckBox().setTag(model);
                viewHolder.getmAppCompatCheckBox().setOnCheckedChangeListener(null);
                viewHolder.getmAppCompatCheckBox().setChecked(model.isComplete() == 1);
                viewHolder.getmAppCompatCheckBox().setOnCheckedChangeListener(onCheckedChangeListener);

                viewHolder.getmAppCompatTextView().setTag(model);
                viewHolder.getmAppCompatTextView().setText(model.getDescription());
                viewHolder.getmAppCompatTextView().setOnClickListener(onDescriptionClickListener);

                viewHolder.getmAppCompatImageView().setTag(model);
                viewHolder.getmAppCompatImageView().setOnClickListener(onDeleteClickListener);
            }
        };
        mTodoView.setAdapter(mBaseRecyclerAdapter);
    }

    public void onStart() {
        EventBus.getDefault().register(this);
    }

    public void onStop() {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetAllEvent(GetAllEvent getAllEvent) {
        new LogUtils(this.getClass().getClass()).information(getAllEvent.getTodoList().toString());
        mBaseRecyclerAdapter.clearItems();
        mBaseRecyclerAdapter.setItems(getAllEvent.getTodoList());
    }

    public void onResume() {
        getAllTodos();
    }

    private void getAllTodos() {
        TodoIntentService.getAll(mTodoView.getActivityContext());
    }
}
