package com.example.lq.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lq.myapplication.R;
import com.example.lq.myapplication.activity.WebActivity;
import com.example.lq.myapplication.bean.DbBean;
import com.example.lq.myapplication.bean.GgItemBean;
import com.example.lq.myapplication.utils.DbUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by 003 on 2019/5/2.
 */

public class CollectRlvAdaopter extends RecyclerView.Adapter {
    private static final String TAG = "jj";
    private final Context mContext;
    public final ArrayList<DbBean> mBeans;


    public CollectRlvAdaopter(Context context, ArrayList<DbBean> beans) {

        mContext = context;
        mBeans = beans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.collect_item, parent, false);
        return new Vh(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final Vh holder1 = (Vh) holder;
        final DbBean bean = mBeans.get(position);
        String author = bean.getAuthor();
        holder1.mName.setText(author);
        holder1.mText.setText(bean.getType());
        holder1.mContext1.setText(bean.getTitle());
        holder1.mClock.setText(bean.getTime());

        holder1.mIv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DbUtils.getDbUtils().delete(bean);
                mBeans.remove(bean);
                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        return mBeans.size();
    }


    class Vh extends RecyclerView.ViewHolder {

        @BindView(R.id.img)
        ImageView mImg;
        @BindView(R.id.name)
        TextView mName;
        @BindView(R.id.text)
        TextView mText;
        @BindView(R.id.context1)
        TextView mContext1;
        @BindView(R.id.iv)
        CheckBox mIv;
        @BindView(R.id.iv2)
        ImageView mIv2;
        @BindView(R.id.clock)
        TextView mClock;

        public Vh(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
