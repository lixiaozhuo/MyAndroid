package com.lixiaozhuo.game.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.lixiaozhuo.game.R;
import com.lixiaozhuo.game.service.MusicService;
import com.lixiaozhuo.game.thread.WeatherThread;

/**
 * 游戏初始菜单
 */
public class GameMenuActivity extends Activity implements View.OnClickListener {
    //音乐业务
    private MusicService musicService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_menu);
        //运行加载天气线程
        new WeatherThread((TextView) findViewById(R.id.showWeather)).start();
        //获取控件并绑定事件
        findViewById(R.id.btnPlay).setOnClickListener(this);
        findViewById(R.id.btnRecord).setOnClickListener(this);
        findViewById(R.id.btnSetting).setOnClickListener(this);
        findViewById(R.id.btnExit).setOnClickListener(this);
        //作者按钮
        findViewById(R.id.btnAuthor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开github
                startActivity(new Intent(GameMenuActivity.this, GitHubPageActivity.class));
            }
        });
        //音乐业务
        musicService = new MusicService(this);
    }

    @Override
    public void onClick(View view) {
        //敲击声音
        musicService.playKeyMusic();
        switch (view.getId()) {
            //开始游戏
            case R.id.btnPlay:
                startActivity(new Intent(this, GamePlayActivity.class));
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
