package com.example.lq.myapplication.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lq.myapplication.R;
import com.example.lq.myapplication.adapter.ProjectVpAdapter;
import com.example.lq.myapplication.bean.ProjectTabBean;
import com.example.lq.myapplication.http.ApiServer;
import com.liangxq.callback.ObServerCallback;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectFragment extends Fragment {


    private View view;
    private TabLayout mProjectTab;
    private ViewPager mProjectVp;
    private ProjectTabBean mTabBean;
    private ArrayList<Fragment> mFragments;

    public ProjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_project, container, false);
        initData();
        initView(inflate);
        return inflate;
    }

    private void initData() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiServer.URL).addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();
        ApiServer server = retrofit.create(ApiServer.class);
        final Observable<ProjectTabBean> tab = server.getProjectTab();
        tab.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProjectTabBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ProjectTabBean projectTabBean) {
                        List<ProjectTabBean.DataBean> data = projectTabBean.getData();
                        for (int i = 0; i < data.size(); i++) {
                            mFragments.add(new Project_item(data.get(i).getId()));
                            mProjectTab.addTab(mProjectTab.newTab().setText(data.get(i).getName()));
                        }
                        ProjectVpAdapter adapter = new ProjectVpAdapter(getChildFragmentManager(), mFragments);
                        mProjectVp.setAdapter(adapter);
                        mProjectTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                            @Override
                            public void onTabSelected(TabLayout.Tab tab) {
                                mProjectVp.setCurrentItem(tab.getPosition());

                            }

                            @Override
                            public void onTabUnselected(TabLayout.Tab tab) {

                            }

                            @Override
                            public void onTabReselected(TabLayout.Tab tab) {

                            }
                        });
                        mProjectVp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mProjectTab));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private void initView(View inflate) {
        mProjectTab = (TabLayout) inflate.findViewById(R.id.project_tab);
        mProjectVp = (ViewPager) inflate.findViewById(R.id.project_vp);

        mFragments = new ArrayList<>();


    }
}
