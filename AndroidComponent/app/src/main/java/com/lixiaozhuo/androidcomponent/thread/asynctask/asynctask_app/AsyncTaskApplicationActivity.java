package com.lixiaozhuo.androidcomponent.thread.asynctask.asynctask_app;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lixiaozhuo.androidcomponent.R;

//在子线程通知主线程更新UI
public class AsyncTaskApplicationActivity extends Activity implements View.OnClickListener {
    //显示控件
    private TextView mTvTest;
    //子线程更新UI测试
    private Button mBtnTest1;
    //Handler发送消息
    private Button mBtnTest2;
    //Handler.Post
    private Button mBtnTest3;
    //View.Post
    private Button mBtnTest4;
    //Activity.RunOnUIThread
    private Button mBtnTest5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asynctask_application);
        //获取控件并绑定事件
        initView();
    }
    //获取控件并绑定事件
    private void initView() {
        mTvTest =  findViewById(R.id.tv_test);
        mBtnTest1 = findViewById(R.id.btn_test1);
        mBtnTest2 = findViewById(R.id.btn_test2);
        mBtnTest3 = findViewById(R.id.btn_test3);
        mBtnTest4 = findViewById(R.id.btn_test4);
        mBtnTest5 = findViewById(R.id.btn_test5);
        mBtnTest1.setOnClickListener(this);
        mBtnTest2.setOnClickListener(this);
        mBtnTest2.setOnClickListener(this);
        mBtnTest3.setOnClickListener(this);
        mBtnTest4.setOnClickListener(this);
        mBtnTest5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //子线程更新UI测试
            case R.id.btn_test1:
                new Thread(new Runnable() {//API 26,27 模拟器，此程序子线程能更新。。。API23,24崩溃。。。
                    @Override
                    public void run() {
                        mTvTest.setText("子线程可以更新UI");
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
                                mTvTest.setText("handler.post");
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
                                mTvTest.setText("view.post");
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
                                mTvTest.setText("runOnUIThread");
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
                mTvTest.setText("由Handler发送消息");
            }
        }
    };

}
