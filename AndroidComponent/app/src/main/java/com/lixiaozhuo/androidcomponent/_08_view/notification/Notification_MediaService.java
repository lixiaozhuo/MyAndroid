package com.lixiaozhuo.androidcomponent._08_view.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.RemoteViews;

import com.lixiaozhuo.androidcomponent.R;

/**
 * 播放器服务
 */
public class Notification_MediaService extends Service {
    /**
     * 通知管理器
     */
    private  NotificationManager manger;
    /**
     * 播放器服务
     */
    private  MediaPlayer mediaPlayer;

    /**
     * 播放暂停命令
     */
    public static final int CommandPlay =1;
    /**
     * 下一首命令
     */
    public static final int CommandNext =2;
    /**
     * 关闭命令
     */
    public static final int CommandClose =3;
    /**
     * 停止状态
     */
    public static final int StatusStop = 0;
    /**
     * 播放状态
     */
    public static final int StatusPlay = 1;
    /**
     * 当前播放器状态
     */
    private int playerStatus = 0;
    /**
     * 歌曲
     */
    private int[] songs = {R.raw.song1,R.raw.song2};
    /**
     * 当前播放歌曲索引
     */
    private static int index = 0;


    @Override
    public void onCreate() {
        //获取通知管理器
        manger = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent==null){
            return super.onStartCommand(intent, flags, startId);
        }
        int command = intent.getIntExtra("command",0);
        //命令默认为播放暂停
        if(command==0 && mediaPlayer==null){
            command = CommandPlay;
        }
        //下一首命令
        if(command ==CommandNext){
            //歌曲索引指向下一首歌曲
            index = (index+1)%2;
        }
        //关闭命令
        if(command==CommandClose){
            //更改播放器状态为停止状态
            playerStatus = StatusStop;
            //取消通知
            manger.cancel(NotificationActivity.TYPE_Customer);
        }else {
            //发送通知
            sendCustomerNotification(command);
        }
        //设置播放器
        setMediaPlayer(command, playerStatus, index);
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 发送通知
     * @param command
     */
    private void sendCustomerNotification(int command){
        Notification.Builder builder = new Notification.Builder(this,NotificationActivity.DEFAULT_CHANNEL_ID);
        //通知小图标
        builder.setSmallIcon(R.mipmap.small);
        //通知大图标
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.large));
        //标题
        builder.setContentTitle("Notification");
        //内容
        builder.setContentText("自定义");
        //点击响应后自动取消显示
        builder.setAutoCancel(false);
        //设置不可取消
        builder.setOngoing(true);
        //不显示时间
        builder.setShowWhen(false);
        //远程视图
        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.notification_media);
        remoteViews.setTextViewText(R.id.title,"音乐播放器");
        remoteViews.setTextViewText(R.id.text,"歌曲"+index);
        //下一首命令
        if(command==CommandNext){
            remoteViews.setImageViewResource(R.id.btn1,R.drawable.ic_pause);
        }//播放暂停命令
        else if(command==CommandPlay){
            //当前状态为暂停
            if(playerStatus==StatusStop){
                //图标显示为暂停
                remoteViews.setImageViewResource(R.id.btn1,R.drawable.ic_pause);
            }else{
                //图标显示为播放
                remoteViews.setImageViewResource(R.id.btn1,R.drawable.ic_play);
            }
        }
        //点击后播放
        Intent Intent1 = new Intent(this,Notification_MediaService.class);
        Intent1.putExtra("command",CommandPlay);
        PendingIntent PIntent1 =  PendingIntent.getService(this,5,Intent1,0);
        remoteViews.setOnClickPendingIntent(R.id.btn1,PIntent1);
        //点击后播放下一首
        Intent Intent2 = new Intent(this,Notification_MediaService.class);
        Intent2.putExtra("command",CommandNext);
        PendingIntent PIntent2 =  PendingIntent.getService(this,6,Intent2,0);
        remoteViews.setOnClickPendingIntent(R.id.btn2,PIntent2);
        //点击后关闭
        Intent Intent3 = new Intent(this,Notification_MediaService.class);
        Intent3.putExtra("command",CommandClose);
        PendingIntent PIntent3 =  PendingIntent.getService(this,7,Intent3,0);
        remoteViews.setOnClickPendingIntent(R.id.btn3,PIntent3);
        builder.setContent(remoteViews);
        //生成通知
        Notification notification = builder.build();
        //向系统发送通知
        manger.notify(NotificationActivity.TYPE_Customer,notification);
    }

    private void setMediaPlayer(int command,int status,int currentIndex){
        switch (command){
            //播放暂停命令
            case CommandPlay:
                //当前状态为暂停
                if(status==StatusStop){
                    if(mediaPlayer==null){
                        mediaPlayer = MediaPlayer.create(this,songs[index%2]);
                    }
                    //开始播放
                    mediaPlayer.start();
                    //更改当前状态为播放
                    playerStatus = StatusPlay;
                }//当前状态为播放
                else {
                    //暂停播放
                    mediaPlayer.pause();
                    //更改当前状态为暂停
                    playerStatus = StatusStop;
                }
                break;
            //下一首命令
            case CommandNext:
                if(mediaPlayer!=null){
                    if(mediaPlayer.isPlaying()){
                        //停止播放
                        mediaPlayer.stop();
                    }
                    //释放播放器服务
                    mediaPlayer.reset();
                }
                //播放下一首歌曲
                mediaPlayer = MediaPlayer.create(this,songs[index%2]);
                //更改当前状态为播放
                playerStatus = StatusPlay;
                //开始播放
                mediaPlayer.start();
                break;
            //停止命令
            case CommandClose:
                //更改当前状态为停止状态
                playerStatus = StatusStop;
                //停止播放
                mediaPlayer.stop();
                //释放音频播放器
                mediaPlayer.release();
                mediaPlayer = null;
                break;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        //释放音频播放器
        if(mediaPlayer!=null){
            mediaPlayer.reset();
            mediaPlayer.release();
        }
        super.onDestroy();
    }
}
