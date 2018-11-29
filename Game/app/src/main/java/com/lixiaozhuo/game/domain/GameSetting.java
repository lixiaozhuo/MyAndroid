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
    //移动速度
    private int moveSpeed;
    //踏板添加间隔
    private int addPedalInterval;


    public GameSetting(){
        this.menNO =R.id.radioMen1;
        this.levelNO = R.id.radioLevel1;
        //更新踏板移动速度和踏板添加时间
        updateData();
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
        updateData();
    }

    public int getPedalMoveSpeed() {
        return moveSpeed;
    }

    public int getMenMoveSpeed(){
        return moveSpeed * 2;
    }

    public int getAddPedalInterval() {
        return addPedalInterval;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //更新移动速度和踏板添加间隔
    private void updateData(){
        switch (levelNO){
            case R.id.radioLevel1:
                moveSpeed =10;
                addPedalInterval =60;
                break;
            case R.id.radioLevel2:
                moveSpeed =14;
                addPedalInterval =50;
                break;
            case R.id.radioLevel3:
                moveSpeed =16;
                addPedalInterval =40;
                break;
            case R.id.radioLevel4:
                moveSpeed =18;
                addPedalInterval =30;
                break;
        }
    }
}
