package com.lixiaozhuo.game.service;

import android.widget.RelativeLayout;

import com.lixiaozhuo.game.common.GameState;
import com.lixiaozhuo.game.domain.Game;
import com.lixiaozhuo.game.domain.GameMen;
import com.lixiaozhuo.game.domain.GamePedal;

import java.util.Iterator;

/**
 * 游戏业务
 */
public class GameService {
    //游戏人物业务
    private GameMenService gameMenService;
    //踏板业务
    private GamePedalService gamePedalService;
    //界面布局业务
    private GameLayoutService layoutService;

    public GameService(RelativeLayout layout) {
        gameMenService = new GameMenService();
        gamePedalService = new GamePedalService();
        layoutService = new GameLayoutService(layout);
    }

    /**
     * 初始化游戏
     *
     * @param game 游戏内容
     */
    public void initGame(Game game) {
        //清空计时
        game.setTime(0);
        //清空计时显示
        layoutService.clearTime();
        //修改游戏状态为运行态
        game.setGameState(GameState.RUNNING);
        //清除界面中的人物和踏板
        layoutService.removeMen(game.getGameMen());
        for (GamePedal pedal : game.getPedalList()) {
            layoutService.removePedal(pedal);
        }
        //清空踏板数据
        game.getPedalList().clear();
        //初始化跳板
        GamePedal gamePedal = gamePedalService.createPedal(game.getGameSetting().getPedalMoveSpeed());
        //初始化人物
        GameMen gameMen = gameMenService.createMen(
                game.getGameSetting().getMenNO(),
                gamePedal.getX() + (gamePedal.getLength() / 2),
                game.getGameSetting().getMenMoveSpeed());
        //在界面中添加人物和踏板
        layoutService.addMen(gameMen);
        layoutService.addPedal(gamePedal);
        //将踏板添加到数据中
        game.getPedalList().add(gamePedal);
        //设置游戏人物和当前踏板
        game.setGameMen(gameMen);
        game.setCurrentPedal(gamePedal);
        //清空移动计数
        game.clearMoveCount();
    }

    /**
     * 添加踏板
     *
     * @param game 游戏内容
     */
    public void addPedal(Game game) {
        //初始化踏板
        GamePedal gamePedal = gamePedalService.createPedal(game.getGameSetting().getPedalMoveSpeed());
        //在界面中添加人踏板
        layoutService.addPedal(gamePedal);
        //将踏板添加到数据中
        game.getPedalList().add(gamePedal);
    }

    /**
     * 移动人物和踏板
     *
     * @param game 游戏内容
     * @return 人物是否死亡
     */
    public boolean moveGame(Game game) {
        //遍历踏板集合
        Iterator<GamePedal> iterator = game.getPedalList().iterator();
        while (iterator.hasNext()) {
            GamePedal pedal = iterator.next();
            //踏板出界,销毁踏板
            if (gamePedalService.isPedalDead(pedal)) {
                //从界面删除踏板
                layoutService.removePedal(pedal);
                //从踏板集合删除踏板
                iterator.remove();
            } else {
                //移动踏板
                gamePedalService.movePedal(pedal);
            }
        }
        //游戏人物
        GameMen men = game.getGameMen();
        //标志是否正在下落
        boolean isDrop = true;
        //遍历踏板集合
        for (GamePedal pedal : game.getPedalList()) {
            //人物没有下落(人物在踏板上)
            if (!gameMenService.isMenDrop(men, pedal)) {
                //修改标志
                isDrop = false;
                //更新当前踏板
                game.setCurrentPedal(pedal);
                break;
            }
        }
        //判断人物是否正在下降
        if (isDrop) {
            //人物在下落,更新人物位置
            gameMenService.moveMenDown(men);
        } else {
            //人物在踏板上,更新人物位置
            gameMenService.moveMenUp(men, game.getCurrentPedal());
        }
        //返回人物是否死亡结果
        return !gameMenService.isMenDead(men);
    }

    /**
     * 更新时间
     *
     * @param game 游戏内容
     */
    public void updateTime(Game game) {
        //更新时间
        game.setTime(game.getTime() + 1000);
        //界面更新
        layoutService.updateTime(game.getTime());

    }

}
