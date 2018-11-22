package com.lixiaozhuo.androidcomponent.thread.handler.handler1;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lixiaozhuo.androidcomponent.R;

/**
 * Handler1
 */
public class Handler1Activity extends Activity {
    //显示控件
    private TextView show;
    //
    private Handler handler;

    @Override
    //主线程创建时便自动创建Looper和对应的MessageQueue,之前执行Loop()进入消息循环
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler1);
        //获取显示控件
        show = findViewById(R.id.show);
        StringBuffer text = new StringBuffer();
        text.append("First：主线程启动");
        show.setText(text);
        //实例化Handler(无指定Looper,即自动绑定当前线程(主线程)的Looper和MessageQueue)
        handler = new Handler();
        //启动子线程
        new Thread_1().start();
        new Thread_2().start();

    }


    class Thread_1 extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    String line = "\n";
                    StringBuffer text = new StringBuffer(show.getText());
                    text.append(line).append("Second：五秒后，第一个子线程启动");
                    show.setText(text);
                }
            });
        }
    }

    class Thread_2 extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    String line = "\n";
                    StringBuffer text = new StringBuffer(show.getText());
                    text.append(line).append("Third：十秒后，第二个子线程启动");
                    show.setText(text);
                }

            });
        }
    }
}

