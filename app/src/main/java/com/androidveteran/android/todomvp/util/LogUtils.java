package com.androidveteran.android.todomvp.util;

import android.util.Log;

import com.androidveteran.android.todomvp.BuildConfig;


/**
 * Created by chint on 9/4/2016.
 */
public class LogUtils {

    private String mTag;
    private boolean mIsLoggable;

    public LogUtils(Class tag) {
        mTag = createTag(tag);
        mIsLoggable = BuildConfig.DEBUG;
    }

    private String createTag(Class tag) {
        String tempTag = tag.getSimpleName();
        return tempTag.length() > 23 ? tempTag.substring(0, 22) : tempTag;
    }

    public void error(String message) {
        if (mIsLoggable) {
            Log.e(mTag, message);
        }
    }

    public void warning(String message) {
        if (mIsLoggable) {
            Log.w(mTag, message);
        }
    }

    public void information(String message) {
        if (mIsLoggable) {
            Log.i(mTag, message);
        }
    }

    public void debug(String message) {
        if (mIsLoggable) {
            Log.d(mTag, message);
        }
    }

    public void verbose(String message) {
        if (mIsLoggable) {
            Log.v(mTag, message);
        }
    }

    public void wtf(String message) {
        if (mIsLoggable) {
            Log.wtf(mTag, message);
        }
    }
}