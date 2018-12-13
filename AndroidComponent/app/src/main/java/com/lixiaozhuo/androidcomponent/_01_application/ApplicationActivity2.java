package com.lixiaozhuo.androidcomponent._01_application;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lixiaozhuo.androidcomponent.R;

/**
 * 自定义应用测试活动2
 */
public class ApplicationActivity2 extends Activity {
    //显示框
    private TextView tv;
    //输入框
    private EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.application_test2);
        //获取控件并绑定事件
        tv = findViewById(R.id.tv);
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
    public CustomApplication getApp() {
        //获取这个应用程序的Context
        return ((CustomApplication)getApplicationContext());
    }
}
