package com.lixiaozhuo.androidcomponent.thread.handler.handler_app;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lixiaozhuo.androidcomponent.R;

/**
 * 线程修改UI测试
 */
public class HandlerApplicationActivity extends Activity implements View.OnClickListener {
    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler_application);
        //获取控件并绑定时间
        textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button1);
        Button button1 = findViewById(R.id.button2);
        button.setOnClickListener(this);
        button1.setOnClickListener(this);
        //
        new Thread(new Runnable() {
            @Override
            public void run() {
                textView.setText("子线程可以更新UI");
            }
        }).start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //直接在子线程修改文字
            case R.id.button1:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("你好");
                    }
                }).start();
                break;
            //通过Handler修改文字
            case R.id.button2:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = 100;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            default:
                break;
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                textView.setText("由Handler发送消息");
            }
        }
    };
}
