package com.lixiaozhuo.androidcomponent.thread.handler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lixiaozhuo.androidcomponent.R;
import com.lixiaozhuo.androidcomponent.thread.handler.handler1.Handler1Activity;
import com.lixiaozhuo.androidcomponent.thread.handler.handler2.Handler2Activity;
import com.lixiaozhuo.androidcomponent.thread.handler.handler3.Handler3Activity;
import com.lixiaozhuo.androidcomponent.thread.handler.handler_app.HandlerApplicationActivity;

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
     *Handler3
     * @param v
     */
    public void handler3Test(View v){
        Intent intent = new Intent(HandlerActivity.this, Handler3Activity.class);
        startActivity(intent);
    }

    /**
     *Handler应用
     * @param v
     */
    public void handlerApplicationTest(View v){
        Intent intent = new Intent(HandlerActivity.this, HandlerApplicationActivity.class);
        startActivity(intent);
    }

}
