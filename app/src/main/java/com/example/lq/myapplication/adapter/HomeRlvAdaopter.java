package com.example.lq.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.DngCreator;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lq.myapplication.R;
import com.example.lq.myapplication.activity.WebActivity;
import com.example.lq.myapplication.bean.BannerBean;
import com.example.lq.myapplication.bean.DbBean;
import com.example.lq.myapplication.bean.ListData;
import com.example.lq.myapplication.utils.DbUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by 003 on 2019/5/2.
 */

public class HomeRlvAdaopter extends RecyclerView.Adapter {
    private static final String TAG = "jj";
    private final Context mContext;
    public final ArrayList<ListData.DatasBean> mBeans;
    public final ArrayList<BannerBean> mBanners;


    public HomeRlvAdaopter(Context context, ArrayList<ListData.DatasBean> beans, ArrayList<BannerBean> banners) {
        mContext = context;
        mBeans = beans;
        mBanners = banners;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.main_banner, null);
            return new Vh1(inflate);
        } else {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
            return new Vh(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        int viewType = getItemViewType(position);
        if (viewType == 1) {
            Vh1 holder1 = (Vh1) holder;
            ArrayList<String> title = new ArrayList<>();
            ArrayList<String> img = new ArrayList<>();
            for (int i = 0; i < mBanners.size(); i++) {
                title.add(mBanners.get(i).getTitle());
                img.add(mBanners.get(i).getUrl());
            }
            holder1.mBanner.setImages(mBanners).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    BannerBean bean = (BannerBean) path;
                    Log.d("jj", bean.toString());
                    Glide.with(context).load(bean.getImagePath()).into(imageView);
                }
            });
            holder1.mBanner.setDelayTime(img.size()*400);    //设置四秒播放
            holder1.mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);  // 设置轮播图样式
            holder1.mBanner .setBannerAnimation(Transformer.DepthPage);
            holder1.mBanner.setIndicatorGravity(BannerConfig.CENTER);    //设置指示器位置
            holder1.mBanner.setBannerTitles(title);
            //  hanld.mHomeBanner.setBannerStyle(BannerConfig.NUM_INDICATOR);
            holder1.mBanner.start();
        } else {
            final Vh holder1 = (Vh) holder;
            int newsPosition = position;
            if (mBanners.size() > 0) {
                newsPosition -= 1;
            }
            final ListData.DatasBean bean = mBeans.get(newsPosition);
         //   Glide.with(mContext).load(R.mipmap.ic_launcher).into(holder1.mImg);
            holder1.mName.setText(bean.getAuthor());
            holder1.mContext1.setText(bean.getTitle());
            holder1.mClock.setText(bean.getNiceDate());
            holder1.mText.setText(bean.getSuperChapterName());


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


/*            //判断数据库里的数据与你子条目里的数据是否一致
            if (DbUtils.getDbUtils().has(dbBean)){
                holder.btn.setText("取消");
            }else {
                holder.btn.setText("关注");
            }
            holder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.btn.getText().equals("关注")){
                        holder.btn.setText("取消");
                        DbUtils.getDbUtils().insert(dbBean);
                        Toast.makeText(context, "关注成功"+position+"", Toast.LENGTH_SHORT).show();
                    }else {
                        holder.btn.setText("关注");
                        DbUtils.getDbUtils().delete(dbBean);
                        Toast.makeText(context, "取消成功"+position+"", Toast.LENGTH_SHORT).show();
                    }
                }
            });*/


            final int finalNewsPosition = newsPosition;
            holder1.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, WebActivity.class);
                    intent.putExtra("url", mBeans.get(finalNewsPosition).getLink());
                    intent.putExtra("title", mBeans.get(finalNewsPosition).getTitle());
                    mContext.startActivity(intent);
                }
            });
        }
    }

    public void addData(ListData listData) {
        mBeans.addAll(listData.getDatas());
        notifyDataSetChanged();
        Log.d(TAG, "addData: " + mBeans.size());
    }

    @Override
    public int getItemCount() {
        if (mBanners != null && mBanners.size() > 0) {
            return mBeans.size() + 1;
        } else {
            return mBeans.size();
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 1;
        } else {
            return 2;
        }
    }

    public void addBanner(List<BannerBean> bannerBean) {
        mBanners.addAll(bannerBean);
        Log.d(TAG, "addBanner: " + mBanners.size());
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

    class Vh1 extends RecyclerView.ViewHolder {

        private final Banner mBanner;

        public Vh1(View itemView) {
            super(itemView);
            mBanner = itemView.findViewById(R.id.main_banner);
        }
    }
}
