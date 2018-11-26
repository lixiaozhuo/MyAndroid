package com.lixiaozhuo.game.domain;


import android.content.Context;
import android.util.Log;

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
public class GameRecord{
    //应用上下文
    private Context context;
    //游戏记录数据
    private static Map<Integer, GameScore> data = new HashMap<>();

    public GameRecord(Context context) {
        this.context = context;
        //加载游戏记录数据
        try {
            //获取输入流
            ObjectInputStream ois = new ObjectInputStream(context.openFileInput("RecordScore"));
            //从文件中读取游戏记录数据
            data = (Map<Integer, GameScore>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取记录
     *
     * @return
     */
    public List<GameScore> getData() {
        //将记录数据转换为list格式
        List<GameScore> list = new ArrayList<>();
        for (int id : data.keySet()) {
            list.add(data.get(id));
        }
        //对数据进行排序
        list.sort(new Comparator<GameScore>() {
            @Override
            public int compare(GameScore o1, GameScore o2) {
                return o1.getLevelName().compareTo(o2.getLevelName());
            }
        });
        return list;
    }

    /**
     * 更新记录
     *
     * @param gameScore
     */
    public void updateData(GameScore gameScore) {
        //获取当前记录
        GameScore currentGameScore = data.get(gameScore.getLevel());
        //当前无记录或者记录被刷新
        if (currentGameScore == null || currentGameScore.getScore() < gameScore.getScore()) {
            //更新记录
            data.put(gameScore.getLevel(), gameScore);
        }
        //保存更改
        saveData();
    }

    /**
     * 清空记录
     */
    public void clearData() {
        //清除数据
        data.clear();
        //保存更改
        saveData();
    }

    /**
     * 将数据保存到文件中
     */
    private void saveData() {
        try {
            //获取输出流
            ObjectOutputStream oos = new ObjectOutputStream(context.openFileOutput("RecordScore", Context.MODE_PRIVATE));
            //将数据写入文件
            oos.writeObject(data);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}