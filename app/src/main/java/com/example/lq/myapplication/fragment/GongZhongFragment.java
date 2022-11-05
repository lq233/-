package com.example.lq.myapplication.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.lq.myapplication.R;
import com.example.lq.myapplication.adapter.GgVpAdapter;
import com.example.lq.myapplication.base.BaseFragment;
import com.example.lq.myapplication.bean.GgBean;
import com.example.lq.myapplication.presnter.GgPresenter;
import com.example.lq.myapplication.utils.HindMain;
import com.example.lq.myapplication.view.GgView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class GongZhongFragment extends BaseFragment<GgView, GgPresenter<GgView>> implements GgView {


    @BindView(R.id.gg_tab)
    TabLayout mGgTab;
    @BindView(R.id.gg_vp)
    ViewPager mGgVp;
    Unbinder unbinder;
    private List<GgBean> mList;

    public GongZhongFragment() {
        // Required empty public constructor
    }


    @Override
    public void initData() {
        super.initData();
        presenter.getData();

    }

    @Override
    public void showProgressbar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void onSuccess(List<GgBean> list) {
        mList = list;
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            fragments.add(new GgFragment(mList.get(i).getId(), mList.get(i).getName()));
        }
        GgVpAdapter adapter = new GgVpAdapter(getChildFragmentManager(), mList, fragments);
        mGgVp.setAdapter(adapter);
        mGgTab.setupWithViewPager(mGgVp);
    }

    @Override
    public void onFail(String string) {

    }

    @Override
    protected GgPresenter<GgView> initPresenter() {
        return new GgPresenter<>();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_gongzhong;
    }

}
