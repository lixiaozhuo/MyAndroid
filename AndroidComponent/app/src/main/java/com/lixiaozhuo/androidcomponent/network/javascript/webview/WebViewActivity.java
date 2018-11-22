package com.lixiaozhuo.androidcomponent.network.javascript.webview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.lixiaozhuo.androidcomponent.R;

/**
 * 显示web视图
 */
public class WebViewActivity extends Activity {
    //web视图
    private WebView mWebView;
    //提示开始加载
    private TextView beginLoading;
    //结束加载提示
    private TextView endLoading;
    //加载中提示
    private TextView loading;
    //网页标题
    private TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        //初始化控件
        mWebView = findViewById(R.id.webView1);
        beginLoading = findViewById(R.id.text_beginLoading);
        endLoading = findViewById(R.id.text_endLoading);
        loading = findViewById(R.id.text_Loading);
        mTitle = findViewById(R.id.title);

        mWebView.setWebChromeClient(new WebChromeClient() {
            /**
             * 获取网站标题
             * @param view
             * @param title
             */
            @Override
            public void onReceivedTitle(WebView view, String title) {
                mTitle.setText("网页标题：" + title);
            }

            /**
             * 加载进度改变
             * @param view
             * @param newProgress
             */
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                String progress = newProgress + "%";
                loading.setText("加载进度:" + progress);
            }
        });

        mWebView.setWebViewClient(new WebViewClient() {
            /**
             * 加载前
             * @param view
             * @param url
             * @param favicon
             */
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {//设置
                beginLoading.setText("开始加载!!!");
            }

            /**
             * 结束加载
             * @param view
             * @param url
             */
            @Override
            public void onPageFinished(WebView view, String url) {
                endLoading.setText("结束加载!!!");
            }
        });
    }

    /**
     * 直接显示
     *
     * @param view
     */
    public void loadUrl(View view) {
        try {
            mWebView.loadUrl("https://www.baidu.com/");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 中文显示
     *
     * @param view
     */
    public void loadData(View view) {
        try {
            String data = "<html><title>河北大学</title>" + "<body>" + "网络空间安全与计算机学院" + "</body>" + "</html>";
            mWebView.loadData(data, "text/html;charset=UTF-8", null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 本地网页
     *
     * @param view
     */
    public void loadLocal(View view) {
        try {
            mWebView.loadUrl("file:///android_asset/test.html");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 图片混排
     *
     * @param view
     */
    public void loadImage(View view) {
        try {
            String data = "测试本地图文混排。这是APK中的图片:" + "<IMG src= file:///android_asset/icon.jpg>" + "图片路径：src= file:///android_asset/icon.jpg";
            //显示网页
            mWebView.loadDataWithBaseURL("about:blank", data, "text/html;charset=UTF-8", null, "");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 点击时设定返回上一页面
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            //返回上一页
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 销毁Web视图
     */
    @Override
    protected void onDestroy() {
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            //清除历史记录
            mWebView.clearHistory();
            //从父控件中删除其
            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }
}
