package com.lixiaozhuo.androidcomponent._10_network.javascript.webview_javascript;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.lixiaozhuo.androidcomponent.R;

/**
 * WebView(JS版本)
 */
public class WebViewJavaScriptActivity extends Activity {
    /**
     * web视图
     */
    private WebView webView;
    /**
     * 数据适配器
     */
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_javascript);
        //获取控件
        webView = findViewById(R.id.webView);
        //设置启动js
        webView.getSettings().setJavaScriptEnabled(true);
        // 从assets目录下面的加载html
        webView.loadUrl("file:///android_asset/WebViewJSTest.html");
        //添加js接口
        webView.addJavascriptInterface(WebViewJavaScriptActivity.this, "android");

        //调用JS
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 无参数调用js
                webView.loadUrl("javascript:javaCallJs()");
            }
        });
        //调用有参JS
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 传递参数调用js
                webView.loadUrl("javascript:javaCallJsWith(" + "'我是Java的参数'" + ")");
            }
        });
    }

    //由于安全原因 需要加 @JavascriptInterface
    @JavascriptInterface
    public void startFunction() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(WebViewJavaScriptActivity.this, "我来自JAVA", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @JavascriptInterface
    public void startFunction(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new AlertDialog.Builder(WebViewJavaScriptActivity.this).setMessage(text).show();
            }
        });
    }

    @JavascriptInterface
    public void callAndroid() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                //按确定键跳转到下一个Activity
                startActivity(new Intent(WebViewJavaScriptActivity.this, WebViewJavaScriptOpenActivity.class));
            }
        });
    }
}