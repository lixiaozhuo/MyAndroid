package com.lixiaozhuo.androidcomponent.view.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.lixiaozhuo.androidcomponent.MainActivity;
import com.lixiaozhuo.androidcomponent.R;

/**
 * 下载服务
 */
public class Notification_DownloadService extends Service {
    /**
     * 适配器
     */
    private Handler mHandler;
    /**
     * 通知管理器
     */
    private NotificationManager manger;
    /**
     *线程
     */
    private Runnable runnable;

    /**
     * 进度
     */
    private int progress = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        //新建适配器
        mHandler = new Handler(getMainLooper());
        //获取通知管理器
        manger = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //下载线程
        runnable = new Runnable() {
            @Override
            public void run() {
                if(progress>99){
                    //进度归0,下载完成
                    progress=0;
                    //取消通知
                    manger.cancel(NotificationActivity.TYPE_Progress);
                }else{
                    //发送通知
                    sendNotification();
                    //进度增加
                    progress++;
                    //延迟500ms
                    mHandler.postDelayed(runnable,500);
                }
            }
        };
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent==null){
            return super.onStartCommand(intent, flags, startId);
        }
        int command = intent.getIntExtra("command",0);
        if(command==1){
            //进度归0
            progress=0;
            //停止线程
            mHandler.removeCallbacks(runnable);
            //取消通知
            manger.cancel(NotificationActivity.TYPE_Progress);
        }else {
            //开始下载
            if (progress < 1) {
                //开始线程
                mHandler.post(runnable);
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 发送通知
     */
    private void sendNotification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,NotificationActivity.DEFAULT_CHANNEL_ID);
        //通知小图标
        builder.setSmallIcon(R.mipmap.small);
        //通知大图标
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.large));
        //禁止滑动删除
        builder.setOngoing(true);
        //不显示时间
        builder.setShowWhen(false);
        //通知栏标题
        builder.setContentTitle("下载中..."+progress+"%");
        //进度条
        builder.setProgress(100,progress,false);
        //生成通知
        Notification notification = builder.build();
        //向系统发送通知
        manger.notify(NotificationActivity.TYPE_Progress,notification);
    }


    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    /**
     *对象销毁
     */
    @Override
    public void onDestroy() {
        //停止线程
        mHandler.removeCallbacks(runnable);
        //取消通知
        manger.cancel(NotificationActivity.TYPE_Progress);
        super.onDestroy();
    }
}

