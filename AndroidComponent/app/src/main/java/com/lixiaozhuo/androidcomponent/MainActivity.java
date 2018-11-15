package com.lixiaozhuo.androidcomponent;

import android.content.ContentProvider;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lixiaozhuo.androidcomponent.activity.Activity1;
import com.lixiaozhuo.androidcomponent.broadcast.BroadcastActivity;
import com.lixiaozhuo.androidcomponent.calculator.CalculatorActivity;
import com.lixiaozhuo.androidcomponent.content_provider.ContentProviderActivity;
import com.lixiaozhuo.androidcomponent.intent.IntentActivity1;
import com.lixiaozhuo.androidcomponent.network.Network;

import com.lixiaozhuo.androidcomponent.save_data.SaveDataActivity;
import com.lixiaozhuo.androidcomponent.service.ServiceActivity;
import com.lixiaozhuo.androidcomponent.thread.MyThread;
import com.lixiaozhuo.androidcomponent.view.ViewActivity;

/**
 * 主函数
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * 计算器
     * @param v
     */
    public void calculatorTest(View v){
        Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
        startActivity(intent);
    }
    /**
     * 意图
     * @param v
     */
    public void intentTest(View v){
        Intent intent = new Intent(MainActivity.this, IntentActivity1.class);
        startActivity(intent);
    }

    /**
     * 活动
     * @param v
     */
    public void activityTest(View v){
        Intent intent = new Intent(MainActivity.this, Activity1.class);
        startActivity(intent);
    }

    /**
     * 服务
     * @param v
     */
    public void serviceTest(View v){
        Intent intent = new Intent(MainActivity.this, ServiceActivity.class);
        startActivity(intent);
    }

    /**
     * 广播
     * @param v
     */
    public void broadcastTest(View v){
        Intent intent = new Intent(MainActivity.this, BroadcastActivity.class);
        startActivity(intent);
    }

    /**
     * 内容提供者
     * @param v
     */
    public void contentProviderTest(View v){
        Intent intent = new Intent(MainActivity.this, ContentProviderActivity.class);
        startActivity(intent);
    }

    /**
     * 控件
     * @param v
     */
    public void viewTest(View v){
        Intent intent = new Intent(MainActivity.this, ViewActivity.class);
        startActivity(intent);
    }

    /**
     * 数据存储
     * @param v
     */
    public void saveDataTest(View v){
        Intent intent = new Intent(MainActivity.this, SaveDataActivity.class);
        startActivity(intent);
    }

    /**
     * 网络
     * @param v
     */
    public void networkTest(View v){
        Intent intent = new Intent(MainActivity.this, Network.class);
        startActivity(intent);
    }

    /**
     * 线程通信
     * @param v
     */
    public void threadTest(View v){
        Intent intent = new Intent(MainActivity.this, MyThread.class);
        startActivity(intent);
    }


}
