package com.lixiaozhuo.androidcomponent.thread.asynctask.asynctask2;

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
    //
    private EditText chronoValue1;
    //
    private TextView chronoText1;
    //
    private Button start1;
    //
    private EditText chronoValue2;
    //
    private TextView chronoText2;
    //
    private Button start2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asynctask2);

        //获取控件并绑定事件
        start1 = findViewById(R.id.start);
        chronoText1 = findViewById(R.id.chronoText);
        chronoValue1 = findViewById(R.id.chronoValue);
        start2 = findViewById(R.id.start1);
        chronoText2 = findViewById(R.id.chronoText1);
        chronoValue2 = findViewById(R.id.chronoValue1);

        start1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取EditText里的数值
                int value = Integer.parseInt(String.valueOf(chronoValue1.getText()));
                // 验证数值是否大于零
                if (value > 0) {
                    new Chronograph().execute(value);
                } else {
                    Toast.makeText(AsyncTask2Activity.this, "请输入一个大于零的整数值 !", Toast.LENGTH_LONG).show();
                }
            }
        });

        start2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取EditText里的数值
                int value = Integer.parseInt(String.valueOf(chronoValue2.getText()));
                // 验证数值是否大于零
                if (value > 0) {
                    // 发布增量
                    chronoText2.setText("等待了：" + value + "秒");
                    MyClock(value);
                } else {
                    Toast.makeText(AsyncTask2Activity.this, "请输入一个大于零的整数值 !", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private class Chronograph extends AsyncTask<Integer, Integer, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //在计时开始前，设置按钮和EditText不能用
            chronoValue1.setEnabled(false);
            start1.setEnabled(false);
            //设置初值
            chronoText1.setText("0:0");
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
            chronoText1.setText(values[0] + ":" + values[1]);
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // 设置按钮和EditText可以使用
            chronoValue1.setEnabled(true);
            start1.setEnabled(true);
        }
    }

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
