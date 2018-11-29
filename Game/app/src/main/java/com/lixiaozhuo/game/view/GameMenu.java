package com.lixiaozhuo.game.view;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;


import com.lixiaozhuo.game.R;
import com.lixiaozhuo.game.service.MusicService;

import java.util.HashMap;

/**
 * 游戏初始菜单
 */
public class GameMenu extends Activity implements View.OnClickListener {

    private MusicService musicService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.game_menu);
        //获取控件并绑定事件
        findViewById(R.id.btnPlay).setOnClickListener(this);
        findViewById(R.id.btnRecord).setOnClickListener(this);
        findViewById(R.id.btnSetting).setOnClickListener(this);
        findViewById(R.id.btnExit).setOnClickListener(this);
        musicService = new MusicService(this);
    }

    @Override
    public void onClick(View view) {
        //敲击声音
        musicService.playKeyMusic();
        switch (view.getId()){
            //开始游戏
            case R.id.btnPlay:
                startActivity(new Intent(this,PlayGame.class));
                break;
            //排行榜
            case R.id.btnRecord:
                new GameRecordDialog(this).show();
                break;
            //设置
            case R.id.btnSetting:
                new GameSettingDialog(this).show();
                break;
            //关闭
            case R.id.btnExit:
                finish();
                break;
        }
    }
}
