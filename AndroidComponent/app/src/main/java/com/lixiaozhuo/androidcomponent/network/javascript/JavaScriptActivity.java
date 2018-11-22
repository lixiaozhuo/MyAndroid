package com.lixiaozhuo.androidcomponent.network.javascript;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lixiaozhuo.androidcomponent.R;
import com.lixiaozhuo.androidcomponent.network.javascript.webview.WebViewActivity;
import com.lixiaozhuo.androidcomponent.network.javascript.webview_javascript.WebViewJavaScriptActivity;
import com.lixiaozhuo.androidcomponent.network.javascript.webview_javascript.WebViewJavaScriptOpenActivity;
import com.lixiaozhuo.androidcomponent.network.network_operate.NetworkOperatorActivity;

/**
 *JavaScript
 */
public class JavaScriptActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.javascript);
    }

    /**
     *
     * @param v
     */
    public void webViewTest(View v){
        Intent intent = new Intent(JavaScriptActivity.this, WebViewActivity.class);
        startActivity(intent);
    }

    /**
     *
     * @param v
     */
    public void webViewJavaScriptTest(View v){
        Intent intent = new Intent(JavaScriptActivity.this, WebViewJavaScriptActivity.class);
        startActivity(intent);
    }


}
