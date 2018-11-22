package com.lixiaozhuo.androidcomponent.network.data_parser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lixiaozhuo.androidcomponent.R;
import com.lixiaozhuo.androidcomponent.network.data_parser.json.JsonActivity;
import com.lixiaozhuo.androidcomponent.network.data_parser.login.NetworkLoginActivity;
import com.lixiaozhuo.androidcomponent.network.data_parser.sax.SaxActivity;
import com.lixiaozhuo.androidcomponent.network.network_operate.NetworkOperatorActivity;

/**
 * 数据解析
 */
public class DataParserActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_parser);
    }

    /**
     * JSON解析
     * @param v
     */
    public void jsonTest(View v){
        Intent intent = new Intent(DataParserActivity.this, JsonActivity.class);
        startActivity(intent);
    }

    /**
     * SAX解析
     * @param v
     */
    public void saxTest(View v){
        Intent intent = new Intent(DataParserActivity.this, SaxActivity.class);
        startActivity(intent);
    }

    /**
     * 远程登录
     * @param v
     */
    public void loginTest(View v){
        Intent intent = new Intent(DataParserActivity.this, NetworkLoginActivity.class);
        startActivity(intent);
    }

}
