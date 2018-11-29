package com.lixiaozhuo.game.service;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.lixiaozhuo.game.R;
import com.lixiaozhuo.game.domain.GamePedal;

import java.util.Random;

/**
 * 踏板服务
 */
public class GamePedalService {
    private Context context;

    public GamePedalService(Context context) {
        this.context = context;
    }

    /**
     * 添加踏板
     */
    public  GamePedal createPedal(int speed) {
        //屏幕高和宽
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        int screenHeight = context.getResources().getDisplayMetrics().heightPixels;
        //获取踏板原始宽度
        BitmapFactory.Options options = new BitmapFactory.Options();
        BitmapFactory.decodeResource(context.getResources(), R.mipmap.pedal, options);
        int pedalWidth = options.outWidth;
        //随记生成踏板
        int length;
        int x;
        int y = screenHeight;
        while (true) {
            //踏板长度为1/2-1倍之间
            length = new Random().nextInt(pedalWidth - 3 * (pedalWidth / 4)) + 3 * (pedalWidth / 4);
            //获取随机横坐标
            x = new Random().nextInt(screenWidth - 50) + 50;
            //符合条件
            if (x + length < screenWidth) {
                break;
            }
        }
        GamePedal pedal = new GamePedal(length, x, y, speed, context);
        return pedal;
    }

    /**
     * 移动踏板
     */
    public void movePedal(GamePedal pedal) {
        pedal.setY(pedal.getY() - pedal.getSpeed());
    }

    /**
     * 踏板是否出界
     *
     * @param pedal
     * @return
     */
    public boolean isPedalDead(GamePedal pedal) {
        //踏板出界
        if (pedal.getY() <= 0) {
            return true;
        }
        return false;
    }
}
