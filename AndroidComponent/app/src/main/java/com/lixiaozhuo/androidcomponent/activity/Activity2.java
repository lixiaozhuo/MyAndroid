package com.lixiaozhuo.androidcomponent.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lixiaozhuo.androidcomponent.R;

/**
 * 活动2
 */
public class Activity2 extends Activity {
    //打印信息标志
    private static final String TAG = "App:Activity2";

    /**
     *只在活动第一次被创建时被调用
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);
        Log.e(TAG,"onCreate");

        Button button = findViewById( R.id.button);
        button.setText("进入Activity1");
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到活动1
                Intent intent = new Intent ( Activity2.this,Activity1.class);
                startActivity(intent);
            }
        });
    }

    /**
     *当活动由不可见变为可见时被调用
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG,"onStart");
    }


    /**
     *在活动准备好与用户进行交互时调用
     * 此时的活动一定位于返回栈的栈顶，并且处于运行状态
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG,"onResume");
    }

    /**
     *还原实例状态
     * @param savedInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e(TAG,"onRestoreInstanceState");
    }

    /**
     *保存当前状态
     * @param savedInstanceState
     */
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.e(TAG,"onSaveInstanceState");
    }

    /**
     *系统准备去启动或恢复另一个活动时调用
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"onPause");
    }

    /**
     *在活动完全不可见时被调用
     */
    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"onStop");
    }

    /**
     *活动由停止状态变为运行状态前调用
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG,"onRestart");
    }
    /**
     *活动被销毁前调用
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy");
    }
}
