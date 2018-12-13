package com.lixiaozhuo.game.service;

import android.content.Context;

import com.lixiaozhuo.game.MyApplication;
import com.lixiaozhuo.game.domain.GameMen;
import com.lixiaozhuo.game.domain.GamePedal;

/**
 * 游戏人物业务
 */
public class GameMenService {
    //屏幕高度
    private int screenHeight;
    //屏幕宽度
    private int screenWidth;

    public GameMenService() {
        Context context = MyApplication.getContext();
        screenHeight = context.getResources().getDisplayMetrics().heightPixels;
        screenWidth = context.getResources().getDisplayMetrics().widthPixels;
    }

    public GameMen createMen(int menNO, int x, int speed) {
        //返回游戏人物
        return new GameMen(menNO,
                x, screenHeight / 4,
                speed);
    }

    public void moveMen(GameMen gameMen, int x) {
        //移动后位置
        int newMenX = gameMen.getX() + x;
        //移动后人物未超出界面
        if ((newMenX > 0) && (newMenX < (screenWidth - gameMen.getMenImage().getWidth()))) {
            gameMen.setX(newMenX);
        }
    }

    /**
     * 向下移动人物
     *
     * @param gameMen 游戏人物
     */
    public void moveMenDown(GameMen gameMen) {
        //人物在下落,更新人物位置
        gameMen.setY(gameMen.getY() + gameMen.getSpeed());
    }

    /**
     * 向上移动人物
     *
     * @param gameMen 游戏人物
     * @param pedal   踏板
     */
    public void moveMenUp(GameMen gameMen, GamePedal pedal) {
        //人物在踏板上,更新人物位置
        gameMen.setY(pedal.getY() - gameMen.getHeight());
    }

    /**
     * 判断人物是否死亡
     */
    public boolean isMenDead(GameMen gameMen) {
        //人物是否死亡
        return gameMen.getY() >= screenHeight || gameMen.getY() <= 10;
    }

    /**
     * 判断人物是否掉落
     */
    public boolean isMenDrop(GameMen gameMen, GamePedal pedal) {
        //人物位置
        int menX = gameMen.getX() + gameMen.getWidth() / 2;
        int menY = gameMen.getY() + gameMen.getHeight();
        //人物处于此踏板水平位置上
        if (menX >= pedal.getX() && menX <= (pedal.getX() + pedal.getLength())) {
            int pedalY = pedal.getY();
            //条件1
            //人物位置<=踏板位置<=人物位置 + 人物速度
            // (踏板位置-人物速度<=人物位置<=踏板位置)
            //条件2
            //人物位置<=踏板位置+踏板速度<=人物位置+人物速度
            // (踏板位置+踏板速度-人物速度<=人物位置<=踏板位置+踏板速度)
            //综合
            //踏板位置-人物速度<=人物位置<=踏板位置+踏板速度
            if ((pedalY - gameMen.getSpeed()) <= menY && menY <= (pedalY + gameMen.getSpeed())) {
                //当前踏板
                return false;
            }
        }
        return true;
    }
}
