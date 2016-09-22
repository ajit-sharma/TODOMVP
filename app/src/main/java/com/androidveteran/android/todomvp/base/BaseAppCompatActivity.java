package com.androidveteran.android.todomvp.base;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;

import com.androidveteran.android.todomvp.R;
import com.androidveteran.android.todomvp.util.DialogUtils;
import com.androidveteran.android.todomvp.util.LogUtils;
import com.androidveteran.android.todomvp.util.ProgressDialogUtils;
import com.androidveteran.android.todomvp.util.SnackBarUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by chint on 8/27/2016.
 */

public abstract class BaseAppCompatActivity extends AppCompatActivity {

    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout mCoordinatorLayout;

    private int DEFAULT_REQUEST_CODE = 100;

    private int FRAGMENT_TRANSACTION_ADD = 200;
    private int FRAGMENT_TRANSACTION_REPLACE = 300;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
        ButterKnife.bind(this);
    }

    protected abstract int getLayoutResource();

    public void showSnackBar(int message) {
        SnackBarUtils.getInstance(this).show(mCoordinatorLayout, message);
    }

    public void showProgressDialog() {
        ProgressDialogUtils.getInstance(this).show();
    }

    public void showProgressDialog(String title) {
        ProgressDialogUtils.getInstance(this).show(title);
    }

    public void showProgressDialog(String title, String message) {
        ProgressDialogUtils.getInstance(this).show(title, message);
    }

    public void hideProgressDialog() {
        ProgressDialogUtils.getInstance(this).hide();
    }

    public void showAlertDialog(int title, int message) {
        DialogUtils.getInstance(this).alert(title, message);
    }

    public void showConfirmDialog(int title, int message, int positiveButtonTitle, DialogInterface.OnClickListener onPositiveButtonClickListener, int negativeButtonTitle, DialogInterface.OnClickListener onNegativeButtonClickListener) {
        DialogUtils.getInstance(this).confirm(title, message, positiveButtonTitle, onPositiveButtonClickListener, negativeButtonTitle, onNegativeButtonClickListener);
    }

    public void logError(Class tag, String message) {
        new LogUtils(tag).error(message);
    }

    public void logInformation(Class tag, String message) {
        new LogUtils(tag).information(message);
    }

    public void logDebug(Class tag, String message) {
        new LogUtils(tag).debug(message);
    }

    public void logWarning(Class tag, String message) {
        new LogUtils(tag).warning(message);
    }

    public void logVerbose(Class tag, String message) {
        new LogUtils(tag).verbose(message);
    }

    public void logWTF(Class tag, String message) {
        new LogUtils(tag).wtf(message);
    }

    public void launchActivity(Intent intent) {
        launchActivity(intent, false);
    }

    public void launchActivity(Intent intent, boolean finishCurrent) {
        launchActivity(intent, DEFAULT_REQUEST_CODE, finishCurrent);
    }

    public void launchActivity(Intent intent, int requestCode, boolean finishCurrent) {
        if (requestCode != DEFAULT_REQUEST_CODE) {
            startActivityForResult(intent, requestCode);
        } else {
            if (finishCurrent) {
                finish();
            }
            startActivity(intent);
        }
    }

    public void addFragment(Fragment fragment) {
        addFragment(fragment, false);
    }

    public void addFragment(Fragment fragment, boolean addToBackStack) {
        loadFragment(fragment, FRAGMENT_TRANSACTION_ADD, addToBackStack);
    }

    public void replaceFragment(Fragment fragment) {
        replaceFragment(fragment, false);
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        loadFragment(fragment, FRAGMENT_TRANSACTION_REPLACE, addToBackStack);
    }

    private void loadFragment(Fragment fragment, int transactionType) {
        loadFragment(fragment, transactionType, false);
    }

    private void loadFragment(Fragment fragment, int transactionType, boolean addToBackStack) {
//        loadFragment(fragment, R.id.frameLayout, transactionType, addToBackStack);
    }

    private void loadFragment(Fragment fragment, int containerId, int transactionType, boolean addToBackStack) {
        String fragmentName = fragment.getClass().getSimpleName();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragmentName);
        }

        if (transactionType == FRAGMENT_TRANSACTION_ADD) {
            fragmentTransaction.add(containerId, fragment, fragmentName);
        } else {
            fragmentTransaction.replace(containerId, fragment, fragmentName);
        }

        fragmentTransaction.commit();
    }

    void showDialogFragment(AppCompatDialogFragment appCompatDialogFragment) {
        appCompatDialogFragment.show(getSupportFragmentManager(), appCompatDialogFragment.getClass().getSimpleName());
    }
}
