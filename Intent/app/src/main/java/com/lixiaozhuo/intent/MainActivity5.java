package com.lixiaozhuo.intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * 活动5
 */
public class MainActivity5 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        //获取控件
        Button buttonGet = (Button) findViewById( R.id.getPara);
        Button buttonSend = (Button) findViewById( R.id.sendPara);
        final TextView textView = (TextView)findViewById(R.id.textPara) ;
        //打开传过来的数据
        buttonGet.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Bundle bundle = getIntent().getExtras();
                String para = bundle.getString("参数1-2");
                textView.setText(para);

            }
        });
        //关闭并返回数据
        buttonSend.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("参数2-1","参数值2-1");
                intent.putExtras(bundle);
                intent.setClass(MainActivity5.this,MainActivity.class);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}