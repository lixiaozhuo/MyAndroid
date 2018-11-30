package com.lixiaozhuo.game.service;

import android.content.Context;

import com.lixiaozhuo.game.dao.GameRecordSQLiteOpenHelper;
import com.lixiaozhuo.game.domain.GameScore;

import java.util.List;

/**
 * 游戏记录业务
 */
public class GameRecordService {
    //数据库帮助器
    private GameRecordSQLiteOpenHelper dbHelper;

    public GameRecordService(Context context) {
        //初始化数据库帮助器
        dbHelper = new GameRecordSQLiteOpenHelper(context);
    }

    /**
     * 获取记录
     *
     * @return 游戏记录
     */
    public List<GameScore> getRecord() {
        return dbHelper.onList();
    }

    /**
     * 更新记录
     *
     * @param gameScore 游戏记录
     */
    public void updateRecord(GameScore gameScore) {
        dbHelper.onUpdate(gameScore);
    }

    /**
     * 清空记录
     */
    public void clearRecord() {
        dbHelper.onClear();
    }
}
