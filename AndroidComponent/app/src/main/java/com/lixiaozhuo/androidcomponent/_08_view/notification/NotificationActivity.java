package com.lixiaozhuo.androidcomponent._08_view.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Toast;

import com.lixiaozhuo.androidcomponent.R;

/**
 * 通知
 */
public class NotificationActivity extends Activity {
    /**
     * 普通通知
     */
    public static final int TYPE_Normal = 1;
    /**
     * 带下载的通知
     */
    public static final int TYPE_Progress = 2;
    /**
     * 大文本样式
     */
    public static final int TYPE_BigText = 3;
    /**
     * 多行文本样式
     */
    public static final int TYPE_Inbox = 4;
    /**
     * 大图片样式
     */
    public static final int TYPE_BigPicture = 5;
    /**
     * 横幅通知
     */
    public static final int TYPE_Hangup = 6;
    /**
     * 播放器样式
     */
    public static final int TYPE_Media = 7;
    /**
     * 自定义通知(播放服务)
     */
    public static final int TYPE_Customer = 8;
    /**
     * 渠道id
     */
    public static final String DEFAULT_CHANNEL_ID = "DEFAULT_CHANNEL_ID";
    /**
     * 通知管理器
     */
    private NotificationManager manger;
    /**
     * 下载服务
     */
    private Intent downloadService;
    /**
     * 播放器服务
     */
    private Intent mediaService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);
        //获取通知管理器
        manger = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //创建通知渠道
        createNotificationChannel();
        //初始化服务
        downloadService = new Intent(this, Notification_DownloadService.class);
        mediaService = new Intent(this,Notification_MediaService.class);
    }

    private void createNotificationChannel() {
        //设置通知渠道
        NotificationChannel channel = new NotificationChannel(DEFAULT_CHANNEL_ID,
                "普通通知", NotificationManager.IMPORTANCE_DEFAULT);
        manger.createNotificationChannel(channel);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                //普通通知
                simpleNotify();
                break;
            case R.id.btn2:
                //带下载的通知
                startService(downloadService);
                break;
            case R.id.btn3:
                //大文本样式
                bigTextStyle();
                break;
            case R.id.btn4:
                //多行文本样式
                inBoxStyle();
                break;
            case R.id.btn5:
                //大图片样式
                bigPictureStyle();
                break;
            case R.id.btn6:
                //横幅通知
                hangup();
                break;
            case R.id.btn7:
                //播放器样式
                mediaStyle();
                break;
            case R.id.btn8:
                //自定义通知(播放服务)
                startService(mediaService);
                break;
            default:
                break;
        }
    }

    /**
     * 普通通知
     */
    private void simpleNotify() {
        Notification.Builder builder = new Notification.Builder(this, DEFAULT_CHANNEL_ID);
        //通知小图标
        builder.setSmallIcon(R.mipmap.small);
        //通知大图标
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.large));
        //摘要
        builder.setSubText("普通样式摘要");
        //标题
        builder.setContentTitle("普通样式标题");
        //通知内容
        builder.setContentText("普通样式通知内容");
        //生成通知
        Notification notification = builder.build();
        //点击响应后自动取消显示
        builder.setAutoCancel(true);
        //向系统发送通知
        manger.notify(TYPE_Normal, notification);
    }

    /**
     * 大文本通知
     */
    private void bigTextStyle() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, DEFAULT_CHANNEL_ID);
        //通知小图标
        builder.setSmallIcon(R.mipmap.small);
        //通知大图标
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.large));
        //摘要
        builder.setSubText("大文本样式摘要");
        //标题
        builder.setContentTitle("大文本样式标题");
        //部分内容
        builder.setContentText("大文本样式部分内容");
        //设置通知为大文本样式
        NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle();
        //详细内容
        style.bigText("大文本样式详细内容，可以换行可以显示很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长");
        builder.setStyle(style);
        //点击响应后自动取消显示
        builder.setAutoCancel(true);
        //生成通知
        Notification notification = builder.build();
        //向系统发送通知
        manger.notify(TYPE_BigText, notification);
    }

    /**
     * 多行文本样式
     */
    public void inBoxStyle() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, DEFAULT_CHANNEL_ID);
        //通知小图标
        builder.setSmallIcon(R.mipmap.small);
        //通知大图标
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.large));
        //摘要
        builder.setSubText("多行文本样式摘要");
        //标题
        builder.setContentTitle("多行文本样式标题");
        //部分内容
        builder.setContentText("多行文本样式部分内容");
        //设置通知为多行文本样式
        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
        //详细内容
        style.addLine("多行文本样式详细内容，第一行，第一行，第一行")
                .addLine("第二行")
                .addLine("第三行")
                .addLine("第四行")
                .addLine("第五行");
        builder.setStyle(style);
        //生成通知
        Notification notification = builder.build();
        //向系统发送通知
        manger.notify(TYPE_Inbox, notification);
    }

    /**
     * 大图片样式
     */
    public void bigPictureStyle() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, DEFAULT_CHANNEL_ID);
        //通知小图标
        builder.setSmallIcon(R.mipmap.small);
        //通知大图标
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.large));
        //摘要
        builder.setSubText("大图片样式摘要");
        //标题
        builder.setContentTitle("大图片样式标题");
        //部分内容
        builder.setContentText("大图片样式部分内容");
        //设置通知为大图片样式
        NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle();
        //正文摘要
        style.setSummaryText("大图片样式正文摘要");
        //大图片
        style.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.cat));
        builder.setStyle(style);
        //点击响应后自动取消显示
        builder.setAutoCancel(true);
        //点击后跳转到图片活动
        Intent intent = new Intent(this, Notification_ImageActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 1, intent, 0);
        builder.setContentIntent(pIntent);
        //生成通知
        Notification notification = builder.build();
        //向系统发送通知
        manger.notify(TYPE_BigPicture, notification);
    }

    /**
     * 横幅通知
     */
    private void hangup() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Toast.makeText(NotificationActivity.this, "此类通知在Android 5.0以上版本才会有横幅有效！", Toast.LENGTH_SHORT).show();
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, DEFAULT_CHANNEL_ID);
        //通知小图标
        builder.setSmallIcon(R.mipmap.small);
        //通知大图标
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.large));
        //横幅标题
        builder.setContentTitle("横幅标题");
        //通知内容
        builder.setContentText("横幅通知内容");
        //点击后跳转到图片活动
        Intent intent = new Intent(this, Notification_ImageActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 1, intent, 0);
        builder.setContentIntent(pIntent);
        //设置横幅通知
        builder.setFullScreenIntent(pIntent, true);
        //点击响应后自动取消显示
        builder.setAutoCancel(true);
        //生成通知
        Notification notification = builder.build();
        //向系统发送通知
        manger.notify(TYPE_Hangup, notification);
    }

    /**
     * 播放器样式
     */
    private void mediaStyle() {
        Notification.Builder builder = new Notification.Builder(this, DEFAULT_CHANNEL_ID);
        //通知小图标
        builder.setSmallIcon(R.mipmap.small);
        //通知大图标
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.large));
        //标题
        builder.setContentTitle("播放器样式标题");
        //内容
        builder.setContentText("播放器样式内容");
        //跳转到图片活动
        Intent intent = new Intent(this,Notification_ImageActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this,1,intent,0);
        builder.setContentIntent(pIntent);
        //添加动作
        builder.addAction(R.drawable.ic_previous,"",null);
        builder.addAction(R.drawable.ic_stop,"",null);
        builder.addAction(R.drawable.ic_play,"",null);
        builder.addAction(R.drawable.ic_next,"",null);
        //设置通知为播放器样式
        Notification.MediaStyle style = new Notification.MediaStyle();
        //设置要显示在通知右方的图标 最多三个
        style.setShowActionsInCompactView(0, 1, 2);
        builder.setStyle(style);
        //不显示时间
        builder.setShowWhen(false);
        //生成通知
        Notification notification = builder.build();
        //向系统发送通知
        manger.notify(TYPE_Media, notification);
    }


    /**
     * 销毁下载服务
     */
    @Override
    protected void onDestroy() {
        //如果服务未停止则停止服务
        if(downloadService != null){
            stopService(downloadService);
        }
        if (mediaService != null) {
            stopService(mediaService);
        }
        super.onDestroy();
    }
}
