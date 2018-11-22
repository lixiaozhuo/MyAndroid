package com.lixiaozhuo.androidcomponent.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * 服务生命周期测试
 */
public class MyService extends Service {
    public MyService() {
    }
    // 服务创建的时候使用
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("AndroidApplication","onCreate");
    }
    //////////////////////////////////bind//////////////////////////////////////////////////////////////////////////
    //下载Binder
    private DownloadBinder mBinder = new DownloadBinder();
    //下载Binder
    class DownloadBinder extends Binder {
        public void startDownload() {
            Log.d("MyService", "开始下载！");
        }
        public int getProgress() {
            Log.e("AndroidApplication", "获取进度！");
            return 0;
        }
    }
    //服务绑定时调用
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("AndroidApplication", "onBind");
        return mBinder;
    }
    //服务解绑时调用
    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("AndroidApplication", "onUnbind");
        return super.onUnbind(intent);
    }
    //服务再次绑定时调用
    @Override
    public void onRebind(Intent intent) {
        Log.e("AndroidApplication", "onRebind");
        super.onRebind(intent);
    }
    //////////////////////////////////start//////////////////////////////////////////////////////////////////////////
    // 服务启动的时候调用
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("AndroidApplication","onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 服务销毁的时候调用
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("AndroidApplication","onDestroy");
    }
}
