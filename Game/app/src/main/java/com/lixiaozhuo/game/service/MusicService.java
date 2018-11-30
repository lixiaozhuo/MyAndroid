package com.lixiaozhuo.game.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.IBinder;

import com.lixiaozhuo.game.R;
import com.lixiaozhuo.game.components.NativeMusicService;

/**
 * 音乐播放业务
 */
public class MusicService {
    //上下文
    private Context context;

    //音乐池
    private final static SoundPool soundPool;
    //歌曲id
    private  static int songID;

    static{
        //音乐池初始化
        soundPool =new SoundPool.Builder()
                .setMaxStreams(1)
                .setAudioAttributes(new AudioAttributes.Builder()
                        .setLegacyStreamType(AudioManager.STREAM_MUSIC)
                        .build())
                .build();
    }

    public  MusicService(Context context){
        this.context = context;
        songID= soundPool.load(context, R.raw.key_music,1);
    }

    //音乐服务连接
    private ServiceConnection musicServiceConnection = new ServiceConnection() {
        //活动与服务连接断开时调用
        @Override
        public void onServiceDisconnected(ComponentName arg0) {

        }
        //活动与服务成功绑定时调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
        }

    };

    /**
     * 开始游戏音乐
     */
    public void startGameMusic(){
        //开启音乐服务
        context.bindService(new Intent(context, NativeMusicService.class), musicServiceConnection, Context.BIND_AUTO_CREATE);
    }

    /**
     * 结束游戏音乐
     */
    public void stopGameMusic(){
        //结束音乐服务
        context.unbindService(musicServiceConnection);

    }

    /**
     * 播放点击音乐
     */
    public void playKeyMusic(){
        soundPool.play(songID,1,1,0,0,1);
    }

}
