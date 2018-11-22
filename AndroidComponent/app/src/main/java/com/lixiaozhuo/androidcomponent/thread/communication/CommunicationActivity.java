package com.lixiaozhuo.androidcomponent.thread.communication;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lixiaozhuo.androidcomponent.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 线程间通信
 */
public class CommunicationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.communication);
    }
    //处理器
    private Handler childHandler;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            Log.e("AndroidApplication", msg.obj+"，收到消息线程："+Thread.currentThread().getName());
            Log.e("AndroidApplication", "============================================================");
            super.handleMessage(msg);
        }
    };

    /**
     * 启动子线程1
     * @param view
     */
    public void Button1_OnClick(View view){
        new Thread1().start();
    }

    public class Thread1 extends Thread{
        @Override
        public void run() {
            Log.e("AndroidApplication", "启动进程："+Thread.currentThread().getName());
            //创建消息队列
            Looper.prepare();
            childHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    Log.e("AndroidApplication", msg.obj+"，收到消息线程："+Thread.currentThread().getName());
                    Log.e("AndroidApplication", "============================================================");
                }
            };
            //开启循环消息队列
            Looper.loop();
        }
    }

    /**
     *主线程-->子线程1发消息
     * @param view
     */
    public void Button0To1_OnClick(View view){
        Message msg = Message.obtain();
        msg.what = 1;
        //日期格式化工具
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年-MM月dd日-HH时mm分ss秒");
        Date date = new Date(System.currentTimeMillis());
        msg.obj = formatter.format(date)+"";
        childHandler.sendMessage(msg);
        Log.e("AndroidApplication", msg.obj+"，发送消息线程："+Thread.currentThread().getName());
    }

    /**
     *子进程2-->子进程1发消息
     * @param view
     */
    public void Button2To1_OnClick(View view){
        new Thread2().start();
    }
    public class Thread2 extends Thread{
        @Override
        public void run() {
            Message msg = Message.obtain();
            msg.what = 1;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy年-MM月dd日-HH时mm分ss秒");
            Date date = new Date(System.currentTimeMillis());
            msg.obj = formatter.format(date)+"";
            childHandler.sendMessage(msg);
            Log.i("AndroidApplication", msg.obj+"，发送消息线程："+Thread.currentThread().getName());
        }
    }


    /**
     *子进程3-->主进程发消息
     * @param view
     */
    public void Button3To0_OnClick(View view){
        new Thread3().start();
    }
    public class Thread3 extends Thread{
        @Override
        public void run() {
            Message msg = Message.obtain();
            msg.what = 1;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy年-MM月dd日-HH时mm分ss秒");
            Date date = new Date(System.currentTimeMillis());
            msg.obj = formatter.format(date)+"";
            handler.sendMessage(msg);
            Log.i("AndroidApplication", msg.obj+"，发送消息线程："+Thread.currentThread().getName());
        }
    }




}
