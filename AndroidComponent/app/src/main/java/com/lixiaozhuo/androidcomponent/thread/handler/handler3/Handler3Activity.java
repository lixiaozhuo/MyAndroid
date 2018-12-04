package com.lixiaozhuo.androidcomponent.thread.handler.handler3;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lixiaozhuo.androidcomponent.R;

/**
 * Handler3
 */
public class Handler3Activity extends AppCompatActivity {
    private final static String TAG = "App:Handler";

    //线程
    private HandlerThread myHandlerThread ;
    //
    private Handler handler ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler3);
        //创建一个线程,线程名字：handler-thread
        myHandlerThread = new HandlerThread( "handler-thread") ;
        //开启一个线程
        myHandlerThread.start();
        //在这个线程中创建一个handler对象
        handler = new Handler( myHandlerThread.getLooper() ){
            //运行在 handler-thread 线程中的(可以执行耗时操作)
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.e( TAG , "消息： " + msg.what + "  线程： " + Thread.currentThread().getName()  ) ;

            }
        };
        //在主线程给handler发送消息
        handler.sendEmptyMessage( 1 ) ;

        new Thread(new Runnable() {
            @Override
            public void run() {
                //在子线程给handler发送消息
                handler.sendEmptyMessage( 2 ) ;
            }
        }).start() ;


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        myHandlerThread.quit() ;
    }
}
