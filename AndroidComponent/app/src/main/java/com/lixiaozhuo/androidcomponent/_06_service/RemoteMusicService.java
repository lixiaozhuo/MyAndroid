package com.lixiaozhuo.androidcomponent._06_service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.RemoteException;

import com.lixiaozhuo.androidcomponent.R;

/**
 * 远程音乐服务
 */
public class RemoteMusicService extends Service {
    /**
     * 音乐播放
     */
    private MediaPlayer mediaPlayer;
    /**
     *实现对应AIDI中定义的控制方法
     */
    IServicePlayer.Stub  stub = new IServicePlayer.Stub() {
        /**
         * 播放
         * @throws RemoteException
         */
        @Override
        public void play() throws RemoteException {
            mediaPlayer.start();
        }
        /**
         *暂停
         * @throws RemoteException
         */
        @Override
        public void pause() throws RemoteException {
            mediaPlayer.pause();
        }

        /**
         * 停止
         * @throws RemoteException
         */
        @Override
        public void stop() throws RemoteException {
            mediaPlayer.stop();
        }

        /**
         *时长
         * @return
         * @throws RemoteException
         */
        @Override
        public int getDuration() throws RemoteException {
            return mediaPlayer.getDuration();
        }

        /**
         *当前位置
         * @return
         * @throws RemoteException
         */
        @Override
        public int getCurrentPosition() throws RemoteException {
            return mediaPlayer.getCurrentPosition();
        }

        /**
         * 是否循环播放
         * @param loop
         * @return
         * @throws RemoteException
         */
        @Override
        public boolean setLoop(boolean loop) throws RemoteException {
            mediaPlayer.setLooping(loop);
            return loop;
        }

        /**
         * 拖动位置
         * @param current
         * @throws RemoteException
         */
        @Override
        public void seekTo(int current) throws RemoteException {
            mediaPlayer.seekTo( current );
        }
    };



    @Override
    public void onCreate() {
        super.onCreate();
        //创建多媒体
        this.mediaPlayer = MediaPlayer.create( this, R.raw.chengdu );
    }

    @Override
    public IBinder onBind(Intent intent) {
        return this.stub;
    }
}
