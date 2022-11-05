package com.example.lq.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lq.myapplication.R;
import com.example.lq.myapplication.activity.KnowActivity;
import com.example.lq.myapplication.bean.KnowBean;
import com.example.lq.myapplication.bean.ListData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.POST;


/**
 * Created by 003 on 2019/5/2.
 */

public class KnowRlvAdaopter extends RecyclerView.Adapter {
    private static final String TAG = "jj";
    private final Context mContext;
    private final ArrayList<KnowBean> mBeans;


    public KnowRlvAdaopter(Context context, ArrayList<KnowBean> beans) {

        mContext = context;
        mBeans = beans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.konw_item, parent, false);
        return new Vh(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        Vh holder1 = (Vh) holder;
        KnowBean bean = mBeans.get(position);
        holder1.mKnowTitle.setText(bean.getName());
        List<KnowBean.ChildrenBean> beans = bean.getChildren();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < beans.size(); i++) {
            String name = beans.get(i).getName();
            buffer.append(name + "  ");
        }
        holder1.mKnowInfo.setText(buffer.toString());
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, KnowActivity.class);
                intent.putExtra("position", position);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBeans.size();
    }

    public void addData(List<KnowBean> knowBean) {
        mBeans.addAll(knowBean);
    }


    class Vh extends RecyclerView.ViewHolder {


        private final TextView mKnowInfo;
        private final TextView mKnowTitle;

        public Vh(View itemView) {
            super(itemView);
            mKnowInfo = itemView.findViewById(R.id.konw_info);
            mKnowTitle = itemView.findViewById(R.id.konw_title);
        }
    }
}
