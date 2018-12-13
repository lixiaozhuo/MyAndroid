package com.lixiaozhuo.androidcomponent._06_service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.lixiaozhuo.androidcomponent.R;

/**
 * 本地音乐服务
 */
public class NativeMusicService extends Service {
    public NativeMusicService() {
    }

    /**
     * 音乐播放
     */
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mediaPlayer = MediaPlayer.create( this, R.raw.sound);
        //开始播放
        this.mediaPlayer.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //停止播放
        this.mediaPlayer.stop();
    }
}
