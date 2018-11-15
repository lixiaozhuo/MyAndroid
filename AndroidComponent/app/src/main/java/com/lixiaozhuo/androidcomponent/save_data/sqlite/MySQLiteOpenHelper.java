package com.lixiaozhuo.androidcomponent.save_data.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 *自定义帮助器
 */
public class MySQLiteOpenHelper extends android.database.sqlite.SQLiteOpenHelper {
    /**
     *
     * @param context 上下文
     * @param name 数据库文件的名字
     * @param factory 游标工厂(结果集)
     * @param version 数据库的版本号(用于升级)
     */
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //创建数据库
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("MainActivity", "数据库创建成功");
        //创建一个学生表
        db.execSQL("create table student(_id integer primary key autoincrement, name char(10), age integer, sno integer, cpp float, math float, english float)");
    }

    //升级数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("MainActivity", "数据库升级成功");
    }
}
