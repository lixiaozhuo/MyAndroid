package com.lixiaozhuo.androidcomponent._11_thread.asynctask.asynctask2;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lixiaozhuo.androidcomponent.R;

/**
 * AsyncTask2
 */
public class AsyncTask2Activity extends Activity {
    private static final String TAG = "App:AsyncTask";
    //计时器1值(分钟)
    private EditText chronographValue1;
    //计时器1显示
    private TextView chronographText1;
    //开始计时器1
    private Button start1;
    //计时器2值(秒)
    private EditText chronographValue2;
    //计时器2显示
    private TextView chronographText2;
    //开始计时器2
    private Button start2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asynctask2);

        //获取控件并绑定事件
        start1 = findViewById(R.id.start);
        chronographText1 = findViewById(R.id.chronoText);
        chronographValue1 = findViewById(R.id.chronoValue);
        start2 = findViewById(R.id.start1);
        chronographText2 = findViewById(R.id.chronoText1);
        chronographValue2 = findViewById(R.id.chronoValue1);
        //开始计时器1
        start1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取EditText里的数值
                int value = Integer.parseInt(String.valueOf(chronographValue1.getText()));
                // 验证数值是否大于零
                if (value > 0) {
                    //开始计时
                    new Chronograph().execute(value);
                } else {
                    Toast.makeText(AsyncTask2Activity.this, "请输入一个大于零的整数值 !", Toast.LENGTH_LONG).show();
                }
            }
        });
        //开始计时器2
        start2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取EditText里的数值
                int value = Integer.parseInt(String.valueOf(chronographValue2.getText()));
                // 验证数值是否大于零
                if (value > 0) {
                    MyClock(value);
                    // 发布增量
                    chronographText2.setText("等待了：" + value + "秒");
                } else {
                    Toast.makeText(AsyncTask2Activity.this, "请输入一个大于零的整数值 !", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    //计时
    private class Chronograph extends AsyncTask<Integer, Integer, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //在计时开始前，设置按钮和EditText不能用
            chronographValue1.setEnabled(false);
            start1.setEnabled(false);
            //设置初值
            chronographText1.setText("0:0");
        }

        @Override
        protected Void doInBackground(Integer... params) {
            // 计时
            for (int i = 0; i <= params[0]; i++) {
                for (int j = 0; j < 60; j++) {
                    try {
                        // 发布增量
                        publishProgress(i, j);
                        if (i == params[0]) {
                            return null;
                        }
                        // 暂停一秒
                        Thread.sleep(1000);
                        Log.e(TAG, j + "");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (isCancelled()) {
                return null;
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            // 更新UI界面
            chronographText1.setText(values[0] + ":" + values[1]);
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // 设置按钮和EditText可以使用
            chronographValue1.setEnabled(true);
            start1.setEnabled(true);
        }
    }

    //自定义时钟
    private void MyClock(Integer integer) {
        for (int i = 0; i <= integer; i++) {
            try {
                Thread.sleep(1000);
                Log.e(TAG, i + "");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
