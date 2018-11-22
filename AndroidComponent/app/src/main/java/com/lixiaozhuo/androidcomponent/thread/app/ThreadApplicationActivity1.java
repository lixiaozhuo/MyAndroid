package com.lixiaozhuo.androidcomponent.thread.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lixiaozhuo.androidcomponent.R;

/**
 * 线程应用主活动
 */
public class ThreadApplicationActivity1 extends Activity {
    //标志
    private String TAG = "AndroidApplication";
    //显示框
    private TextView tv;
    //输入框
    private EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread_application1);
        //获取控件并绑定事件
        tv =findViewById(R.id.tv);
        tv.setText("共享数据：" + getApp().getTextData());
        et = findViewById(R.id.et);
        findViewById(R.id.btnTextData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getApp().setTextData(et.getText().toString());
                tv.setText("共享数据：" + et.getText().toString());
            }
        });
    }
    public void openActivity2(View view){
        //打开活动2
        Intent intent = new Intent(ThreadApplicationActivity1.this,ThreadApplicationActivity2.class);
        startActivity(intent);
    }
    public ThreadCustomApp getApp() {
        //获取这个应用程序的Context
        return ((ThreadCustomApp)getApplicationContext());
    }
}
