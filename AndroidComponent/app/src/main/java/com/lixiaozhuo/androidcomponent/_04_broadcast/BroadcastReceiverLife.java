package com.lixiaozhuo.androidcomponent._04_broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 广播接收器
 */
public class BroadcastReceiverLife extends BroadcastReceiver {
    private final static String TAG = "App:BroadcastReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        String Intent_Action = intent.getAction();
        if("com.lixiaozhuo.androidcomponent.broadcast.BroadcastLife".equals(Intent_Action)){
            Log.e(TAG,"onReceive");
        }
    }
}
