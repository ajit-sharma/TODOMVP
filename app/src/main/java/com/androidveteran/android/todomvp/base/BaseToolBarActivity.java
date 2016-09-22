package com.androidveteran.android.todomvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.androidveteran.android.todomvp.R;

import butterknife.BindView;

public abstract class BaseToolBarActivity extends BaseAppCompatActivity {

    private static final int RESOURCE_NO_MENU = 0;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBar();
    }

    public void setToolBar() {
        setSupportActionBar(mToolbar);
    }

    public Toolbar getToolBar() {
        return mToolbar;
    }

    protected abstract int getMenuResource();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getMenuResource() == RESOURCE_NO_MENU)
            return super.onCreateOptionsMenu(menu);
        else {
            getMenuInflater().inflate(getMenuResource(), menu);
            return true;
        }
    }
}