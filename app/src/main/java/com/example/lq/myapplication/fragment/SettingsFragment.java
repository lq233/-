package com.example.lq.myapplication.fragment;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lq.myapplication.R;
import com.example.lq.myapplication.activity.MainActivity;
import com.example.lq.myapplication.utils.SpUtil;
import com.example.lq.myapplication.utils.UIModeUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {


    private static final String TAG = "SettingsFragment";
    private View view;
    /**
     * 通用设置
     */
    private TextView mSettingUsageTv;
    private AppCompatCheckBox mCbSettingCache;
    private LinearLayout mSettingAutoCacheGroup;
    private AppCompatCheckBox mCbSettingImage;
    private AppCompatCheckBox mCbSettingNight;
    private CardView mSettingUsageCard;
    /**
     * 其他设置
     */
    private TextView mSettingOtherTv;
    /**
     * 意见反馈
     */
    private TextView mLlSettingFeedback;
    private TextView mTvSettingClear;
    private LinearLayout mLlSettingClear;
    private CardView mSettingOtherGroup;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_settings, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        mSettingUsageTv = (TextView) inflate.findViewById(R.id.setting_usage_tv);
        mCbSettingCache = (AppCompatCheckBox) inflate.findViewById(R.id.cb_setting_cache);
        mSettingAutoCacheGroup = (LinearLayout) inflate.findViewById(R.id.setting_auto_cache_group);
        mCbSettingImage = (AppCompatCheckBox) inflate.findViewById(R.id.cb_setting_image);
        mCbSettingNight = (AppCompatCheckBox) inflate.findViewById(R.id.cb_setting_night);
        mSettingUsageCard = (CardView) inflate.findViewById(R.id.setting_usage_card);
        mSettingOtherTv = (TextView) inflate.findViewById(R.id.setting_other_tv);
        mLlSettingFeedback = (TextView) inflate.findViewById(R.id.ll_setting_feedback);
        mTvSettingClear = (TextView) inflate.findViewById(R.id.tv_setting_clear);
        mLlSettingClear = (LinearLayout) inflate.findViewById(R.id.ll_setting_clear);
        mSettingOtherGroup = (CardView) inflate.findViewById(R.id.setting_other_group);
        int currentNightMode = getActivity().getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (currentNightMode == Configuration.UI_MODE_NIGHT_NO) {
            //判断当前是日间模式
            mCbSettingNight.setChecked(false);
        } else {
            mCbSettingNight.setChecked(true);
        }
        mCbSettingNight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isPressed()) {
                    //切换并保存模式
                    UIModeUtil.changeModeUI((MainActivity) getActivity());
/*                    //保存当前碎片的type
                    SpUtil.setParam(Constants.DAY_NIGHT_FRAGMENT_POS,MainActivity.TYPE_SETTINGS);*/
                }
            }
        });
        mCbSettingImage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SpUtil.setParam("key", isChecked);
                } else {
                    SpUtil.setParam("key", isChecked);
                }
            }
        });
    }
}
