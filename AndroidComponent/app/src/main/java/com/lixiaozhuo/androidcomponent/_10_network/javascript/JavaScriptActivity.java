package com.lixiaozhuo.androidcomponent._10_network.javascript;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lixiaozhuo.androidcomponent.R;
import com.lixiaozhuo.androidcomponent._10_network.javascript.webview.WebViewActivity;
import com.lixiaozhuo.androidcomponent._10_network.javascript.webview_javascript.WebViewJavaScriptActivity;

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
