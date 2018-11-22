package com.lixiaozhuo.androidcomponent.network;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lixiaozhuo.androidcomponent.R;
import com.lixiaozhuo.androidcomponent.network.data_parser.DataParserActivity;
import com.lixiaozhuo.androidcomponent.network.javascript.JavaScriptActivity;
import com.lixiaozhuo.androidcomponent.network.network_operate.NetworkOperatorActivity;

/**
 * 网络主函数
 */
public class NetworkActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.network);
    }

    /**
     * 网络信息
     * @param v
     */
    public void httpTest(View v){
        Intent intent = new Intent(NetworkActivity.this, NetworkOperatorActivity.class);
        startActivity(intent);
    }

    /**
     * 数据解析
     * @param v
     */
    public void dataParserTest(View v){
        Intent intent = new Intent(NetworkActivity.this, DataParserActivity.class);
        startActivity(intent);
    }

    /**
     * js
     * @param v
     */
    public void jsTest(View v){
        Intent intent = new Intent(NetworkActivity.this, JavaScriptActivity.class);
        startActivity(intent);
    }

}
