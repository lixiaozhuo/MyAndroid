package com.lixiaozhuo.androidcomponent._11_thread.handler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lixiaozhuo.androidcomponent.R;
import com.lixiaozhuo.androidcomponent._11_thread.ThreadActivity;
import com.lixiaozhuo.androidcomponent._11_thread.handler.communication.CommunicationActivity;
import com.lixiaozhuo.androidcomponent._11_thread.handler.handler1.Handler1Activity;
import com.lixiaozhuo.androidcomponent._11_thread.handler.handler2.Handler2Activity;
import com.lixiaozhuo.androidcomponent._11_thread.handler.handler_thread.HandlerThreadActivity;
import com.lixiaozhuo.androidcomponent._11_thread.handler.handler_ui.HandlerUIActivity;

/**
 * 线程通信主函数
 */
public class HandlerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler);
    }

    /**
     *Handler1
     * @param v
     */
    public void handler1Test(View v){
        Intent intent = new Intent(HandlerActivity.this, Handler1Activity.class);
        startActivity(intent);
    }

    /**
     *Handler2
     * @param v
     */
    public void handler2Test(View v){
        Intent intent = new Intent(HandlerActivity.this, Handler2Activity.class);
        startActivity(intent);
    }

    /**
     *HandlerThread
     * @param v
     */
    public void handlerThreadTest(View v){
        Intent intent = new Intent(HandlerActivity.this, HandlerThreadActivity.class);
        startActivity(intent);
    }

    /**
     *Handler更新UI
     * @param v
     */
    public void handlerUITest(View v){
        Intent intent = new Intent(HandlerActivity.this, HandlerUIActivity.class);
        startActivity(intent);
    }

    /**
     *进程之间通信
     * @param v
     */
    public void communicationTest(View v){
        Intent intent = new Intent(HandlerActivity.this, CommunicationActivity.class);
        startActivity(intent);
    }

}
