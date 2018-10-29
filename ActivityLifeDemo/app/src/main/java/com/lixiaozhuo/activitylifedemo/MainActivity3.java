package com.lixiaozhuo.activitylifedemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * 活动3
 */
public class MainActivity3 extends Activity {
    //打印信息标志
    private static final String TAG = "Activity3";

    /**
     *在活动第一次被创建的时候调用
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Log.e(TAG,"onCreate");
    }

    /**
     *关闭按钮事件
     * @param view
     */
    public void close(View view){
        this.finish();
    }

    /**
     *在活动由不可见变为可见的时候调用
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG,"onStart");
    }

    /**
     *在活动准备好和用户进行交互的时候调用，
     * 此时的活动一定位于返回栈的栈顶，并且处于运行状态
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"onResume");
    }

    /**
     *在活动由停止状态变为运行状态之前调用
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG,"onRestart");
    }

    /**
     *在系统准备去启动或者恢复另一个活动的时候调用
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"onPause");
    }

    /**
     *在活动完全不可见的时候调用
     */
    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"onStop");
    }

    /**
     *在活动被销毁之前调用
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy");
    }
}
