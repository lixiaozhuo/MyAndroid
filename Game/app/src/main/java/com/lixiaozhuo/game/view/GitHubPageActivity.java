package com.lixiaozhuo.game.view;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.lixiaozhuo.game.R;

/**
 * github主页
 */
public class GitHubPageActivity extends Activity {
    //浏览器
    private WebView webView;
    //进度条
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.github_page);
        //进度条
        progressBar = findViewById(R.id.progressBar);
        //浏览器
        webView = findViewById(R.id.githubPageWebView);
        //WebSettings webSettings = webView.getSettings();
        //设置WebViewClient，防止请求跳转到系统浏览器
        webView.setWebViewClient(new WebViewClient() {
            //开始加载
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                //设置进度条可见
                progressBar.setVisibility(View.VISIBLE);
            }

            //结束加载
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //设置进度条不可见并且不占用布局空间
                progressBar.setVisibility(View.GONE);
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            //加载进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //更新进度
                progressBar.setProgress(newProgress);
                super.onProgressChanged(view, newProgress);
            }
        });

        //加载网页
        webView.loadUrl("https://github.com/lixiaozhuo/MyAndroid/tree/master/Game/");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            //返回上一页
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
