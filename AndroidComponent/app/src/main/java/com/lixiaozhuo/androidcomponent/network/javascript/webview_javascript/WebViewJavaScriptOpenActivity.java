package com.lixiaozhuo.androidcomponent.network.javascript.webview_javascript;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lixiaozhuo.androidcomponent.MainActivity;
import com.lixiaozhuo.androidcomponent.R;

/**
 * JavaScript打开的界面
 */
public class WebViewJavaScriptOpenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_javascript_open);
    }
    public void backToMain(View view){
        //返回到原来界面
        startActivity(new Intent(this,WebViewJavaScriptActivity.class));
    }
}
