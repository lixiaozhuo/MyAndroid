package com.lixiaozhuo.game.service;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.lixiaozhuo.game.R;
import com.lixiaozhuo.game.domain.GameMen;
import com.lixiaozhuo.game.domain.GamePedal;

/**
 * 游戏人物业务
 */
public class GameMenService {
    private Context context;

    public GameMenService(Context context) {
        this.context = context;
    }

    public GameMen createMen(int menNO, int x, int speed) {
        //屏幕高度
        int screenHeight = context.getResources().getDisplayMetrics().heightPixels;
        //初始化人物
        GameMen gameMen = new GameMen(menNO,
                x, screenHeight / 4,
                speed, context);
        return gameMen;
    }

    public void moveMen(GameMen gameMen,int x){
        //屏幕宽度
        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
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
     * @param gameMen
     */
    public void moveMenDown(GameMen gameMen) {
        //人物在下落,更新人物位置
        gameMen.setY(gameMen.getY() + gameMen.getSpeed());
    }

    /**
     * 向上移动人物
     *
     * @param gameMen
     * @param pedal
     */
    public void moveMenUp(GameMen gameMen, GamePedal pedal) {
        //人物在踏板上,更新人物位置
        gameMen.setY(pedal.getY() - gameMen.getHeight());
    }

    /**
     * 判断人物是否死亡
     */
    public boolean isMenDead(GameMen gameMen) {
        int screenHeight = context.getResources().getDisplayMetrics().heightPixels;
        //人物死亡
        if (!(gameMen.getY() < screenHeight && gameMen.getY() > 10)) {
            return true;
        }
        return false;
    }

    /**
     * 判断人物是否掉落
     */
    public boolean isMenDrop(GameMen gameMen ,GamePedal pedal) {
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
            if ((pedal.getY() - gameMen.getSpeed()) <= menY && menY <= (pedalY + gameMen.getSpeed())) {
                //当前踏板
                return false;
            }
        }
        return true;
    }
}
