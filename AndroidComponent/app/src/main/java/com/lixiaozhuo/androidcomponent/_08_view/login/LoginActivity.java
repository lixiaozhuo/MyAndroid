package com.lixiaozhuo.androidcomponent._08_view.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.lixiaozhuo.androidcomponent.R;

/**
 * 登录界面
 */
public class LoginActivity extends Activity {
    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }

    public void login(View v) {
        Toast.makeText(this, "登录功能内测中", Toast.LENGTH_SHORT).show();
    }

    public void register(View v) {
        Toast.makeText(this, "注册功能内测中", Toast.LENGTH_SHORT).show();
    }

    public void changerPhoto(View v) {
        ImageView myImage = findViewById(R.id.myImage);
        if (flag == true) {
            myImage.setImageResource(R.drawable.tiger);
        } else {
            myImage.setImageResource(R.drawable.lion);
        }
        flag = !flag;
    }
}
