package com.example.lq.myapplication.base;

import java.lang.ref.WeakReference;

/**
 * Created by 003 on 2019/4/29.
 */

public class BasePresnter<V> {
    private WeakReference<V> mReference;
    public V mView;

    public void attachView(V v) {
        mReference = new WeakReference<>(v);
        mView = mReference.get();
    }

    public void deachView() {
        if (mReference != null) {
            mReference.clear();
        }
    }
}
