package com.lixiaozhuo.androidcomponent.network.network_operate;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lixiaozhuo.androidcomponent.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 网络操作(网络连接与网络状态)
 */
public class NetworkOperatorActivity extends Activity implements View.OnClickListener{
    //显示控件
    private  TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.network_operate);
        //获取控件并绑定事件
        Button httpConnection =findViewById(R.id.httpConnection);
        httpConnection.setOnClickListener(this);
        Button networkInformation =findViewById(R.id.networkInformation);
        networkInformation.setOnClickListener(this);
        //获取显示控件
        textView =  findViewById(R.id.textView);
        //注册网络改变监控广播
        register();
    }



    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.httpConnection) {
            Toast.makeText(this,"正在加载响应数据",Toast.LENGTH_SHORT).show();
            //发送网络请求
            sendRequestWithHttpURLConnection();

        }else if(v.getId() == R.id.networkInformation){
            Toast.makeText(this,"正在加载网络状态数据",Toast.LENGTH_SHORT).show();
            //显示当前网络状态
            textView.setText(NetworkConnectChangedReceiver.networkState);

        }
    }
    ///////////////////////////////////HTTP连接////////////////////////
    /**
     *  发送网络请求
     */
    private void sendRequestWithHttpURLConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://www.baidu.com");
                    connection = (HttpURLConnection) url.openConnection();
                    //设置请求方式
                    connection.setRequestMethod("GET");
                    //设置连接超时，读取的毫秒数
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    //获取服务器的返回的输入流
                    InputStream in = connection.getInputStream();
                    // 对获取到的输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(in));
                    //保存读取到的信息
                    StringBuilder response = new StringBuilder();
                    //缓存读入的信息
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    //显示返回的数据
                    showResponse(response.toString());
                    //将数据保存到文件
                    save(response.toString());

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
                        //关闭HTTP连接
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    /**
     * 显示获取到的信息
     * @param response
     */
    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(response);
            }
        });
    }

    /**
     * 保存文件
     * @param inputText
     */
    public void save(String inputText) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            //将字符串保存到文件中
            out = openFileOutput("networkData", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    ///////////////////////////////////显示网络信息////////////////////////
    //注册监听器
    private void register() {
        //Intent过滤器
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        filter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        filter.addAction("android.net.wifi.STATE_CHANGE");
        //网络改变监控广播
        NetworkConnectChangedReceiver mNetworkChangeListener= new NetworkConnectChangedReceiver();
        //注册监听器
        registerReceiver(mNetworkChangeListener,filter);
    }
}
