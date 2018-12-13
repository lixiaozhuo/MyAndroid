package com.lixiaozhuo.androidcomponent._10_network.data_parser.login;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lixiaozhuo.androidcomponent.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 远程登录
 */
public class NetworkLoginActivity extends Activity {
    private final static String TAG = "App:NetworkLogin";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.network_login);
    }

    /**
     * 登录
     *
     * @param v
     */
    public void onLoginClick(View v) {
        //发送请求
        sendRequest();
        Toast.makeText(this, "正在远程登录", Toast.LENGTH_SHORT).show();
    }

    /**
     * 注册
     *
     * @param v
     */
    public void onRegClick(View v) {
        Toast.makeText(this, "注册功能暂未实现", Toast.LENGTH_SHORT).show();
    }

    //存储响应数据
    private StringBuilder response;

    //发送请求
    private void sendRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://e0d980a0-9406-4d96-b8af-8a3375049db0.coding.io/conn.php");
                    //获取连接
                    connection = (HttpURLConnection) url.openConnection();
                    //设置连接方式
                    connection.setRequestMethod("GET");
                    //设置连接超时，读取的毫秒数
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    //获取输入流
                    InputStream in = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    //初始化数据
                    response = new StringBuilder();
                    //数据缓存
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    //显示响应结果
                    Log.e(TAG, response.toString());
                    //解析请求
                    parseRequest();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        //关闭连接
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    //解析请求
    private void parseRequest() {
        try {
            //转换为json数组
            JSONArray jsonArray = new JSONArray(response.toString());
            //存储解析结果
            String result = "";
            for (int i = 0; i < jsonArray.length(); i++) {
                //转换为json对象
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                //id
                String id = jsonObject.getString("id");
                //密码
                String psw = jsonObject.getString("psw");
                result = "\n[ID = " + id + "  , Name = " + psw + "]\n";
                //显示结果
                Log.e(TAG, result);
                if (id.equals("1") && psw.equals("1")){
                    Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
