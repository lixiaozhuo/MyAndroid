package com.lixiaozhuo.game.domain;

import com.lixiaozhuo.game.R;

/**
 * 游戏设置
 */
public class GameSetting {
    //人物编号
    private  int menNO;
    //级别编号
    private  int levelNO;
    //踏板移动间隔
    private int movePedalTime;
    //踏板添加时间
    private int addPedalInterval;


    public GameSetting(int menNO,int levelNO){
        this.menNO =menNO;
        this.levelNO = levelNO;
        //更新踏板移动速度和踏板添加时间
        updateTime();
    }

    public GameSetting(){
        this(R.id.radioMen1, R.id.radioLevel1);
    }

    public int getMenNO() {
        return menNO;
    }

    public void setMenNO(int menNO) {
        this.menNO = menNO;
    }

    public int getLevelNO() {
        return levelNO;
    }

    public void setLevelNO(int levelNO) {
        this.levelNO = levelNO;
        //更新踏板移动速度和踏板添加时间
        updateTime();
    }

    public int getMovePedalTime() {
        return movePedalTime;
    }

    public int getAddPedalInterval() {
        return addPedalInterval;
    }

    //更新踏板移动速度和踏板添加时间
    private void updateTime(){
        switch (levelNO){
            case R.id.radioLevel1:
                movePedalTime =6;
                addPedalInterval =100;
                break;
            case R.id.radioLevel2:
                movePedalTime =12;
                addPedalInterval =60;
                break;
            case R.id.radioLevel3:
                movePedalTime =18;
                addPedalInterval =50;
                break;
            case R.id.radioLevel4:
                movePedalTime =24;
                addPedalInterval =40;
                break;
        }
    }
}
