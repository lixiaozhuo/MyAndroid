package com.lixiaozhuo.androidcomponent._05_intent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lixiaozhuo.androidcomponent.R;

/**
 * 活动5
 */
public class IntentActivity5 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent5);
        //打开获取参数按钮
        Button buttonGet = findViewById( R.id.getPara);
        //关闭返回参数按钮
        Button buttonSend =  findViewById( R.id.sendPara);
        //显示数据控件
        final TextView textView = findViewById(R.id.textPara) ;
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
                intent.setClass(IntentActivity5.this,IntentActivity1.class);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
