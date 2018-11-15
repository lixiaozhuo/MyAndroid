package com.lixiaozhuo.androidcomponent.thread;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lixiaozhuo.androidcomponent.R;
import com.lixiaozhuo.androidcomponent.view.login.LoginActivity;
import com.lixiaozhuo.androidcomponent.view.menu.MenuActivity;

/**
 * 线程通信主函数
 */
public class MyThread extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread);
    }

    /**
     *
     * @param v
     */
    public void myTest(View v){
        Intent intent = new Intent(MyThread.this, MyThread.class);
        startActivity(intent);
    }

}
