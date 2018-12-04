package com.lixiaozhuo.game.thread;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.lixiaozhuo.game.service.WeatherService;

/**
 * 天气线程
 */
public class WeatherThread extends Thread {
    //展现天气命令
    private final static int SHOW_RESPONSE = 1;
    //天气服务
    private WeatherService weatherService;
    //天气显示控件
    private TextView textView;
    //天气结果
    private String weather;

    public WeatherThread(TextView textView) {
        this.textView = textView;
        weatherService = new WeatherService();
    }

    @Override
    public void run() {
        //获取天气数据响应
        String response = weatherService.getResponse();
        //解析响应的天气数据
        weather = weatherService.parseResponse(response);
        //通知更新天气信息
        handler.sendEmptyMessage(SHOW_RESPONSE);
    }


    /**
     * 接受子线程发来的消息，并且调用相应的方法执行更新UI操作
     */
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            //显示当前天气
            if (msg.what == SHOW_RESPONSE) {
                weatherService.showWeather(textView,weather);
            }
            return false;
        }
    });
}
