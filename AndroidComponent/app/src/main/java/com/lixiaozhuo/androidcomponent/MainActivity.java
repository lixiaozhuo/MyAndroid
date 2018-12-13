package com.lixiaozhuo.androidcomponent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lixiaozhuo.androidcomponent._01_application.ApplicationActivity1;
import com.lixiaozhuo.androidcomponent._03_activity.Activity1;
import com.lixiaozhuo.androidcomponent._04_broadcast.BroadcastActivity;
import com.lixiaozhuo.androidcomponent._02_calculator.CalculatorActivity;
import com.lixiaozhuo.androidcomponent._07_content_provider.ContentProviderActivity;
import com.lixiaozhuo.androidcomponent._05_intent.IntentActivity1;
import com.lixiaozhuo.androidcomponent._10_network.NetworkActivity;

import com.lixiaozhuo.androidcomponent._09_data.DataActivity;
import com.lixiaozhuo.androidcomponent._06_service.ServiceActivity;
import com.lixiaozhuo.androidcomponent._11_thread.ThreadActivity;
import com.lixiaozhuo.androidcomponent._08_view.ViewActivity;

/**
 * 主函数
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     *小应用
     * @param v
     */
    public void applicationTest(View v){
        Intent intent = new Intent(MainActivity.this,ApplicationActivity1.class);
        startActivity(intent);
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
        Intent intent = new Intent(MainActivity.this, DataActivity.class);
        startActivity(intent);
    }

    /**
     * 网络
     * @param v
     */
    public void networkTest(View v){
        Intent intent = new Intent(MainActivity.this, NetworkActivity.class);
        startActivity(intent);
    }

    /**
     * 线程通信
     * @param v
     */
    public void threadTest(View v){
        Intent intent = new Intent(MainActivity.this, ThreadActivity.class);
        startActivity(intent);
    }


}
