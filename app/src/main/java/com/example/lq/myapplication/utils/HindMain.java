package com.example.lq.myapplication.utils;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioGroup;


public class HindMain {

    private static boolean isScroll;
    public static void hind(RecyclerView recyclerView, final RadioGroup rg, final FloatingActionButton flb){

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == 0) {
                    isScroll = false;
                } else {
                    isScroll = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isScroll) {
                    if (dy < 0) {
                        rg.setVisibility(View.VISIBLE);
                        flb.setVisibility(View.VISIBLE);
                    } else if (dy > 0) {
                        rg.setVisibility(View.GONE);
                        flb.setVisibility(View.GONE);
                    }
                }
            }
        });
    }
}
