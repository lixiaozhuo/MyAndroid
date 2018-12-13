package com.lixiaozhuo.androidcomponent._11_thread;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lixiaozhuo.androidcomponent.R;
import com.lixiaozhuo.androidcomponent._01_application.ApplicationActivity1;
import com.lixiaozhuo.androidcomponent._11_thread.asynctask.AsyncTaskActivity;
import com.lixiaozhuo.androidcomponent._11_thread.handler.communication.CommunicationActivity;
import com.lixiaozhuo.androidcomponent._11_thread.handler.HandlerActivity;
import com.lixiaozhuo.androidcomponent._11_thread.update_ui.UpdateUIActivity;

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
     *子线程更新UI
     * @param v
     */
    public void handlerUITest(View v){
        Intent intent = new Intent(ThreadActivity.this, UpdateUIActivity.class);
        startActivity(intent);
    }



}
