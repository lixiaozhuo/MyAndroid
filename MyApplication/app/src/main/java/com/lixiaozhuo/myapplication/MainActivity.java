package com.lixiaozhuo.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    //创建JSON数据
    public String createJSON() throws JSONException {
        //JSON对象
        JSONObject object = new JSONObject();
        //JSON数组
        JSONArray phone = new JSONArray();
        phone.put("12345678");
        object.put("phone", phone);
        object.put("name", "xxx");
        object.put("age", 100);
        //JSON对象
        JSONObject address = new JSONObject();
        address.put("country", "china");
        address.put("province", "jiangsu");
        object.put("address", address);
        object.put("married", false);
        //输出构件好的JSON对象
        Log.e("JSON Object=", object.toString());
        return object.toString();
    }

    //解析JSON
    public void parseJSON() throws JSONException {
        String json = createJSON();
        //构件JSON对象
        JSONObject object = new JSONObject(json);
        //JSON数组
        JSONArray phone = object.getJSONArray("phone");
        String value = (String) phone.get(0);
        String name = object.getString("name");
        Integer age = object.getInt("age");
        //JSON对象
        JSONObject address = object.getJSONObject("address");
        String country = address.getString("country");
        String province = address.getString("province");
        Boolean married = object.getBoolean("married");
        //输出解析的数据
        Log.e("JSON phone=", value);
        Log.e("JSON name=", name);
        Log.e("JSON age=", age.toString());
        Log.e("JSON country=", country);
        Log.e("JSON province=", province);
        Log.e("JSON married=", married.toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //JSON
        try {
            parseJSON();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ////////////////////////////////////////////////////////////////////////////////////////////
        //Handler1:主线程中创建
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        Log.e("Handler1", "收到消息");
                        break;
                }
            }
        };
        Message msg = new Message();
        msg.arg1 = 0x01;
        msg.arg2 = 0x02;
        msg.obj = "ObjectType";
        msg.what = 1;
        handler.sendMessage(msg);
        ////////////////////////////////////////////////////////////////////////////////////////////
        //Handler2:非UI线程中创建
        Thread thread = new Thread() {
            private Handler handler;

            @Override
            public void run() {
                Looper.prepare();
                handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        switch (msg.what) {
                            case 2:
                                Log.e("Handler2", "收到消息");
                                break;
                        }
                    }
                };
                Looper.loop();
            }
        };
        ////////////////////////////////////////////////////////////////////////////////////////////
        //AsyncTask
        AsyncTask<Integer, Integer, Integer> customTask = new AsyncTask<Integer, Integer, Integer>() {
            //任务执行前:可以访问UI组件
            @Override
            protected void onPreExecute() {

            }

            //执行任务:不可以访问UI组件
            @Override
            protected Integer doInBackground(Integer... integers) {
                Log.e("AsyncTask", "AsyncTask执行任务");
                return null;
            }

            //任务执行后:可以访问UI组件
            @Override
            protected void onPostExecute(Integer integer) {

            }
        }.execute();
    }


}
