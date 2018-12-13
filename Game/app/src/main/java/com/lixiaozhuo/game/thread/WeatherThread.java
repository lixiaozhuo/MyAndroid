package com.lixiaozhuo.game.thread;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.lixiaozhuo.game.MyApplication;
import com.lixiaozhuo.game.service.LocationService;
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
        //显示控件
        this.textView = textView;
        //初始化天气业务
        weatherService = new WeatherService();
    }

    @Override
    public void run() {
        //获取定位
        new LocationService().startLocation();
        //等待获取位置信息
        while (LocationService.isSuccess == null) {
            if (LocationService.isSuccess != null) {
                break;
            }
        }
        //获取天气数据响应
        String response = weatherService.getResponse(MyApplication.ADCode);
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
                textView.setText(weather);
            }
            return false;
        }
    });
}
