package com.lixiaozhuo.androidcomponent._11_thread.handler.handler_ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.lixiaozhuo.androidcomponent.R;

/**
 * 线程修改UI测试
 */
public class HandlerUIActivity extends Activity implements View.OnClickListener {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler_ui);
        //获取控件并绑定事件
        initView();
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                textView.setText("子线程可以更新UI(特殊)");// onCreate()子线程可以更改UI(特殊)
            }
        }).start();*/
    }

    private void initView() {
        textView = findViewById(R.id.textView);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
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

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 100) {
                textView.setText("由Handler发送消息");
            }
            return false;
        }
    });
}
