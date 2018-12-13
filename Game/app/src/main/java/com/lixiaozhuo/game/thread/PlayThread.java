package com.lixiaozhuo.game.thread;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.widget.RelativeLayout;

import com.lixiaozhuo.game.R;
import com.lixiaozhuo.game.common.GameState;
import com.lixiaozhuo.game.domain.Game;
import com.lixiaozhuo.game.domain.GameScore;
import com.lixiaozhuo.game.service.GameMenService;
import com.lixiaozhuo.game.service.GameRecordService;
import com.lixiaozhuo.game.service.GameService;
import com.lixiaozhuo.game.service.GameSettingService;
import com.lixiaozhuo.game.service.MusicService;

import java.util.Date;


/**
 * 游戏线程操作
 */
public class PlayThread extends Thread {
    //游戏命令
    private final static int PEDAL_ADD = 1;
    private final static int PEDAL_MOVE = 2;
    private final static int UPDATE_TIME = 3;

    //上下文
    private Activity activity;
    //游戏业务
    private GameService gameService;
    //音乐服务
    private MusicService musicService;
    //游戏信息
    private Game game;

    public PlayThread(Activity activity) {
        this.activity = activity;
        gameService = new GameService((RelativeLayout) activity.findViewById(R.id.playGame));
        musicService = new MusicService();
        game = new Game(new GameSettingService().getSetting());
    }

    @Override
    public void run() {
        //开始游戏
        startGame();
        //计时线程
        timeThread.start();
        //游戏线程
        gameThread.start();
    }
    /////////////////////////////////////游戏线程运行///////////////////////////////////////////////////////////////////
    /**
     * 接受子线程发来的消息，并且调用相应的方法执行更新UI操作
     */
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (game.getGameState() != GameState.RUNNING) {
                return false;
            }
            //添加或移动
            if (msg.what == PEDAL_ADD || msg.what == PEDAL_MOVE) {
                if (msg.what == PEDAL_ADD) {
                    //添加踏板
                    gameService.addPedal(game);
                }
                //移动人物和踏板
                if (!gameService.moveGame(game)) {
                    //人物死亡
                    stopGame();
                }
            }
            //更新计时
            if (msg.what == UPDATE_TIME) {
                gameService.updateTime(game);
            }
            return false;
        }
    });

    //游戏线程
    private Thread gameThread = new Thread() {
        @Override
        public void run() {
            int count = 0;
            while (game.getGameState() != GameState.FINISH) {
                //阻塞线程达到暂停的方法
                while (game.getGameState() == GameState.PAUSE) {
                    try {
                        Thread.sleep(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                try {
                    //睡眠指定时间移动踏板
                    Thread.sleep(20);
                    //发送移动消息
                    handler.sendEmptyMessage(PEDAL_MOVE);
                    //固定次数后添加踏板
                    if (count == game.getGameSetting().getAddPedalInterval()) {
                        //发送添加消息
                        handler.sendEmptyMessage(PEDAL_ADD);
                        count = 0;
                    }
                    count++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    //计时线程
    private Thread timeThread = new Thread() {
        @Override
        public void run() {
            while (game.getGameState() != GameState.FINISH) {
                //阻塞线程达到暂停的方法
                while (game.getGameState() == GameState.PAUSE) {
                    try {
                        Thread.sleep(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                try {
                    //计时
                    Thread.sleep(1000);
                    //发送更新时间消息
                    handler.sendEmptyMessage(UPDATE_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    ///////////////////////////////////////控制游戏/////////////////////////////////////////////////////////////////

    /**
     * 开始游戏
     */
    private void startGame() {
        //初始游戏
        gameService.initGame(game);
        //开始音乐
        musicService.startGameMusic();
    }

    /**
     * 暂停游戏
     */
    public void pauseGame() {
        //设置游戏状态为暂停状态
        game.setGameState(GameState.PAUSE);
        //暂停音乐
        musicService.stopGameMusic();
        new AlertDialog.Builder(activity).setTitle("暂停")
                .setMessage("暂停了游戏")
                .setPositiveButton("返回主菜单", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //结束游戏
                        finishGame();
                    }
                }).setNegativeButton("继续游戏", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //继续游戏
                continueGame();
            }
        }).setCancelable(false).show();
    }

    /**
     * 停止游戏
     */
    private void stopGame() {
        //设置游戏状态为暂停
        game.setGameState(GameState.PAUSE);
        //停止音乐
        musicService.stopGameMusic();

        //获取成绩
        GameScore gameScore = new GameScore(game.getGameSetting().getLevelNO(), new Date(game.getTime()).getTime());
        //更新成绩
        new GameRecordService().updateRecord(gameScore);
        //提示结束
        new AlertDialog.Builder(activity).setTitle("游戏结束 !")
                .setMessage("您的成绩为 : " + gameScore.getScore())
                .setPositiveButton("返回主菜单", new DialogInterface.OnClickListener() {
                    //返回主菜单的操作
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //结束游戏
                        finishGame();
                    }
                }).setNegativeButton("再来一把", new DialogInterface.OnClickListener() {
            //重新开始游戏
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //开始游戏
                startGame();
            }
        }).setCancelable(false).show();
    }

    /**
     * 继续游戏
     */
    private void continueGame() {
        //设置游戏状态为游戏中
        game.setGameState(GameState.RUNNING);
        //开始音乐
        musicService.startGameMusic();
    }

    /**
     * 结束游戏
     */
    private void finishGame() {
        //设置游戏状态为结束
        game.setGameState(GameState.FINISH);
        //关闭窗口
        activity.finish();
    }

    /**
     * 控制游戏人物移动
     *
     * @param x 移动位置
     */
    public void moveGameMen(int x) {
        new GameMenService().moveMen(game.getGameMen(), x);
    }


}