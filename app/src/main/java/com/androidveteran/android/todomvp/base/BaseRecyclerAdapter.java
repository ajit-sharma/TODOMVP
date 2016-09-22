package com.androidveteran.android.todomvp.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chint on 9/23/2016.
 */

public abstract class BaseRecyclerAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private Class<T> mModelClass;
    private int mModelLayout;
    private Class<VH> mViewHolderClass;
    private List<T> mList = new ArrayList<>();

    public BaseRecyclerAdapter(Class<VH> viewHolderClass, int modelLayout) {
        mModelLayout = modelLayout;
        mViewHolderClass = viewHolderClass;
    }

    public void setmList(List<T> mList) {
        this.mList = mList;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup view = (ViewGroup) LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        try {
            Constructor<VH> constructor = mViewHolderClass.getConstructor(View.class);
            return constructor.newInstance(view);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onBindViewHolder(VH viewHolder, int position) {
        T model = getItem(position);
        populateViewHolder(viewHolder, model, position);
    }

    @Override
    public int getItemViewType(int position) {
        return mModelLayout;
    }

    abstract protected void populateViewHolder(VH viewHolder, T model, int position);

    public void addItem(T t) {
        int index = mList.size();
        mList.add(index, t);
        notifyItemInserted(index);
    }

    public void updateItem(T t, int position) {
        mList.set(position, t);
        notifyItemChanged(position);
    }

    public void removeItem(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public void setItems(List<T> items) {
        mList = items;
        notifyDataSetChanged();
    }

    public void clearItems() {
        mList.clear();
        notifyDataSetChanged();
    }
}
