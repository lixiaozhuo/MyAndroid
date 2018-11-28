package com.lixiaozhuo.game.domain;

import com.lixiaozhuo.game.activity.GameMenu;

import java.io.Serializable;
import java.text.SimpleDateFormat;

/**
 *游戏得分
 */
public class GameScore implements Serializable{
    //级别
    private int level;
    //时间(ms单位)
    private long score;

    public GameScore(int level, long score) {
        this.level = level;
        this.score = score;
    }
    /**
     * 获取级别名称
     * @return
     */
    public String getLevelName() {
        return GameMenu.gameLevelMap.get(level);
    }
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public String getDate() {
        return new SimpleDateFormat("mm" + "分" + "ss" + "秒").format(score);
    }
}
