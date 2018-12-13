package com.lixiaozhuo.androidcomponent._07_content_provider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lixiaozhuo.androidcomponent.R;
import com.lixiaozhuo.androidcomponent._07_content_provider.provider.ContentProvider_ProviderActivity;
import com.lixiaozhuo.androidcomponent._07_content_provider.system_book.ContentProvider_SystemBookActivity;
import com.lixiaozhuo.androidcomponent._07_content_provider.user.ContentProvider_UserActivity;

/**
 * 数据提供者
 */
public class ContentProviderActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_provider);
    }

    /**
     * 数据提供者
     * @param v
     */
    public void providerTest(View v){
        Intent intent = new Intent(ContentProviderActivity.this, ContentProvider_ProviderActivity.class);
        startActivity(intent);
    }

    /**
     * 使用数据者
     * @param v
     */
    public void userTest(View v){
        Intent intent = new Intent(ContentProviderActivity.this, ContentProvider_UserActivity.class);
        startActivity(intent);
    }

    /**
     * 读取系统电话本
     * @param v
     */
    public void systemBookTest(View v){
        Intent intent = new Intent(ContentProviderActivity.this, ContentProvider_SystemBookActivity.class);
        startActivity(intent);
    }
}
