package com.lixiaozhuo.game.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
        //判断是否获取权限,如果缺少权限则申请权限
        if (getPermissions()) {
            //已拥有权限,运行加载天气线程
            new WeatherThread((TextView) findViewById(R.id.showWeather)).start();
        }
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
        musicService = new MusicService();
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


    //获取权限
    private boolean getPermissions() {
        //危险权限集合
        String[] Permissions = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,//网络定位
                Manifest.permission.ACCESS_FINE_LOCATION,//GPS定位
                Manifest.permission.WRITE_EXTERNAL_STORAGE,//向存储中写入数据
                Manifest.permission.READ_PHONE_STATE,};//手机当前的状态
        //标志是否拥有权限
        boolean isHavePermission = true;
        //检查是否缺少权限
        for (String permission : Permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                isHavePermission = false;
                break;
            }
        }
        //缺少权限
        if (!isHavePermission) {
            //进行授权
            ActivityCompat.requestPermissions(this, Permissions, 1);
        }
        return isHavePermission;
    }

    //获取权限后
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //运行加载天气线程
        new WeatherThread((TextView) findViewById(R.id.showWeather)).start();
    }
}
