package com.lixiaozhuo.androidcomponent._01_application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lixiaozhuo.androidcomponent.R;

/**
 * 自定义应用测试活动1
 */
public class ApplicationActivity1 extends Activity {
    //显示框
    private TextView tv;
    //输入框
    private EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.application_test1);
        //获取控件并绑定事件
        tv =findViewById(R.id.tv);
        tv.setText("共享数据：" + getApp().getTextData());
        et = findViewById(R.id.et);
        //更新数据
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
        Intent intent = new Intent(ApplicationActivity1.this,ApplicationActivity2.class);
        startActivity(intent);
    }
    public CustomApplication getApp() {
        //获取这个应用程序的Context
        return ((CustomApplication)getApplicationContext());
    }
}
