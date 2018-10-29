package com.lixiaozhuo.notification;

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

/**
 * 下载服务
 */
public class DownloadService extends Service {
    /**
     * 适配器
     */
    private Handler mHandler;
    /**
     * 进度
     */
    private int progress = 0;
    /**
     * 通知管理器
     */
    private NotificationManager manger;
    /**
     *
     */
    private Runnable runnable;

    /**
     * 渠道id
     */
    private static final String CHANNEL_ID = "CHANNEL_ID";

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Toast.makeText(this,"aaa",Toast.LENGTH_LONG);
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mHandler = new Handler(getMainLooper());
        Toast.makeText(this,"aaa",Toast.LENGTH_LONG);
        manger = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        runnable = new Runnable() {
            @Override
            public void run() {
                if(progress>99){
                    progress=0;

                    manger.cancel(MainActivity.TYPE_Progress);
                }else{
                    sendNotification();
                    progress++;
                    mHandler.postDelayed(runnable,500);
                }
            }
        };
    }

    private void createDownloadNotificationChannel() {
        //设置通知渠道
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "下载通知", NotificationManager.IMPORTANCE_LOW);
        manger.createNotificationChannel(channel);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent==null){
            return super.onStartCommand(intent, flags, startId);
        }
        int command = intent.getIntExtra("command",0);
        if(command==1){
            progress=0;
            mHandler.removeCallbacks(runnable);
            manger.cancel(MainActivity.TYPE_Progress);
        }else {
            if (progress < 1) {
                mHandler.post(runnable);
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 发送通知
     */
    private void sendNotification(){
        createDownloadNotificationChannel();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID);
        //通知小图标
        builder.setSmallIcon(R.mipmap.small);
        //通知大图标
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.large));
        //禁止滑动删除
        builder.setOngoing(true);
        builder.setShowWhen(false);
        //通知栏标题
        builder.setContentTitle("下载中..."+progress+"%");
        //进度条
        builder.setProgress(100,progress,false);
        Intent intent = new Intent(this,DownloadService.class);
        intent.putExtra("command",1);
        Notification notification = builder.build();
        manger.notify(MainActivity.TYPE_Progress,notification);

    }

    /**
     *对象销毁
     */
    @Override
    public void onDestroy() {
        mHandler.removeCallbacks(runnable);
        manger.cancel(MainActivity.TYPE_Progress);
        super.onDestroy();
    }
}

