package com.lixiaozhuo.androidcomponent.save_data;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lixiaozhuo.androidcomponent.R;
import com.lixiaozhuo.androidcomponent.save_data.file.FileActivity;
import com.lixiaozhuo.androidcomponent.save_data.shared_preferences.SharedPreferencesActivity;
import com.lixiaozhuo.androidcomponent.save_data.sqlite.SQLiteActivity;
import com.lixiaozhuo.androidcomponent.view.dialog.DialogActivity;
import com.lixiaozhuo.androidcomponent.view.listview.ListViewActivity;
import com.lixiaozhuo.androidcomponent.view.login.LoginActivity;
import com.lixiaozhuo.androidcomponent.view.menu.MenuActivity;
import com.lixiaozhuo.androidcomponent.view.notification.NotificationActivity;
import com.lixiaozhuo.androidcomponent.view.spinner.SpinnerActivity;
import com.lixiaozhuo.androidcomponent.view.toast.ToastActivity;

/**
 * 数据存储主函数
 */
public class SaveDataActivity extends Activity {

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
        Intent intent = new Intent(SaveDataActivity.this, SharedPreferencesActivity.class);
        startActivity(intent);
    }

    /**
     * SQLite
     * @param v
     */
    public void sqLiteTest(View v){
        Intent intent = new Intent(SaveDataActivity.this, SQLiteActivity.class);
        startActivity(intent);
    }

    /**
     *文件存储
     * @param v
     */
    public void fileTest(View v){
        Intent intent = new Intent(SaveDataActivity.this, FileActivity.class);
        startActivity(intent);
    }
}
