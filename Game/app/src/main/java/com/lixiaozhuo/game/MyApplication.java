package com.lixiaozhuo.game;

import android.app.Application;
import android.content.Context;

/**
 * 自定义Application
 */
public class MyApplication extends Application {
    //上下文
    private static Context context;

    /**
     * 区域编码:默认为北京
     */
    public  static String  ADCode="110000";

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Context
        context = getApplicationContext();
    }

    /**
     * 获取上下文
     * @return
     */
    public static Context getContext(){
        return context;
    }
}
