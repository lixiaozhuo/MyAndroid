package com.lixiaozhuo.androidcomponent.service;

//音乐播放服务接口
interface IServicePlayer {
    void play(); //播放
    void pause(); //暂停
    void stop(); //停止
    int getDuration(); //时长
    int getCurrentPosition(); //当前位置
    void seekTo(int current); //拖动位置
    boolean setLoop(boolean loop); //是否循环播放
}
