package com.lixiaozhuo.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

//http://blog.csdn.net/zhi137_zhi148_qwer/article/details/53282238
public class MainActivity extends AppCompatActivity {
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
     * hangup横幅通知
     */
    public static final int TYPE_Hangup = 6;
    /**
     * 播放器样式
     */
    public static final int TYPE_Media = 7;
    /**
     * 自定义通知
     */
    public static final int TYPE_Customer = 8;
    /**
     * 通知管理器
     */
    private NotificationManager manger;
    /**
     * 渠道id
     */
    private static final String DEFAULT_CHANNEL_ID = "DEFAULT_CHANNEL_ID";

    /**
     * 服务
     */
    private Intent service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取通知管理器
        manger = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //创建通知渠道
        createNotificationChannel();
        //初始化下载服务
        service = new Intent(this, DownloadService.class);
    }

    private void createNotificationChannel() {
        //设置通知渠道
        NotificationChannel channel = new NotificationChannel(DEFAULT_CHANNEL_ID, "普通通知", NotificationManager.IMPORTANCE_DEFAULT);
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
                startService(service);
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
                //hangup横幅通知
                hangup();
                break;
            case R.id.btn7:
                //播放器样式
                mediaStyle();
                break;
            case R.id.btn8:
                //自定义通知
                Intent intent = new Intent(this,MediaService.class);
                startService(intent);
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
        //内容摘要
        builder.setSubText("自定义普通内容摘要");
        //通知栏标题
        builder.setContentTitle("自定义普通标题");
        //通知正文
        builder.setContentText("自定义普通通知内容");
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
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.large));
        builder.setSubText("自定义大文本内容摘要");
        //无效,显示自定义点击后大文本标题
        //builder.setContentTitle("自定义大文本标题");
        builder.setContentText("自定义大文本通知内容");
        //通知样式
        android.support.v4.app.NotificationCompat.BigTextStyle style = new android.support.v4.app.NotificationCompat.BigTextStyle();
        style.setBigContentTitle("自定义点击后大文本标题");
        style.bigText("自定义点击后显示的正文，可以换行可以显示很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长");
        //无效
        //style.setSummaryText("自定义点击后未知功能");
        builder.setStyle(style);
        //点击响应后自动取消显示
        builder.setAutoCancel(true);
        Notification notification = builder.build();
        manger.notify(TYPE_BigText, notification);
    }

    /**
     * 多行文本样式
     */
    public void inBoxStyle() {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, DEFAULT_CHANNEL_ID);
        //通知小图标
        builder.setSmallIcon(R.mipmap.small);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.large));
        builder.setSubText("自定义多行文本内容摘要");
        builder.setContentText("自定义多行文本通知内容");
        android.support.v4.app.NotificationCompat.InboxStyle style = new android.support.v4.app.NotificationCompat.InboxStyle();
        style.setBigContentTitle("BigContentTitle")
                .addLine("第一行，第一行，第一行，第一行，第一行，第一行，第一行")
                .addLine("第二行")
                .addLine("第三行")
                .addLine("第四行")
                .addLine("第五行");
        builder.setStyle(style);
        Notification notification = builder.build();
        manger.notify(TYPE_Inbox, notification);
    }

    /**
     * 大图片样式
     */
    public void bigPictureStyle() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, DEFAULT_CHANNEL_ID);
        builder.setSmallIcon(R.mipmap.small);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.large));
        builder.setSubText("自定义大图片内容摘要");
        builder.setContentText("自定义大图片通知内容");
        android.support.v4.app.NotificationCompat.BigPictureStyle style = new android.support.v4.app.NotificationCompat.BigPictureStyle();
        style.setBigContentTitle("点击后大文本标题");
        style.setSummaryText("SummaryText");
        style.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.cat));
        builder.setStyle(style);
        //点击响应后自动取消显示
        builder.setAutoCancel(true);

        Intent intent = new Intent(this, ImageActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 1, intent, 0);
        builder.setContentIntent(pIntent);

        Notification notification = builder.build();
        manger.notify(TYPE_BigPicture, notification);
    }

    /**
     * 横幅通知
     */
    private void hangup() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Toast.makeText(MainActivity.this, "此类通知在Android 5.0以上版本才会有横幅有效！", Toast.LENGTH_SHORT).show();
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, DEFAULT_CHANNEL_ID);
        builder.setSmallIcon(R.mipmap.small);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.large));
        builder.setContentTitle("自定义横幅标题");
        builder.setContentText("自定义横幅通知内容");

        Intent intent = new Intent(this, ImageActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 1, intent, 0);
        builder.setContentIntent(pIntent);
        builder.setFullScreenIntent(pIntent, true);
        //点击响应后自动取消显示
        builder.setAutoCancel(true);
        Notification notification = builder.build();
        manger.notify(TYPE_Hangup, notification);
    }

    /**
     * 播放器样式
     */
    private void mediaStyle() {
        Notification.Builder builder = new Notification.Builder(this, DEFAULT_CHANNEL_ID);
        builder.setSmallIcon(R.mipmap.small);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.large));
        builder.setContentTitle("自定义播放器标题");
        builder.setContentText("自定义播放器通知内容");

        Intent intent = new Intent(this,ImageActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this,1,intent,0);
        builder.setContentIntent(pIntent);
        builder.addAction(R.drawable.ic_previous_white,"",null);
        builder.addAction(R.drawable.ic_stop_white,"",null);
        builder.addAction(R.drawable.ic_play_arrow_white_18dp,"",pIntent);
        builder.addAction(R.drawable.ic_next_white,"",null);
        Notification.MediaStyle style = new Notification.MediaStyle();
        //设置要现实在通知右方的图标 最多三个
        style.setShowActionsInCompactView(0, 1, 2);
        builder.setStyle(style);
        //不展示时间
        builder.setShowWhen(false);
        Notification notification = builder.build();
        manger.notify(TYPE_Media, notification);
    }


    /**
     * 销毁服务
     */
    @Override
    protected void onDestroy() {
        if (service != null) {
            stopService(service);
        }
        super.onDestroy();
    }
}
