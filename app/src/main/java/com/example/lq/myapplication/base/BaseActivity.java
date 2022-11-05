package com.example.lq.myapplication.base;

import android.view.View;

/**
 * Created by 003 on 2019/4/29.
 */

public abstract class BaseActivity<V, P extends BasePresnter<V>> extends SimpleActivity {
    public P presenter;

    @Override
    public void viewCreate(View view) {
        presenter = createPresenter();
        presenter.attachView((V) this);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter == null) {
            presenter.deachView();
            presenter = null;
        }
    }
}
