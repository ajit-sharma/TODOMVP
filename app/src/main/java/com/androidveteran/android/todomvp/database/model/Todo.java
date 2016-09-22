package com.androidveteran.android.todomvp.database.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ln-202 on 20/6/16.
 */

public class Todo implements Parcelable {

    public static final Creator<Todo> CREATOR = new Creator<Todo>() {
        @Override
        public Todo createFromParcel(Parcel in) {
            return new Todo(in);
        }

        @Override
        public Todo[] newArray(int size) {
            return new Todo[size];
        }
    };
    private long id;
    private String description;
    private int isComplete = 0;
    public Todo() {
    }

    public Todo(long id, String description, int isComplete) {
        this.id = id;
        this.description = description;
        this.isComplete = isComplete;
    }

    protected Todo(Parcel in) {
        id = in.readLong();
        description = in.readString();
        isComplete = in.readInt();
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", isComplete=" + isComplete +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int isComplete() {
        return isComplete;
    }

    public void setComplete(int complete) {
        isComplete = complete;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(description);
        dest.writeInt(isComplete);
    }
}
