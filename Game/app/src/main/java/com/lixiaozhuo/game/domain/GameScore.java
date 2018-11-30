package com.lixiaozhuo.game.domain;

import com.lixiaozhuo.game.R;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * 游戏得分
 */
public class GameScore implements Serializable {
    //级别
    private int levelNO;
    //时间
    private long time;

    public GameScore(int levelNO, long time) {
        this.levelNO = levelNO;
        this.time = time;
    }

    public int getLevelNO() {
        return levelNO;
    }

    public String getLevelName() {
        String name;
        switch (levelNO) {
            case R.id.radioLevel1:
                name = "级别1";
                break;
            case R.id.radioLevel2:
                name = "级别2";
                break;
            case R.id.radioLevel3:
                name = "级别3";
                break;
            case R.id.radioLevel4:
                name = "级别4";
                break;
            default:
                name = "级别1";
        }
        return name;
    }

    public long getTime() {
        return time;
    }

    public String getScore() {
        return new SimpleDateFormat("mm" + "分" + "ss" + "秒", Locale.getDefault()).format(time);
    }
}
