package com.lixiaozhuo.androidcomponent.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lixiaozhuo.androidcomponent.R;
import com.lixiaozhuo.androidcomponent.view.dialog.DialogActivity;
import com.lixiaozhuo.androidcomponent.view.listview.ListViewActivity;
import com.lixiaozhuo.androidcomponent.view.login.LoginActivity;
import com.lixiaozhuo.androidcomponent.view.menu.MenuActivity;
import com.lixiaozhuo.androidcomponent.view.notification.NotificationActivity;
import com.lixiaozhuo.androidcomponent.view.spinner.SpinnerActivity;
import com.lixiaozhuo.androidcomponent.view.toast.ToastActivity;

/**
 * 控件主函数
 */
public class ViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view);
    }

    /**
     * 登录
     * @param v
     */
    public void loginTest(View v){
        Intent intent = new Intent(ViewActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    /**
     * 菜单
     * @param v
     */
    public void menuTest(View v){
        Intent intent = new Intent(ViewActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    /**
     * 下拉框
     * @param v
     */
    public void spinnerTest(View v){
        Intent intent = new Intent(ViewActivity.this, SpinnerActivity.class);
        startActivity(intent);
    }

    /**
     * Toast
     * @param v
     */
    public void toastTest(View v){
        Intent intent = new Intent(ViewActivity.this, ToastActivity.class);
        startActivity(intent);
    }

    /**
     * 弹窗
     * @param v
     */
    public void dialogTest(View v){
        Intent intent = new Intent(ViewActivity.this, DialogActivity.class);
        startActivity(intent);
    }

    /**
     * 列表
     * @param v
     */
    public void listViewTest(View v){
        Intent intent = new Intent(ViewActivity.this, ListViewActivity.class);
        startActivity(intent);
    }

    /**
     * 通知
     * @param v
     */
    public void notificationTest(View v){
        Intent intent = new Intent(ViewActivity.this, NotificationActivity.class);
        startActivity(intent);
    }


}
