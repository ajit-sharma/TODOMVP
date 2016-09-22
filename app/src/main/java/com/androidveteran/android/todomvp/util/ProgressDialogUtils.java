package com.androidveteran.android.todomvp.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.androidveteran.android.todomvp.R;

/**
 * Created by chint on 9/4/2016.
 */
public class ProgressDialogUtils {

    private static ProgressDialogUtils ourInstance = null;
    private final Context mContext;

    private ProgressDialog mProgressDialog;
    private String mDefaultTitle;
    private String mDefaultMessage;
    private boolean mDefaultIndeterminate = true;
    private boolean mDefaultCancelable = false;
    private DialogInterface.OnCancelListener mDefaultOnCancelListener = null;

    private ProgressDialogUtils(Context context) {
        mContext = context;
        mDefaultTitle = mContext.getString(R.string.default_progress_dialog_title);
        mDefaultMessage = mContext.getString(R.string.default_progress_dialog_message);
    }

    public static ProgressDialogUtils getInstance(Context context) {
        if (ourInstance == null)
            ourInstance = new ProgressDialogUtils(context);
        return ourInstance;
    }

    public void show() {
        show(mDefaultTitle, mDefaultMessage, mDefaultIndeterminate, mDefaultCancelable, mDefaultOnCancelListener);
    }

    public void show(String title) {
        show(title, mDefaultMessage);
    }

    public void show(String title, String message) {
        show(title, message, mDefaultIndeterminate, mDefaultCancelable, null);
    }

    public void show(String title, String message, boolean indeterminate, boolean cancelable, DialogInterface.OnCancelListener onCancelListener) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.setMessage(message);
        } else {
            mProgressDialog = ProgressDialog.show(mContext, title, message, indeterminate, cancelable, onCancelListener);
        }
    }

    public void hide() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
