package com.androidveteran.android.todomvp.util;

import android.content.Context;
import android.util.Patterns;
import android.view.View;

import java.util.regex.Pattern;

/**
 * Created by chint on 9/6/2016.
 */

public class FormValidationUtils {

    private Context mContext;

    public FormValidationUtils(Context mContext) {
        this.mContext = mContext;
    }

    public void addView() {

    }

    public class ViewModel {
        private View mView;
    }

    public static class ValidationRules{
        public static final Pattern NOT_NULL = Pattern.compile("^null|$");
        public static final Pattern EMAIL = Patterns.EMAIL_ADDRESS;
        public static final Pattern PERSON_NAME = Pattern.compile("/^[a-z ,.'-]+$/i");
//        public static final Pattern
    }
}
