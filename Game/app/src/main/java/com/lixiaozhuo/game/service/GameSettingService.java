package com.lixiaozhuo.game.service;

import android.content.Context;

import com.lixiaozhuo.game.dao.GameSettingDAO;
import com.lixiaozhuo.game.domain.GameSetting;

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
     *
     * @param gameSetting 设置内容
     */
    public void saveSetting(GameSetting gameSetting) {
        gameSettingDAO.saveSetting(gameSetting);
    }

    /**
     * 获取设置
     *
     * @return 设置内容
     */
    public GameSetting getSetting() {
        return gameSettingDAO.getSetting();
    }
}
