package com.lixiaozhuo.game.domain;

import android.service.autofill.FillEventHistory;

import com.lixiaozhuo.game.common.GameState;

import java.util.ArrayList;
import java.util.List;

/**
 * 游戏类
 */
public class Game {
    //游戏设置
    private GameSetting gameSetting;
    //游戏状态
    private volatile GameState gameState;
    //计时
    private long time;
    //踏板集合
    private List<GamePedal> pedalList = new ArrayList<>();
    //游戏人物
    private GameMen gameMen;
    //当前踏板
    private GamePedal currentPedal;
    //移动计数(移动一定次数后添加踏板)
    private int moveCount;


    public Game(GameSetting gameSetting) {
        this.gameSetting = gameSetting;
    }

    public GameSetting getGameSetting() {
        return gameSetting;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public List<GamePedal> getPedalList() {
        return pedalList;
    }

    public GameMen getGameMen() {
        return gameMen;
    }

    public void setGameMen(GameMen gameMen) {
        this.gameMen = gameMen;
    }

    public GamePedal getCurrentPedal() {
        return currentPedal;
    }

    public void setCurrentPedal(GamePedal currentPedal) {
        this.currentPedal = currentPedal;
    }


    public int getMoveCount() {
        return moveCount;
    }

    public void clearMoveCount() {
        this.moveCount = 0;
    }

    public  void  addMoveCount(){
        this.moveCount ++;
    }

}
