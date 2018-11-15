package com.lixiaozhuo.androidcomponent.save_data.shared_preferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lixiaozhuo.androidcomponent.MainActivity;
import com.lixiaozhuo.androidcomponent.R;

//数据存储方式1:SharedPreferences
public class SharedPreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shared_preferences);
        //获取控件并绑定事件
        Button saveData = findViewById(R.id.saveData);
        Button getSaveData = findViewById(R.id.getSaveData);
        Button clearData = findViewById(R.id.clearData);
        final EditText userName = findViewById(R.id.userName);
        final EditText password = findViewById(R.id.password);
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //创建名为“data”的文件，加入键值对，保存数据
                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.putString("name",userName.getText().toString());
                editor.putString("password",password.getText().toString());
                //提交数据
                editor.commit();
            }
        });
        getSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //取出文件中键值对，并提示出来
                SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
                String name = preferences.getString("name","");
                String password = preferences.getString("password","");
                //显示数据
                Toast.makeText(SharedPreferencesActivity.this,"UserName:"+name+"\nPassWord:"+password,Toast.LENGTH_LONG).show();
            }
        });

        clearData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //清空数据
                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.clear();
                //提交操作
                editor.commit();
            }
        });

    }
}
