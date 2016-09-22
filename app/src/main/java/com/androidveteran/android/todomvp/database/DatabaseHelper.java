package com.androidveteran.android.todomvp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.androidveteran.android.todomvp.BuildConfig;
import com.androidveteran.android.todomvp.R;
import com.androidveteran.android.todomvp.database.table.TableTodo;

/**
 * Created by ln-202 on 20/6/16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, context.getString(context.getApplicationInfo().labelRes) + context.getString(R.string.database_extension), null, BuildConfig.VERSION_CODE);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TableTodo.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(TableTodo.DROP_TABLE);
    }
}
