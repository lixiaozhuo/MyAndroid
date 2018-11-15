package com.lixiaozhuo.androidcomponent.content_provider.user;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lixiaozhuo.androidcomponent.R;

/**
 * 使用其他程序提供的数据
 */
public class ContentProvider_UserActivity extends AppCompatActivity {
    //编号
    private String newId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_provider_user);
        //获取控件并绑定事件
        //添加数据
        Button addData =  findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                Uri uri = Uri.parse("content://com.example.databasetest.provider/book");
                //存储数据
                ContentValues values = new ContentValues();
                values.put("name", "Java并发编程实战");
                values.put("author", "张");
                values.put("pages", 1000);
                values.put("price", 55.55);
                //
                Uri newUri = getContentResolver().insert(uri, values);
                newId = newUri.getPathSegments().get(1);
            }
        });
        //查询数据
        Button queryData = (Button) findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                Uri uri = Uri.parse("content://com.example.databasetest.provider/book");
                //查询数据获取游标
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        //获取数据
                        String name = cursor.getString(cursor. getColumnIndex("name"));
                        String author = cursor.getString(cursor. getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex ("pages"));
                        double price = cursor.getDouble(cursor. getColumnIndex("price"));
                        //显示数据
                        Log.d("MainActivity", "Name = " + name);
                        Log.d("MainActivity", "Author = " + author);
                        Log.d("MainActivity", "Pages = " + pages);
                        Log.d("MainActivity", "Price = " + price);
                    }
                    cursor.close();
                }
            }
        });
        // 更新数据
        Button updateData = (Button) findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                Uri uri = Uri.parse("content://com.example.databasetest.provider/book/" + newId);
                //存储数据
                ContentValues values = new ContentValues();
                values.put("name", "Java并发编程实战");
                values.put("pages", 1216);
                values.put("price", 66.66);
                //更新数据
                getContentResolver().update(uri, values, null, null);
            }
        });
        //删除数据
        Button deleteData = (Button) findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                Uri uri = Uri.parse("content://com.example.databasetest.provider/book/" + newId);
                // 删除数据
                getContentResolver().delete(uri, null, null);
            }
        });
    }

}
