package com.lixiaozhuo.androidcomponent.broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lixiaozhuo.androidcomponent.R;

//广播
public class BroadcastActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcast);
        //广播生命周期
        broadcastLife();
        //电池信息广播实例
        batteryBroadcast();
    }
    ////////////////////////////////////////////////////////////////////////////////////
    /**
     * 广播生命周期测试
     */
    private void broadcastLife() {
        //获取控件并绑定事件
        Button sendBroadcast = super.findViewById(R.id.sendBroadcast);
        sendBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Intent_Action = "com.lixiaozhuo.androidcomponent.broadcast.BroadcastReceiverLife.BroadcastLife";
                Intent intent = new Intent(Intent_Action);
                //指定广播接受者的包名(发送显式广播)
                intent.setPackage(getPackageName());
                sendBroadcast(intent);
                Log.e("BroadcastReceiver","sendBroadcast");
            }
        });
    }
    ////////////////////////////////////////////////////////////////////////////////
    /**
     * 电池状态接收器
     */
    private BatteryReceiver batteryReceiver;
    /**
     * 显示电量
     */
    private TextView showBattery;
    /**
     * 电池信息广播实例
     */
    private void batteryBroadcast() {
        //获取控件
        showBattery = findViewById(R.id.showBattery);
        // 注册广播接收者java代码
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        batteryReceiver = new BatteryReceiver();
        // 注册r广播接收器
        registerReceiver(batteryReceiver, intentFilter);
    }
    /**
     * 电池状态接收器
     */
    public class BatteryReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
                //获取当前电量
                int level = intent.getIntExtra("level", 0);
                //显示电量信息
                showBattery.setText("电池电量为" + level + "%");
                if (level < 15) {
                    showBattery.setText("电池电量为" + level + "%" +"\n"+"电池电量不足15%，请及时充电!");
                }
                if (level == 100) {
                    showBattery.setText("电池电量为" + level + "%" +"\n"+"电池已充满！");
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(batteryReceiver);
    }

}
