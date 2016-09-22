package com.androidveteran.android.todomvp.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.androidveteran.android.todomvp.R;

/**
 * Created by chint on 9/4/2016.
 */
public class DialogUtils {
    private static DialogUtils ourInstance = null;
    private final Context mContext;
    private AlertDialog mAlertDialog;

    private DialogUtils(Context context) {
        mContext = context;
    }

    public static DialogUtils getInstance(Context context) {
        if (ourInstance == null)
            ourInstance = new DialogUtils(context);
        return ourInstance;
    }

    public void alert(int title, int message) {
        alert(title, message, R.string.default_alert_dialog_button_title);
    }

    public void alert(int title, int message, int buttonTitle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNeutralButton(buttonTitle, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();
            }
        });
        mAlertDialog = builder.create();
        mAlertDialog.show();
    }

    public void confirm(int title, int message, int positiveButtonTitle, DialogInterface.OnClickListener onPositiveButtonClickListener, int negativeButtonTitle, DialogInterface.OnClickListener onNegativeButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(positiveButtonTitle, onPositiveButtonClickListener);
        builder.setNegativeButton(negativeButtonTitle, onNegativeButtonClickListener);
        mAlertDialog = builder.create();
        mAlertDialog.show();
    }
}