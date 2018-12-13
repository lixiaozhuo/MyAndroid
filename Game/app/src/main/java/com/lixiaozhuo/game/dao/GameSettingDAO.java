package com.lixiaozhuo.game.dao;

import android.content.Context;
import android.content.SharedPreferences;

import com.lixiaozhuo.game.MyApplication;
import com.lixiaozhuo.game.R;
import com.lixiaozhuo.game.domain.GameSetting;

import static android.content.Context.MODE_PRIVATE;

/**
 * 游戏设置数据DAO
 */
public class GameSettingDAO {
    //上下文
    private Context context;

    public GameSettingDAO() {
        this.context = MyApplication.getContext();
    }

    /**
     * 保存设置
     *
     * @param gameSetting 游戏设置
     */
    public void saveSetting(GameSetting gameSetting) {
        //将人物编号和级别编号保存到SharedPreferences
        //创建名为“SettingData”的文件，加入键值对，保存数据
        SharedPreferences.Editor editor = context.getSharedPreferences("SettingData", MODE_PRIVATE).edit();
        editor.putString("menNO", String.valueOf(gameSetting.getMenNO()));
        editor.putString("levelNO", String.valueOf(gameSetting.getLevelNO()));
        //提交数据
        editor.apply();
    }

    public GameSetting getSetting() {
        //取出人物编号和级别编号
        SharedPreferences preferences = context.getSharedPreferences("SettingData", MODE_PRIVATE);
        //将数据保存到对象中
        GameSetting gameSetting = new GameSetting();
        gameSetting.setMenNO(Integer.valueOf(preferences.getString("menNO", String.valueOf(R.id.radioMen1))));
        gameSetting.setLevelNO(Integer.valueOf(preferences.getString("levelNO", String.valueOf(R.id.radioLevel1))));
        return gameSetting;
    }


}
