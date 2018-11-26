package com.lixiaozhuo.game.activity;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;


import com.lixiaozhuo.game.R;
import com.lixiaozhuo.game.view.GameSettingDialog;
import com.lixiaozhuo.game.view.GameRecordDialog;

import java.util.HashMap;

/**
 * 游戏初始菜单
 */
public class GameMenu extends Activity implements View.OnClickListener {
    //游戏人物对应关系
    public static HashMap<Integer,String> gameMenMap = new HashMap<>();
    //游戏级别对应关系
    public static HashMap<Integer,String> gameLevelMap = new HashMap<>();
    //音乐池
    public static SoundPool soundPool;
    //歌曲id
    public static int songID;

    //初始化静态数据
    static{
        //初始化游戏人物
        gameMenMap.put(R.id.radioLevel1,"人物1");
        gameMenMap.put(R.id.radioLevel2,"人物2");
        //初始化游戏级别
        gameLevelMap.put(R.id.radioLevel1,"级别1");
        gameLevelMap.put(R.id.radioLevel2,"级别2");
        gameLevelMap.put(R.id.radioLevel3,"级别3");
        gameLevelMap.put(R.id.radioLevel4,"级别4");
        //音乐池初始化
        soundPool =new SoundPool.Builder()
                .setMaxStreams(1)
                .setAudioAttributes(new AudioAttributes.Builder()
                        .setLegacyStreamType(AudioManager.STREAM_MUSIC)
                        .build())
                .build();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.game_menu);
        //加载音乐
        songID= soundPool.load(this,R.raw.key_music,1);
        //获取控件并绑定事件
        ImageButton btnPlay=findViewById(R.id.btnPlay);
        ImageButton btnRecord=findViewById(R.id.btnRecord);
        ImageButton btnSetting=findViewById(R.id.btnSetting);
        ImageButton btnExit=findViewById(R.id.btnExit);
        btnPlay.setOnClickListener(this);
        btnRecord.setOnClickListener(this);
        btnSetting.setOnClickListener(this);
        btnExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //敲击声音
        soundPool.play(songID,1,1,0,0,1);
        switch (view.getId()){
            //开始游戏
            case R.id.btnPlay:
                Intent intent=new Intent(this,PlayGame.class);
                //开启游戏活动
                startActivity(intent);
                break;
            //排行榜
            case R.id.btnRecord:
                GameRecordDialog dialogScore=new GameRecordDialog(this,300,500);
                dialogScore.show();
                break;
            //设置
            case R.id.btnSetting:
                GameSettingDialog dialogSetting = new GameSettingDialog(this,300,500);
                dialogSetting.show();
                break;
            //关闭
            case R.id.btnExit:
                finish();
                break;
        }
    }
}
