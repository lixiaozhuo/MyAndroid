package com.lixiaozhuo.androidcomponent._11_thread.update_ui;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lixiaozhuo.androidcomponent.R;

/**
 * 在子线程通知主线程更新UI
 */
public class UpdateUIActivity extends Activity implements View.OnClickListener {
    //显示控件
    private TextView mTvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_ui);
        //获取控件并绑定事件
        initView();
    }
    //获取控件并绑定事件
    private void initView() {
        mTvTest = findViewById(R.id.tv_test);
        findViewById(R.id.btn_test1).setOnClickListener(this);
        findViewById(R.id.btn_test2).setOnClickListener(this);
        findViewById(R.id.btn_test3).setOnClickListener(this);
        findViewById(R.id.btn_test4).setOnClickListener(this);
        findViewById(R.id.btn_test5).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //子线程更新UI
            case R.id.btn_test1:
                new Thread(new Runnable() {//API 26,27 此程序子线程可以更新   API23,24崩溃
                    @Override
                    public void run() {
                        Log.e("App",Thread.currentThread().getName());
                        mTvTest.setText("子线程不可以更新UI(闪退)");
                    }
                }).start();
                break;
            //Handler发送消息
            case R.id.btn_test2:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = 100;
                        mHandler.sendMessage(message);
                    }
                }).start();
                break;
            //Handler.Post
            case R.id.btn_test3:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mTvTest.setText("handler.post可以更新UI");
                            }
                        });
                    }
                }).start();
                break;
            //View.Post
            case R.id.btn_test4:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mTvTest.post(new Runnable() {
                            @Override
                            public void run() {
                                mTvTest.setText("view.post可以更新UI");
                            }
                        });
                    }
                }).start();
                break;
            //Activity.RunOnUIThread
            case R.id.btn_test5:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mTvTest.setText("runOnUIThread可以更新UI");
                            }
                        });
                    }
                }).start();
                break;

            default:
                break;
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                mTvTest.setText("Handler发送消息可以更新UI");
            }
        }
    };

}
