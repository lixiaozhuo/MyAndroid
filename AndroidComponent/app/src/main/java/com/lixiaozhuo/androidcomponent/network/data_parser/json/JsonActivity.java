package com.lixiaozhuo.androidcomponent.network.data_parser.json;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lixiaozhuo.androidcomponent.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * JSON解析
 */
public class JsonActivity extends Activity implements View.OnClickListener {
    //显示控件
    private TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json);
        //获取控件并绑定事件
        responseText = findViewById(R.id.response_text);
        Button buildJson = findViewById(R.id.build_json);
        Button parseJson = findViewById(R.id.parse_json);
        Button sendRequest = findViewById(R.id.send_request);
        Button parseRequest = findViewById(R.id.parse_request);
        sendRequest.setOnClickListener(this);
        buildJson.setOnClickListener(this);
        parseJson.setOnClickListener(this);
        parseRequest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        //构建json
        if (v.getId() == R.id.build_json) {
            buildJson();
        }
        //解析json
        if (v.getId() == R.id.parse_json) {
            parseJson();
        }
        //发送请求
        if (v.getId() == R.id.send_request) {
            sendRequest();
            Toast.makeText(this,"请求成功",Toast.LENGTH_SHORT).show();
        }
        //解析请求
        if (v.getId() == R.id.parse_request) {
            parseRequest();
            Toast.makeText(this,"正在解析",Toast.LENGTH_SHORT).show();
        }

    }

    //构件json
    private void buildJson() {
        try {
            JSONObject person = new JSONObject();
            JSONArray phone = new JSONArray();
            phone.put("123");
            person.put("phone", phone);
            person.put("name", "Tom");
            person.put("age", "18");
            JSONObject address = new JSONObject();
            address.put("country", "china");
            address.put("Province", "He Bei");
            person.put("address", address);
            person.put("married", false);
            //显示数据
            showResponse(person.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //解析json
    private void parseJson() {
        try {
            JSONObject object = new JSONObject(responseText.getText().toString());
            JSONArray array = object.getJSONArray("phone");
            String phone = array.get(0).toString();
            String name = object.getString("name");
            int age = object.getInt("age");
            JSONObject object1 = object.getJSONObject("address");
            String country = object1.getString("country");
            String province = object1.getString("Province");
            Boolean married = object.getBoolean("married");
            //显示数据
            showResponse("手机: " + phone + "\n姓名: " + name + "\n年龄: " + age + "\n国家: " + country + "\n省份: " + province + "\n婚姻: " + married);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //发送请求
    private void sendRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://e0d980a0-9406-4d96-b8af-8a3375049db0.coding.io/conn.php");
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
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    //显示响应数据
                    showResponse(response.toString());
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
            JSONArray jsonArray = new JSONArray(responseText.getText().toString());
            String result = "";
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("psw");

                result += "[id = " + id + ",name = " + name + "]\n";
                showResponse(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //显示数据
    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                responseText.setText(response);
            }
        });
    }


}