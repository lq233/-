package com.example.lq.myapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.lq.myapplication.R;
import com.example.lq.myapplication.activity.AlwaysActivity;
import com.example.lq.myapplication.activity.SearchActivity;
import com.example.lq.myapplication.activity.WebActivity;
import com.example.lq.myapplication.bean.Bean;
import com.example.lq.myapplication.bean.NaviBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;

public class MyNavigationAdapter extends RecyclerView.Adapter{
    private final FragmentActivity context;
    private final ArrayList<NaviBean.DataBean> dataBeans;
    private setOnClickListener sc;

    public MyNavigationAdapter(FragmentActivity context, ArrayList<NaviBean.DataBean> dataBeans) {
        this.context = context;
        this.dataBeans = dataBeans;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.navigation_item, viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final int num = i;
        ViewHolder viewHolder1 = (ViewHolder) viewHolder;
        viewHolder1.tv.setText(dataBeans.get(i).getName());
        ArrayList<String> title = new ArrayList<>();
        viewHolder1.tabflowlayout.removeAllViews();
        for (int j = 0; j < dataBeans.get(i).getArticles().size(); j++) {
            String title1 = dataBeans.get(i).getArticles().get(j).getTitle();
            title.add(title1);
        }
        if (title != null) {
            viewHolder1.tabflowlayout.setAdapter(new TagAdapter<String>(title) {
                @SuppressLint("ResourceAsColor")
                @Override
                public View getView(FlowLayout parent, int position, String s) {

                    int[] array = {R.color.Amber, R.color.arrow_color, R.color.colorPrimary
                            , R.color.colorPrimaryDark, R.color.Blue, R.color.color_title_bg, R.color.Green
                            , R.color.Deep_Orange, R.color.Lime, R.color.Teal, R.color.Indigo, R.color.Pink
                            , R.color.Yellow, R.color.Amber, R.color.Purple, R.color.Light_Green
                            , R.color.Light_Blue};

                    int random = (int) (Math.random() * (array.length - 1));
                    View inflate = LayoutInflater.from(context).inflate(R.layout.tag_textview1, parent, false);
                    TextView tv = inflate.findViewById(R.id.tag_textview);
                    tv.setText(s);
                    tv.setTextColor(context.getResources().getColor(array[random]));
                    return inflate;

                }
            });
        }
        viewHolder1.tabflowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                String link = dataBeans.get(num).getArticles().get(position).getLink();
                String name = dataBeans.get(position).getArticles().get(position).getTitle();
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra("url", link);
                intent.putExtra("title", name);
                context.startActivity(intent);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataBeans.size();
    }
    public void addData(List<NaviBean.DataBean> bean1) {
        dataBeans.clear();
        dataBeans.addAll(bean1);
        notifyDataSetChanged();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView tv;
        public TagFlowLayout tabflowlayout;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.tv = (TextView) rootView.findViewById(R.id.tv);
            this.tabflowlayout = (TagFlowLayout) rootView.findViewById(R.id.tabflowlayout);
        }
    }

    interface setOnClickListener {
        void setClickListener(View v, int porition);
    }

    public void setList(setOnClickListener sc) {
        this.sc = sc;
    }
}
