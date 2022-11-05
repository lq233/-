package com.example.lq.myapplication.utils;

import android.widget.Toast;

import com.example.lq.myapplication.app.MyApp;

/**
 * Created by 孤辟 on 2019/4/28.
 */

public class ToastShow {
    public static void ToastShort(String msg){
        Toast.makeText(MyApp.getMyApp(),msg,Toast.LENGTH_SHORT).show();
    }
    public static void ToastLong(String msg){
        Toast.makeText(MyApp.getMyApp(),msg,Toast.LENGTH_LONG).show();
    }
}
