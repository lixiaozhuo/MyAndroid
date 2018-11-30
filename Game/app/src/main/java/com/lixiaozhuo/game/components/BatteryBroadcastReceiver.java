package com.lixiaozhuo.game.components;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 电池广播接收器
 */
public class BatteryBroadcastReceiver extends BroadcastReceiver {

    private UpdateUIListener listener;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
            //获取当前电量
            int level = intent.getIntExtra("level", 0);
            //提示电量不足
            if (level < 15) {
                listener.UpdateUI("电量不足，请充电!");
            } else {
                listener.UpdateUI("");
            }
        }
    }

    public void SetUpdateUIListener(UpdateUIListener listener) {
        this.listener = listener;
    }
}
