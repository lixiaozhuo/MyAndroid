package com.lixiaozhuo.game.service;

import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 天气显示
 */
public class WeatherService {
    /**
     * 获取响应数据
     */
    public String getResponse(String city){
        //存储响应
        StringBuilder response = new StringBuilder();
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            //天气数据url
            // URL url = new URL("https://api.seniverse.com/v3/weather/now.json" +
            // "?key=wpjt7mayjnio3gq9&location="+city+"&language=zh-Hans&unit=c");
            URL url = new URL("https://restapi.amap.com/v3/weather/weatherInfo?" +
                    "key=b6106ba9e02d1fcd913e705e5712209b&city="+city+"&extensions=base");
            connection = (HttpURLConnection) url.openConnection();
            //设置请求所用的方法
            connection.setRequestMethod("GET");
            //设置连接超时，读取的毫秒数
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            //获取服务器的返回的输入流
            InputStream in = connection.getInputStream();
            // 对获取到的输入流进行读取
            reader = new BufferedReader(new InputStreamReader(in));
            //缓存数据
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭数据流
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //关闭连接
            if (connection != null) {
                connection.disconnect();
            }
            return response.toString();
        }

    }

    /**
     * 解析响应数据
     * @param response
     */
    public String parseResponse(String response){
        String result="";
        try{
            //解析数据
            JSONObject jsonObject = new JSONObject(response);
            JSONArray lives = jsonObject.getJSONArray("lives");
            JSONObject data = lives.getJSONObject(0);
            //城市
            String city = data .getString("city");
            //天气状况
            String weather = data .getString("weather");
            //温度
            String temperature = data.getString("temperature");
            result = city+ " " + weather+" " + temperature + "℃";
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            return result;
        }
    }

    /**
     * 显示天气
     * @param textView
     * @param response
     */
    public void showWeather(TextView textView, String response){
        textView.setText(response);
    }

}
