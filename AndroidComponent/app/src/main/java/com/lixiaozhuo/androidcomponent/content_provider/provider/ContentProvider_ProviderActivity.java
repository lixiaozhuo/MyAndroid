package com.lixiaozhuo.androidcomponent.content_provider.provider;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lixiaozhuo.androidcomponent.R;

/**
 * 数据提供活动
 */
public class ContentProvider_ProviderActivity extends AppCompatActivity {
    //数据库帮助器
    private ContentProvider_ProviderHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_provider_provider);
        //实例化帮助器
        dbHelper = new ContentProvider_ProviderHelper(this, "BookStore.db", null, 2);
        //获取控件并绑定事件
        //创建数据库
        Button createDatabase = findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.getWritableDatabase();
            }
        });
        //添加数据
        Button addData = findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取数据库
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                //存储数据
                ContentValues values = new ContentValues();
                // 组装第一条数据
                values.put("name", "Java编程思想");
                values.put("author", "王");
                values.put("pages", 454);
                values.put("price", 16.96);
                //插入第一条数据
                db.insert("Book", null, values);
                //清空ContentValues
                values.clear();
                // 组装第二条数据
                values.put("name", "JVM虚拟机");
                values.put("author", "李");
                values.put("pages", 510);
                values.put("price", 19.95);
                //插入第二条数据
                db.insert("Book", null, values);
            }
        });
        //更新数据库
        Button updateData = findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取数据库
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                //存储数据
                ContentValues values = new ContentValues();
                values.put("price", 10.99);
                //更新数据库
                db.update("Book", values, "name = ?", new String[] { "Java编程思想" });
            }
        });
        //删除数据库
        Button deleteButton = findViewById(R.id.delete_data);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取数据库
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                //删除数据库
                db.delete("Book", "pages > ?", new String[] { "500" });
            }
        });
        //查询数据库
        Button queryButton =  findViewById(R.id.query_data);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取数据库
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                // 查询所有的数据
                Cursor cursor = db.query("Book", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        //获取数据
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        //显示数据
                        Log.d("MainActivity", "Name = " + name);
                        Log.d("MainActivity", "Author = " + author);
                        Log.d("MainActivity", "Pages = " + pages);
                        Log.d("MainActivity", "Price = " + price);
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        });
    }

}
