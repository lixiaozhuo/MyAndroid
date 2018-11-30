package com.lixiaozhuo.game.view;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lixiaozhuo.game.R;
import com.lixiaozhuo.game.components.BatteryBroadcastReceiver;
import com.lixiaozhuo.game.components.UpdateUIListener;
import com.lixiaozhuo.game.service.MusicService;
import com.lixiaozhuo.game.thread.PlayThread;

/**
 * 开始游戏
 */
public class GamePlayActivity extends Activity {
    //开始游戏线程
    private PlayThread playThread;
    //音乐业务
    private MusicService musicService;
    //电池广播接收器
    private BatteryBroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.game_play);
        //音乐业务
        musicService = new MusicService(this);
        //初始化电池广播接收器
        broadcastReceiver = new BatteryBroadcastReceiver();
        //注册电池广播接收器
        registerReceiver(broadcastReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        broadcastReceiver.SetUpdateUIListener(new UpdateUIListener() {
            @Override
            public void UpdateUI(String str) {
                //更新ui
                ((TextView) findViewById(R.id.showBattery)).setText(str);
            }
        });

        //初始化游戏线程
        playThread = new PlayThread(this, (RelativeLayout) findViewById(R.id.playGame));
        //开始游戏
        playThread.start();

        //暂停按钮
        findViewById(R.id.SuspendGame).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //按键声音
                musicService.playKeyMusic();
                //暂停游戏
                playThread.pauseGame();
            }
        });
    }

    /**
     * 返回键
     */
    @Override
    public void onBackPressed() {
        //暂停游戏
        playThread.pauseGame();
    }


    //存储手势起始位置
    private float x;

    /**
     * 手势操作
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //当手指按下的时候
            x = event.getX();
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            //拖动后位置
            float x2 = event.getX();
            if (x - x2 > 50) {
                //向左移动
                playThread.moveGameMen(-10);
            } else if (x2 - x > 50) {
                //向右移动
                playThread.moveGameMen(10);
            }
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        //注销广播
        unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }
}