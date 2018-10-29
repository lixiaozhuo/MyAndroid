package com.lixiaozhuo.activitylifedemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * 活动1
 */
public class MainActivity extends AppCompatActivity {
    //打印信息标志
    private static final String TAG = "Activity1";

    /**
     *在活动第一次被创建的时候调用
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e(TAG,"onCreate");

        //进入活动2
        Button button = (Button)findViewById(R.id.button);
        button.setText("进入Activity2");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });
        //进入活动3(对话框模式)
        Button otherButton = (Button)findViewById(R.id.otherbutton);
        otherButton.setText("进入Activity3,以对话框形式");
        otherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this,MainActivity3.class);
                startActivity(intent);
            }
        });
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
     *还原实例状态
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e(TAG,"onRestoreInstanceState");
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
     *保存当前状态
     * @param savedInstanceState
     */
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.e(TAG,"onSaveInstanceState");
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
     *系统准备去启动或者恢复另一个活动的时候调用
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG,"onPause");
    }

    /**
     *活动完全不可见的时候调用
     */
    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"onStop");
    }

    /**
     * 在活动被销毁之前调用
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"onDestroy");
    }
}
