package com.example.lq.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lq.myapplication.R;
import com.example.lq.myapplication.activity.WebActivity;
import com.example.lq.myapplication.bean.ProjectItemBean;
import com.example.lq.myapplication.utils.SpUtil;
import com.example.lq.myapplication.utils.Switchover;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by 003 on 2019/5/2.
 */

public class ProItemRlvAdaopter extends RecyclerView.Adapter {
    private static final String TAG = "jj";
    private final Context mContext;
    public final ArrayList<ProjectItemBean.DatasBean> mBeans;


    public ProItemRlvAdaopter(Context context, ArrayList<ProjectItemBean.DatasBean> beans) {

        mContext = context;
        mBeans = beans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.project_item, parent, false);
        return new Vh(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        Vh holder1 = (Vh) holder;
        ProjectItemBean.DatasBean bean = mBeans.get(position);
        holder1.mProjectAuthor.setText(bean.getAuthor());
        Boolean key = (Boolean) SpUtil.getParam("key", false);
        if (key) {
            Glide.with(mContext).load(R.drawable.ic_launcher_round).into(holder1.mProjectImg);
        } else {
            Glide.with(mContext).load(bean.getEnvelopePic()).into(holder1.mProjectImg);
        }

        Glide.with(mContext).load(R.drawable.ic_launcher_round).into(holder1.mProjectTx);
        holder1.mProjectTitle.setText(bean.getTitle());
        holder1.mProjectInfo.setText(bean.getDesc());
        holder1.mProjectDate.setText(bean.getNiceDate());

        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WebActivity.class);
                intent.putExtra("url", mBeans.get(position).getProjectLink());
                intent.putExtra("title", mBeans.get(position).getTitle());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBeans.size();
    }

    public void addData(ProjectItemBean knowBean) {
        mBeans.addAll(knowBean.getDatas());
        notifyDataSetChanged();
    }


    class Vh extends RecyclerView.ViewHolder {
        @BindView(R.id.project_img)
        ImageView mProjectImg;
        @BindView(R.id.project_tx)
        ImageView mProjectTx;
        @BindView(R.id.project_title)
        TextView mProjectTitle;
        @BindView(R.id.project_info)
        TextView mProjectInfo;
        @BindView(R.id.project_date)
        TextView mProjectDate;
        @BindView(R.id.project_author)
        TextView mProjectAuthor;

        public Vh(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
