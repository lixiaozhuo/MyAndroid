package com.lixiaozhuo.game.service;

import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lixiaozhuo.game.R;
import com.lixiaozhuo.game.domain.GameMen;
import com.lixiaozhuo.game.domain.GamePedal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 游戏布局业务
 */
public class LayoutService {
    //布局
    private RelativeLayout layout;

    public LayoutService(RelativeLayout layout) {
        this.layout = layout;
    }

    /**
     * 向页面添加游戏人物
     */
    public void addMen(GameMen gameMen) {
        layout.addView(gameMen.getMenImage());
    }

    /**
     * 从页面中删除人物
     */
    public void removeMen(GameMen gameMen) {
        if (gameMen != null) {
            layout.removeView(gameMen.getMenImage());
        }
    }

    /**
     * 向页面中添加踏板
     */
    public void addPedal(GamePedal gamePedal) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(gamePedal.getLength(), 50);
        layout.addView(gamePedal.getPedalImage(), layoutParams);
    }

    /**
     * 从页面中删除踏板
     */
    public void removePedal(GamePedal gamePedal) {
        if (gamePedal != null) {
            layout.removeView(gamePedal.getPedalImage());
        }
    }

    /**
     * 更新记录
     */
    public void clearTime() {
        String score = new SimpleDateFormat("mm" + "分" + "ss" + "秒").format(new Date(0));
        ((TextView) layout.findViewById(R.id.textCount)).setText(score);
    }

    /**
     * 更新记录
     *
     * @param Time
     */
    public void updateTime(Long Time) {
        String score = new SimpleDateFormat("mm" + "分" + "ss" + "秒").format(new Date(Time));
        ((TextView) layout.findViewById(R.id.textCount)).setText(score);
    }


}
