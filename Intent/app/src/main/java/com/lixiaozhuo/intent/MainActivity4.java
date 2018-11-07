package com.lixiaozhuo.intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 活动4
 */
public class MainActivity4 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        //获取控件
        final TextView textView = (TextView) findViewById(R.id.textPara);
        final Button button = (Button) findViewById(R.id.getPara);
        button.setOnClickListener(new View.OnClickListener() {
            //打开传过来的数据
            @Override
            public void onClick(View v) {
                //接收多个参数
                Bundle bundle = getIntent().getExtras();
                String university = bundle.getString("University");
                String college = bundle.getString("College");
                textView.setText(university + ":" + college);
                //接收单个参数
                // Intent intent=getIntent();
                //String string=intent.getStringExtra("data");
                // textView.setText(string);
            }
        });
    }
}
