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
import android.widget.Toast;

import com.example.lq.myapplication.R;
import com.example.lq.myapplication.activity.WebActivity;
import com.example.lq.myapplication.bean.DbBean;
import com.example.lq.myapplication.bean.KnowInfoBean;
import com.example.lq.myapplication.utils.DbUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by 003 on 2019/5/2.
 */

public class KnowItemRlvAdaopter extends RecyclerView.Adapter {
    private static final String TAG = "jj";
    private final Context mContext;
    private final ArrayList<KnowInfoBean.DatasBean> mBeans;


    public KnowItemRlvAdaopter(Context context, ArrayList<KnowInfoBean.DatasBean> beans) {

        mContext = context;
        mBeans = beans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        return new Vh(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final Vh holder1 = (Vh) holder;
        KnowInfoBean.DatasBean bean = mBeans.get(position);
        holder1.mName.setText(bean.getAuthor());
        String name = bean.getChapterName();
        String chapterName = bean.getSuperChapterName();
        holder1.mText.setText(name + "/" + chapterName);
        holder1.mContext1.setText(bean.getTitle());
        holder1.mClock.setText(bean.getNiceDate());

        final DbBean dbBean = new DbBean();
        dbBean.setId(null);
        dbBean.setAuthor(bean.getAuthor());
        dbBean.setTime(bean.getNiceDate());
        dbBean.setTitle(bean.getTitle());
        dbBean.setType(bean.getSuperChapterName());
/*            //判断数据库里的数据与你子条目里的数据是否一致
            if (DbUtils.getDbUtils().has(dbBean)) {
                holder1.mIv.setBackgroundResource(R.mipmap.collect1);
            } else {
                holder1.mIv.setBackgroundResource(R.mipmap.ic_gold_like);
            }*/

        holder1.mIv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    holder1.mIv.setBackgroundResource(R.mipmap.collect1);
                    DbUtils.getDbUtils().insert(dbBean);
                    Toast.makeText(mContext, "关注成功", Toast.LENGTH_SHORT).show();
                } else {
                    holder1.mIv.setBackgroundResource(R.mipmap.ic_gold_like);
//                        DbUtils.getDbUtils().delete(dbBean);
                    Toast.makeText(mContext, "取消关注成功", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WebActivity.class);
                intent.putExtra("url", mBeans.get(position).getLink());
                intent.putExtra("title", mBeans.get(position).getTitle());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBeans.size();
    }

    public void addData(KnowInfoBean knowBean) {
        mBeans.addAll(knowBean.getDatas());
        notifyDataSetChanged();
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
