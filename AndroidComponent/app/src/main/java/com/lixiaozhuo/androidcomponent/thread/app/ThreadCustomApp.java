package com.lixiaozhuo.androidcomponent.thread.app;

import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.Log;

/**
 * 线程自定义app
 */
public class ThreadCustomApp extends Application {
    //标志
    private String TAG = "App:ThreadApp";
    //
    private String textData = "default";

    public void setTextData(String textData) {
        this.textData = textData;
    }

    public String getTextData() {
        return textData;
    }


    @Override
    public void onCreate() {
        // 程序创建
        Log.e(TAG, "onCreate");
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        // 程序终止
        Log.e(TAG, "onTerminate");
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        // 低内存
        Log.e(TAG, "onLowMemory");
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        // 内存清理
        Log.e(TAG, "onTrimMemory");
        super.onTrimMemory(level);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        //
        Log.e(TAG, "onConfigurationChanged");
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void registerActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
        //
        Log.e(TAG, "registerActivityLifecycleCallbacks");
        super.registerActivityLifecycleCallbacks(callback);
    }

    @Override
    public void registerComponentCallbacks(ComponentCallbacks callback) {
        //
        Log.e(TAG, "registerComponentCallbacks");
        super.registerComponentCallbacks(callback);
    }

    @Override
    public void unregisterActivityLifecycleCallbacks(ActivityLifecycleCallbacks callback) {
        //
        Log.e(TAG, "unregisterActivityLifecycleCallbacks");
        super.unregisterActivityLifecycleCallbacks(callback);
    }

    @Override
    public void unregisterComponentCallbacks(ComponentCallbacks callback) {
        //
        Log.e(TAG, "unregisterComponentCallbacks");
        super.unregisterComponentCallbacks(callback);
    }


}
