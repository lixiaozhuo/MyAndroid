package com.lixiaozhuo.game.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lixiaozhuo.game.R;
import com.lixiaozhuo.game.thread.PedalThread;

/**
 * 开始游戏
 */
public class PlayGame extends Activity {
    //踏板线程
    private PedalThread pedalThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.play_game);
        //注册电池广播接收器
        registBroadcastReceiver();
        //初始化跳板线程
        pedalThread = new PedalThread(this, (RelativeLayout) findViewById(R.id.playGame), getResources().getDisplayMetrics());
        //开始游戏
        pedalThread.start();
        //暂停按钮
        ImageButton pauseGame = findViewById(R.id.SuspendGame);
        pauseGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //按键声音
                GameMenu.soundPool.play(GameMenu.songID, 1, 1, 0, 0, 1);
                //暂停游戏
                PlayGame.this.pauseGame();
            }
        });
    }

    //存储手势起始位置
    private float x;

    /**
     * 手势操作
     *
     * @param event
     * @return
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
                pedalThread.moveGameMen(-10);
            } else if (x2 - x > 50) {
                //向右移动
                pedalThread.moveGameMen(10);
            }
        }
        return true;
    }

    /**
     * 返回键
     */
    @Override
    public void onBackPressed() {
        //暂停游戏
        this.pauseGame();
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 暂停游戏
     */
    private void pauseGame() {
        //暂停游戏
        pedalThread.pauseGame();
        new AlertDialog.Builder(this).setTitle("暂停").setMessage("暂停了游戏")
                .setPositiveButton("返回主菜单", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //结束游戏
                        pedalThread.stopGame();
                    }
                })
                .setNegativeButton("继续游戏", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //继续游戏
                        pedalThread.continueGame();
                    }
                }).setCancelable(false).show();
    }


    //////////////////////////////////////广播接收器///////////////////////////////////////////////////////////
    //注册广播接收器
    private  void registBroadcastReceiver(){
        // 注册电池状态接收器
        registerReceiver(new BatteryReceiver(), new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
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
                //提示电量不足
                if (level < 15) {
                    ((TextView)findViewById(R.id.showBattery)).setText("电量不足，请充电!");
                }else{
                    ((TextView)findViewById(R.id.showBattery)).setText("");
                }
            }
        }
    }
}
