package com.lixiaozhuo.androidcomponent.view.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lixiaozhuo.androidcomponent.R;

/**
 * Activity跳转
 */
public class RadiobuttonActivity extends AppCompatActivity {
    /**
     * 显示信息
     */
    private TextView textView;
    /**
     * 中国
     */
    private RadioButton chinaBtn;
    /**
     * 英国
     */
    private RadioButton ukBtn;
    /**
     * 美国
     */
    private RadioButton usaBtn;
    /**
     * 德国
     */
    private RadioButton gerBtn;
    /**
     * 法国
     */
    private RadioButton fraBtn;
    /**
     *
     */
    private RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_radiobutton);

        //获取TextView
        textView =findViewById(R.id.myTextView);
        //获取RadioButton
        chinaBtn = findViewById(R.id.china_Button);
        ukBtn = findViewById(R.id.uk_Button);
        usaBtn =findViewById(R.id.usa_Button);
        gerBtn= findViewById(R.id.ger_Button);
        fraBtn= findViewById(R.id.fra_Button);
        //通过ID找到RadioGroup
        rg = findViewById(R.id.rBtnGroup);
        //只要对RadioGroup进行监听
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(R.id.china_Button == checkedId){
                    textView.setText("您选择的国家是：" + chinaBtn.getText().toString());
                }
                else if(R.id.uk_Button == checkedId){
                    textView.setText("您选择的国家是：" + ukBtn.getText().toString());
                }
                else if(R.id.usa_Button == checkedId){
                    textView.setText("您选择的国家是：" + usaBtn.getText().toString());
                }
                else if(R.id.ger_Button == checkedId){
                    textView.setText("您选择的国家是：" + gerBtn.getText().toString());
                }
                else if(R.id.fra_Button == checkedId){
                    textView.setText("您选择的国家是：" + fraBtn.getText().toString());
                }
            }
        });
    }
    public void onOK(View v){
        Intent intent = new Intent(RadiobuttonActivity.this,DialogActivity.class);
        intent.putExtra("name", textView.getText());
        startActivity(intent);
    }
}
