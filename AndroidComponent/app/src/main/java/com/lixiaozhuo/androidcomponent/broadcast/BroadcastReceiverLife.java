package com.lixiaozhuo.androidcomponent.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

//广播接收器
public class BroadcastReceiverLife extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String Intent_Action = intent.getAction();
        if("com.lixiaozhuo.androidcomponent.broadcast.BroadcastLife".equals(Intent_Action)){
            Log.e("AndroidApplication","onReceive");
        }
    }
}
