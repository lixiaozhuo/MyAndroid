package com.lixiaozhuo.androidcomponent.thread;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lixiaozhuo.androidcomponent.R;
import com.lixiaozhuo.androidcomponent.thread.app.ThreadApplicationActivity1;
import com.lixiaozhuo.androidcomponent.thread.asynctask.AsyncTaskActivity;
import com.lixiaozhuo.androidcomponent.thread.communication.CommunicationActivity;
import com.lixiaozhuo.androidcomponent.thread.handler.HandlerActivity;

/**
 * 线程通信主函数
 */
public class ThreadActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread);
    }

    /**
     *Handler
     * @param v
     */
    public void handlerTest(View v){
        Intent intent = new Intent(ThreadActivity.this, HandlerActivity.class);
        startActivity(intent);
    }

    /**
     *AsyncTask
     * @param v
     */
    public void asyncTaskTest(View v){
        Intent intent = new Intent(ThreadActivity.this, AsyncTaskActivity.class);
        startActivity(intent);
    }

    /**
     *进程之间通信
     * @param v
     */
    public void communicationTest(View v){
        Intent intent = new Intent(ThreadActivity.this, CommunicationActivity.class);
        startActivity(intent);
    }

    /**
     *小应用
     * @param v
     */
    public void applicationTest(View v){
        Intent intent = new Intent(ThreadActivity.this,ThreadApplicationActivity1.class);
        startActivity(intent);
    }

}
