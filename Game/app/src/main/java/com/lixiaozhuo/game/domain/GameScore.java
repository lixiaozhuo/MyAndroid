package com.lixiaozhuo.game.domain;

import com.lixiaozhuo.game.activity.GameMenu;

import java.io.Serializable;

/**
 *游戏得分
 */
public class GameScore implements Serializable{
    //级别
    private int level;
    //时间(ms单位)
    private long score;
    //时间(字符串)
    private String date;


    public GameScore(int level, long score, String date) {
        this.level = level;
        this.score = score;
        this.date = date;
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
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
