package com.example.lq.myapplication.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.lq.myapplication.bean.KonwTabBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 003 on 2019/5/5.
 */

public class KnowVpAdapter extends FragmentPagerAdapter {
    private final List<KonwTabBean.DataBean.ChildrenBean> mList;
    private final ArrayList<Fragment> mFragments;

    public KnowVpAdapter(FragmentManager fm, List<KonwTabBean.DataBean.ChildrenBean> list, ArrayList<Fragment> fragments) {
        super(fm);
        mList = list;
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position).getName();
    }
}
