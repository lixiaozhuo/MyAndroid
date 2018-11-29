package com.lixiaozhuo.game.service;

import android.content.Context;
import android.content.SharedPreferences;

import com.lixiaozhuo.game.R;
import com.lixiaozhuo.game.dao.GameRecordSQLiteOpenHelper;
import com.lixiaozhuo.game.dao.GameSettingDAO;
import com.lixiaozhuo.game.domain.GameSetting;

import static android.content.Context.MODE_PRIVATE;

/**
 * 游戏设置业务
 */
public class GameSettingService {
    //游戏设置DAO
    private GameSettingDAO gameSettingDAO;

    public GameSettingService(Context context) {
        gameSettingDAO = new GameSettingDAO(context);
    }

    /**
     * 保存设置
     * @param gameSetting
     */
    public void saveSetting(GameSetting gameSetting){
        gameSettingDAO.saveSetting(gameSetting);
    }

    /**
     * 获取设置
     * @return
     */
    public GameSetting getSetting(){
        return gameSettingDAO.getSetting();
    }
}
