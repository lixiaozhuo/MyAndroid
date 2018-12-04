package com.lixiaozhuo.androidcomponent.save_data.file;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lixiaozhuo.androidcomponent.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * 文件存储
 */
public class FileActivity extends Activity {
    //编辑控件
    private EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_file);
        //获取控件
        edit = findViewById(R.id.edit);
        //读取文件
        String inputText = load();
        if (!TextUtils.isEmpty(inputText)) {
            //显示数据
            edit.setText(inputText);
            edit.setSelection(inputText.length());
            Toast.makeText(this, "读取成功", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 保存文件
     * @param view
     */
    public void ButtonSave(View view){
        //保存数据
        save(edit.getText().toString());
    }

    /**
     * 读取文件
     * @param view
     */
    public void ButtonLoad(View view){
        //读取文件
        String inputText = load();
        if (!TextUtils.isEmpty(inputText)) {
            //显示文件内容
            edit.setText(inputText);
            edit.setSelection(inputText.length());
            Toast.makeText(this, "读取成功", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 保存指定数据
     * @param inputText
     */
    public void save(String inputText) {
        //输出流
        BufferedWriter writer = null;
        try {
            //初始化流
            writer = new BufferedWriter(new OutputStreamWriter(openFileOutput("saveFileTest", Context.MODE_PRIVATE)));
            writer.write(inputText);
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读文件
     * @return
     */
    public String load() {
        //输入流
        BufferedReader reader = null;
        //存储数据
        StringBuilder content = new StringBuilder();
        try {
            //获取流
            reader = new BufferedReader(new InputStreamReader(openFileInput("saveFileTest")));
            String line = "";
            while ((line = reader.readLine()) != null) {
                //将读取的数据存放到容器中
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }

}
