package com.lixiaozhuo.game.thread;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lixiaozhuo.game.R;
import com.lixiaozhuo.game.domain.GameMen;
import com.lixiaozhuo.game.domain.GamePedal;
import com.lixiaozhuo.game.domain.GameRecord;
import com.lixiaozhuo.game.domain.GameScore;
import com.lixiaozhuo.game.domain.GameSetting;
import com.lixiaozhuo.game.service.MusicService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

//游戏状态
enum GameState {
    Running,
    Stop,
    Pause,
}

//命令
class Command {
    public final static int PEDAL_ADD = 1;
    public final static int PEDAL_MOVE = 2;
    public final static int UPDATE_TIME = 3;
}

/**
 * 踏板线程操作
 */
public class PedalThread extends Thread {
    /////////////////////////////////固定属性//////////////////////////////////////////////////
    //上下文
    private Context context;
    //布局
    private RelativeLayout layout;
    //屏幕属性
    private DisplayMetrics displayMetrics;
    //游戏设置
    private GameSetting gameSetting;
    ///////////////////////////////////游戏属性////////////////////////////////////////////////
    //踏板集合
    private List<GamePedal> pedalList = new ArrayList<>();
    //当前踏板
    private GamePedal currentPedal;
    //游戏人物
    private GameMen gameMen;
    //游戏状态(控制游戏的状态)
    private volatile GameState gameState;
    //计时
    private long time = 0;

    ////////////////////////////////初始化线程///////////////////////////////////////////////////
    public PedalThread(Context context, RelativeLayout layout, DisplayMetrics displayMetrics) {
        //上下文
        this.context = context;
        //布局
        this.layout = layout;
        //屏幕属性
        this.displayMetrics = displayMetrics;
        //获取游戏设置
        initGameSetting();
    }

    //获取游戏设置
    private void initGameSetting() {
        //取出人物编号和级别编号
        SharedPreferences preferences = context.getSharedPreferences("SettingData", Context.MODE_PRIVATE);
        gameSetting = new GameSetting(Integer.valueOf(preferences.getString("menNO", String.valueOf(R.id.radioMen1))),
                Integer.valueOf(preferences.getString("levelNO", String.valueOf(R.id.radioLevel1))));
    }

    ///////////////////////////////////////控制游戏/////////////////////////////////////////////////////////////////
    //音乐服务连接
    private ServiceConnection musicServiceConnection = new ServiceConnection() {
        //活动与服务连接断开时调用
        @Override
        public void onServiceDisconnected(ComponentName arg0) {
        }
        //活动与服务成功绑定时调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
        }
    };

    /**
     * 开始游戏
     */
    private void startGame() {
        //设置游戏状态为游戏中
        gameState = GameState.Running;
        //开启音乐服务
        context.bindService(new Intent(context, MusicService.class), musicServiceConnection, Context.BIND_AUTO_CREATE);
        //清空计时
        time = 0;
        //初始化界面
        initView();
    }


    //初始化界面数据
    private void initView() {
        //将原人物清除
        if (gameMen != null) {
            layout.removeView(gameMen.getMenImage());
        }
        //清除界面中的踏板
        for (GamePedal pedal : pedalList) {
            layout.removeView(pedal.getPedalImage());
        }
        //清空踏板
        pedalList.clear();
        //初始化跳板
        currentPedal = addPedal();
        //初始化人物
        gameMen = new GameMen(gameSetting.getMenNO(),
                currentPedal.getX() + (currentPedal.getLength() / 2),
                displayMetrics.heightPixels / 4,
                gameSetting.getMovePedalTime() * 2, context);
        //将人物加入界面中
        layout.addView(gameMen.getMenImage());
    }
    //////////////////////////////////////公共函数/////////////////////////////////////////////////////////////////////

    /**
     * 控制人物移动
     */
    public void moveGameMen(int x) {
        int newMenX = gameMen.getX() + x;
        //移动后人物未超出界面
        if ((newMenX > 0) && (newMenX < (displayMetrics.widthPixels - gameMen.getMenImage().getWidth()))) {
            gameMen.setX(newMenX);
        }
    }

    /**
     * 暂停游戏
     */
    public void pauseGame() {
        //设置游戏状态为暂停状态
        gameState = GameState.Pause;
        //暂停音乐播放器
        context.unbindService(musicServiceConnection);
    }

    /**
     * 继续游戏
     */
    public void continueGame() {
        //设置游戏状态为游戏中
        gameState = GameState.Running;
        //开启音乐播放器
        context.bindService(new Intent(context, MusicService.class), musicServiceConnection, Context.BIND_AUTO_CREATE);
    }

    /**
     * 结束游戏
     */
    public void stopGame() {
        //设置游戏状态为结束
        gameState = GameState.Stop;
        //关闭窗口
        ((Activity) context).finish();
    }

    /////////////////////////////////////游戏线程运行///////////////////////////////////////////////////////////////////
    /**
     * 接受子线程发来的消息，并且调用相应的方法执行更新UI操作
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //添加或移动
            if (msg.what == Command.PEDAL_ADD || msg.what == Command.PEDAL_MOVE) {
                if (msg.what == Command.PEDAL_ADD) {
                    //添加踏板
                    addPedal();
                }
                //移动人物和踏板
                move();
                //判断人物是否死亡
                isDead();
            }
            //更新计时
            if (msg.what == Command.UPDATE_TIME) {
                time += 1000;
                String score = new SimpleDateFormat("mm" + "分" + "ss" + "秒").format(new Date(time));
                //显示当前计时
                ((TextView) layout.findViewById(R.id.textCount)).setText(score);
                ;
            }
        }
    };

    @Override
    public void run() {
        //游戏开始设置
        startGame();
        //开始计时线程
        TimeThread.start();
        //游戏线程
        int count = 0;
        while (gameState != GameState.Stop) {
            //阻塞线程达到暂停的方法
            while (gameState == GameState.Pause) ;
            try {
                //睡眠指定时间移动踏板
                Thread.sleep(20);
                //发送移动消息
                handler.sendEmptyMessage(Command.PEDAL_MOVE);
                //固定次数后添加踏板
                if (count == gameSetting.getAddPedalInterval()) {
                    //发送添加消息
                    handler.sendEmptyMessage(Command.PEDAL_ADD);
                    count = 0;
                }
                count++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //计时线程
    private Thread TimeThread = new Thread() {
        @Override
        public void run() {
            while (gameState != GameState.Stop) {
                //阻塞线程达到暂停的方法
                while (gameState == GameState.Pause) ;
                try {
                    //计时
                    Thread.sleep(1000);
                    //发送更新时间消息
                    handler.sendEmptyMessage(Command.UPDATE_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };


    /////////////////////////////私有函数//////////////////////////////////////////////////////

    /**
     * 添加踏板
     */
    private GamePedal addPedal() {
        //获取踏板宽
        BitmapFactory.Options options = new BitmapFactory.Options();
        BitmapFactory.decodeResource(context.getResources(), R.mipmap.pedal, options);
        int pedalImageWidth = options.outWidth;
        int length;
        int x;
        int y = displayMetrics.heightPixels;
        while (true) {
            //踏板长度为1/2-1倍之间
            length = new Random().nextInt(pedalImageWidth - 3 * (pedalImageWidth / 4)) + 3 * (pedalImageWidth / 4);
            //获取随机横坐标
            x = new Random().nextInt(displayMetrics.widthPixels - 50) + 50;
            //符合条件
            if (x + length < displayMetrics.widthPixels) {
                break;
            }
        }
        GamePedal pedal = new GamePedal(length, x, y, gameSetting.getMovePedalTime(), context);
        //踏板集合添加踏板
        pedalList.add(pedal);
        //界面添加踏板
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(length, 50);
        layout.addView(pedal.getPedalImage(), layoutParams);
        return pedal;
    }

    /**
     * 移动踏板和人物
     */
    private void move() {
        //遍历踏板集合
        Iterator<GamePedal> iterator = pedalList.iterator();
        while (iterator.hasNext()) {
            GamePedal pedal = iterator.next();
            //踏板出界,销毁踏板
            if (pedal.getY() <= 0) {
                //从界面删除踏板
                layout.removeView(pedal.getPedalImage());
                //从踏板集合删除踏板
                iterator.remove();
            } else {
                //更新踏板位置
                pedal.setY(pedal.getY() - pedal.getSpeed());
            }
        }
        //获取人物纵坐标
        int MenY = gameMen.getY();
        //判断人物是否正在下降
        if (isDrop()) {
            //人物在下落,更新人物位置
            gameMen.setY(MenY + gameMen.getSpeed());
        } else {
            //人物在踏板上,更新人物位置
            gameMen.setY(currentPedal.getY() - gameMen.getMenImage().getHeight());
        }
    }

    /**
     * 判断人物是否掉落
     *
     * @return
     */
    private boolean isDrop() {
        //人物位置
        int menX = gameMen.getX() + gameMen.getMenImage().getWidth() / 2;
        int menY = gameMen.getY() + gameMen.getMenImage().getHeight();
        for (GamePedal pedal : pedalList) {
            //人物处于此踏板水平位置上
            if (menX >= pedal.getX() && menX <= (pedal.getX() + pedal.getLength())) {
                int pedalY = pedal.getY();

                //条件1
                //人物位置<=踏板位置<=人物位置 + 人物速度
                // (踏板位置-人物速度<=人物位置<=踏板位置)
                //条件2
                //人物位置<=踏板位置+踏板速度<=人物位置+人物速度
                // (踏板位置+踏板速度-人物速度<=人物位置<=踏板位置+踏板速度)
                //综合
                //踏板位置-人物速度<=人物位置<=踏板位置+踏板速度
                if ((pedal.getY() - gameMen.getSpeed()) <= menY && menY <= (pedalY + pedal.getSpeed())) {
                    //当前踏板
                    currentPedal = pedal;
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 判断人物是否死亡
     */
    private void isDead() {
        //手机屏幕高度
        int screen = displayMetrics.heightPixels;
        //人物死亡
        if (!(gameMen.getY() < screen && gameMen.getY() > 10) && gameState == GameState.Running) {
            //暂停游戏
            pauseGame();
            //获取成绩
           GameScore gameScore =  new GameScore(gameSetting.getLevelNO(), new Date(time).getTime());
            //更新成绩
            new GameRecord(context).updateRecord(gameScore);
            //提示结束
            new AlertDialog.Builder(context)
                    .setTitle("Game Over !")
                    .setMessage("您的成绩为 : " + gameScore.getDate())
                    .setPositiveButton("返回主菜单", new DialogInterface.OnClickListener() { //返回主菜单的操作
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //结束游戏
                            stopGame();
                        }
                    }).setCancelable(false)
                    .setNegativeButton("再来一把", new DialogInterface.OnClickListener() {
                        //重新开始游戏
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //开始游戏
                            startGame();
                        }
                    }).show();
        }
    }


}