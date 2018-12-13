package com.lixiaozhuo.androidcomponent._09_data;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lixiaozhuo.androidcomponent.R;
import com.lixiaozhuo.androidcomponent._09_data.file.FileActivity;
import com.lixiaozhuo.androidcomponent._09_data.shared_preferences.SharedPreferencesActivity;
import com.lixiaozhuo.androidcomponent._09_data.sqlite.SQLiteActivity;

/**
 * 数据存储主函数
 */
public class DataActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_data);
    }

    /**
     * SharedPreferences
     * @param v
     */
    public void sharedPreferencesTest(View v){
        Intent intent = new Intent(DataActivity.this, SharedPreferencesActivity.class);
        startActivity(intent);
    }

    /**
     * SQLite
     * @param v
     */
    public void sqLiteTest(View v){
        Intent intent = new Intent(DataActivity.this, SQLiteActivity.class);
        startActivity(intent);
    }

    /**
     *文件存储
     * @param v
     */
    public void fileTest(View v){
        Intent intent = new Intent(DataActivity.this, FileActivity.class);
        startActivity(intent);
    }
}
