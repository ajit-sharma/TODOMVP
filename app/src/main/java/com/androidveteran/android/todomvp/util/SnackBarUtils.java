package com.androidveteran.android.todomvp.util;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.androidveteran.android.todomvp.R;


/**
 * Created by chint on 9/4/2016.
 */
public class SnackBarUtils {

    private static SnackBarUtils ourInstance;
    private Context mContext;

    private SnackBarUtils(Context context) {
        mContext = context;
    }

    public static SnackBarUtils getInstance(Context context) {
        if (ourInstance == null)
            ourInstance = new SnackBarUtils(context);
        return ourInstance;
    }

    public void show(View view, int description) {
        show(view, description, Snackbar.LENGTH_SHORT);
    }

    public void show(View view, int description, int duration) {
        show(view, description, duration, R.string.default_snackbar_action_title, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void show(View view, int description, int duration, int actionTitle, View.OnClickListener onActionClickListener) {
        Snackbar.make(view, description, duration)
                .setAction(actionTitle, onActionClickListener)
                .show();
    }
}
