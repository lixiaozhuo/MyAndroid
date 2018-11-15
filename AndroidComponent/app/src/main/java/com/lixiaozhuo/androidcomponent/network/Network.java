package com.lixiaozhuo.androidcomponent.network;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lixiaozhuo.androidcomponent.R;
import com.lixiaozhuo.androidcomponent.view.login.LoginActivity;
import com.lixiaozhuo.androidcomponent.view.menu.MenuActivity;

/**
 * 网络主函数
 */
public class Network extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.network);
    }

    /**
     *
     * @param v
     */
    public void myTest(View v){
        Intent intent = new Intent(Network.this, Network.class);
        startActivity(intent);
    }

}
