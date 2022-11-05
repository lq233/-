package com.example.lq.myapplication.utils;

import android.content.Context;
import android.util.Log;

import com.example.lq.myapplication.app.Constants;


/**
 * Created by asus on 2019/3/5.
 */

public class Logger {
    public static void logD(String tag,String msg){
        if (Constants.isDebug){
            Log.d(tag, "logD: "+msg);
        }
    }
}
