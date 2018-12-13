package com.lixiaozhuo.androidcomponent._11_thread.handler.handler1;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lixiaozhuo.androidcomponent.R;

/**
 * Handler1
 */
public class Handler1Activity extends Activity {
    private final static String TAG1 = "App:Handler-调用主线程handler";
    private final static String TAG2 = "App:Handler-调用子线程MyHandler";
    //显示控件
    private  TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler1);
        //获取显示控件
        textView =findViewById(R.id.text) ;
    }
    //Handler在主线程,直接实例化。主线程启动时，已经有Looper和MessageQueue对象。
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message message){
            switch (message.what){
                case 1:
                    Log.e(TAG1,message.toString());
                    textView.setText("直接使用主线程Handler\n更新UI");
                    break;
                case 2:
                    Log.e(TAG1,message.toString());
                    textView.setText("子线程MyHandler发送消息\n调用主线程Handler\n更新UI");
                    break;
                default:
                    break;
            }
            super.handleMessage(message);
        }
    };

    /**
     * Activity直接使用Handler
     * @param view
     */
    public void button1_onClick(View view){
        Message message = handler.obtainMessage();
        message.arg1= 0x01;
        message.arg2 = 0x02;
        message.obj = "objectType";
        message.what = 1;
        //发送到消息队列
        handler.sendMessage(message);
    }

    /**
     * 其他Thread使用Handler
     * @param view
     */
    public void button2_onClick(View view){
        new MyThread().start();
    }

    class MyThread extends Thread{
        //子线程Handler
        private Handler MyHandler;
        @Override
        public void run(){
            //获取Looper和MessageQueue
            Looper.prepare();
            //Handler实例化
            MyHandler = new Handler(){
                //接收消息
                @Override
                public void handleMessage(Message message){
                    super.handleMessage(message);
                    if(message.what == 100)
                        //子线程通过修改主线程的Handler更新UI
                        handler.sendEmptyMessage(2);
                        Log.e(TAG2,message.toString());
                }
            };
            //发送到消息队列
            MyHandler.sendEmptyMessage(100);
            //死循环获得消息队列变化，并分发消息，回调HandleMessage（）
            Looper.loop();
        }

    }

}
