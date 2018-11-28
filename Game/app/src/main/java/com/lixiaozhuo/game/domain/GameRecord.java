package com.lixiaozhuo.game.domain;


import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.lixiaozhuo.game.dao.GameRecordSQLiteOpenHelper;
import com.lixiaozhuo.game.domain.GameScore;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 游戏记录
 */
public class GameRecord {
    //应用上下文
    private Context context;
    //数据库帮助器
    private GameRecordSQLiteOpenHelper dbHelper;

    public GameRecord(Context context) {
        this.context = context;
        //初始化数据库帮助器
        dbHelper = new GameRecordSQLiteOpenHelper(context);
    }

    /**
     * 获取记录
     *
     * @return
     */
    public List<GameScore> getRecord() {
        return dbHelper.onList(dbHelper.getReadableDatabase());
    }

    /**
     * 更新记录
     *
     * @param gameScore
     */
    public void updateRecord(GameScore gameScore) {
        dbHelper.onUpdate(dbHelper.getWritableDatabase(),gameScore);
    }

    /**
     * 清空记录
     */
    public void clearRecord() {
        dbHelper.onClear(dbHelper.getWritableDatabase());
    }
}