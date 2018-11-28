package com.lixiaozhuo.game.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import com.lixiaozhuo.game.R;
/**
 * 本地音乐服务
 */
public class MusicService extends Service {
    public MusicService() {
    }

    /**
     * 音乐播放
     */
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        this.mediaPlayer = MediaPlayer.create( this, R.raw.game_music);
        //设置音乐循环播放
        mediaPlayer.setLooping(true);
    }

    @Override
    public IBinder onBind(Intent intent) {
        //开始播放
        this.mediaPlayer.start();
        return null;
    }


    @Override
    public void onDestroy() {
        //停止播放
        this.mediaPlayer.stop();
        super.onDestroy();
    }
}
