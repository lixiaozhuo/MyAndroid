package com.lixiaozhuo.androidcomponent.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.lixiaozhuo.androidcomponent.R;

/**
 * 服务
 */
public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service);
        //服务生命周期测试初始化
        serviceLifeInit();
        //本地服务初始化
        nativeServiceInit();
        //远程服务初始化
        remoteServiceInit();
    }


    ///////////////////////服务生命周期测试//////////////////////////////////////////////////////////////
    //初始化
    private void serviceLifeInit(){
        //获取控件并绑定事件
        Button startService =findViewById(R.id.startService);
        Button stopService = findViewById(R.id.stopService);
        Button bindService =findViewById(R.id.bindService);
        Button unBindService = findViewById(R.id.unbindService);
        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        bindService.setOnClickListener(this);
        unBindService.setOnClickListener(this);
    }
    /**
     * 服务连接
     */
    private ServiceConnection serviceConnection = new ServiceConnection() {
        //活动与服务连接断开时调用
        @Override
        public void onServiceDisconnected(ComponentName arg0) {
        }
        //活动与服务成功绑定时调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //下载Binder
            MyService.DownloadBinder downloadBinder = (MyService.DownloadBinder) service;
            //开始下载
            downloadBinder.startDownload();
            //获取进度
            downloadBinder.getProgress();
        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startService:
                //开启服务
                Intent startIntent = new Intent(ServiceActivity.this, MyService.class);
                startService(startIntent);
                break;
            case R.id.stopService:
                //暂停服务
                Intent stopIntent = new Intent(ServiceActivity.this, MyService.class);
                stopService(stopIntent);
                break;
            case R.id.bindService:
                //绑定服务
                Intent bindIntent = new Intent(ServiceActivity.this, MyService.class);
                bindService(bindIntent, serviceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.unbindService:
                //解绑服务
                unbindService(serviceConnection);
                break;
            default:
                break;
        }
    }
    ///////////////////////本地服务//////////////////////////////////////////////////////////////
    //本地服务初始化
    private void nativeServiceInit() {
        //获取控件并绑定事件
        Button playButton = super.findViewById( R.id.playNativeService );
        Button stopButton = super.findViewById( R.id.stopNativeService );
        playButton.setOnClickListener( nativeServiceListener );
        stopButton.setOnClickListener( nativeServiceListener );
    }
    private View.OnClickListener nativeServiceListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch ( v.getId() ) {
                case R.id.playNativeService:
                    //本地服务:播放
                    Intent playIntent = new Intent(ServiceActivity.this, NativeMusicService.class);
                    startService(playIntent);
                    break;
                case R.id.stopNativeService:
                    //本地服务:暂停
                    Intent stopIntent = new Intent(ServiceActivity.this, NativeMusicService.class);
                    stopService(stopIntent);
                    break;
                default:
                    break;
            }
        }
    };
    ///////////////////////远程服务//////////////////////////////////////////////////////////////
    /**
     * 远程音乐服务进度条
     */
    private SeekBar seekBarRemoteService;
    /**
     * 远程音乐服务播放按钮
     */
    private Button playRemoteService;
    /**
     * 标志是否正在播放
     */
    private boolean isPlaying = false;
    /**
     * 音乐服务
     */
    IServicePlayer iServicePlayer;
    /**
     * 处理器
     */
    private Handler handler = new Handler();
    /**
     * 服务连接
     */
    private ServiceConnection remoteServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //获取音乐服务
            iServicePlayer = IServicePlayer.Stub.asInterface( service );
        }
    };
    //远程服务初始化
    private void remoteServiceInit() {
        //绑定远程音乐服务
        bindService(new Intent(this, RemoteMusicService.class), remoteServiceConnection, Context.BIND_AUTO_CREATE);
        //开始音乐服务
        startService(new Intent(this, RemoteMusicService.class));
        //获取控件并绑定事件
        this.seekBarRemoteService = ( SeekBar )super.findViewById( R.id.seekBarRemoteService );
        this.playRemoteService = ( Button )super.findViewById( R.id.playRemoteService );
        seekBarRemoteService.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //进度条停止拖动时回调
                if ( iServicePlayer != null ) {
                    try {
                        //根据进度条调整音乐进度
                        iServicePlayer.seekTo( seekBar.getProgress() );
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        this.playRemoteService.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( !isPlaying ) {
                    try {
                        //开始播放
                        iServicePlayer.play();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    //设置暂停标志
                    playRemoteService.setText( "Pause" );
                    //标记当前状态为正在播放
                    isPlaying = true;
                }
                else {
                    try {
                        //暂停播放
                        iServicePlayer.pause();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    //设置播放标志
                    playRemoteService.setText( "Play" );
                    //标记当前状态未停止播放
                    isPlaying = false;
                }
            }
        });
        //开启进度条更新线程
        handler.post( updateSeekBarThread );
    }

    /**
     * 进度条更新线程
     */
    private Runnable updateSeekBarThread = new Runnable() {
        @Override
        public void run() {
            if ( iServicePlayer != null ) {
                try {
                    //设置进度条最大值为音乐时长
                    seekBarRemoteService.setMax( iServicePlayer.getDuration() );
                    //更新进度条进度
                    seekBarRemoteService.setProgress( iServicePlayer.getCurrentPosition() );
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            handler.post( updateSeekBarThread );
        }
    };

}
