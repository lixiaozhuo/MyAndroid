package com.lixiaozhuo.androidcomponent.thread.asynctask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lixiaozhuo.androidcomponent.R;
import com.lixiaozhuo.androidcomponent.thread.asynctask.asynctask1.AsyncTask1Activity;
import com.lixiaozhuo.androidcomponent.thread.asynctask.asynctask2.AsyncTask2Activity;
import com.lixiaozhuo.androidcomponent.thread.asynctask.asynctask_app.AsyncTaskApplicationActivity;

/**
 * 线程通信主函数
 */
public class AsyncTaskActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asynctask);
    }

    /**
     *AsyncTask1
     * @param v
     */
    public void asyncTask1Test(View v){
        Intent intent = new Intent(AsyncTaskActivity.this, AsyncTask1Activity.class);
        startActivity(intent);
    }

    /**
     *AsyncTask2
     * @param v
     */
    public void asyncTask2Test(View v){
        Intent intent = new Intent(AsyncTaskActivity.this, AsyncTask2Activity.class);
        startActivity(intent);
    }

    /**
     *AsyncTask应用
     * @param v
     */
    public void asyncTaskApplicationTest(View v){
        Intent intent = new Intent(AsyncTaskActivity.this, AsyncTaskApplicationActivity.class);
        startActivity(intent);
    }

}
