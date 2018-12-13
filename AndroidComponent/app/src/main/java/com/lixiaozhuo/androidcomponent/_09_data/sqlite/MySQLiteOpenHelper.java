package com.lixiaozhuo.androidcomponent._09_data.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 *自定义帮助器(管理数据库的创建和版本的管理)
 */
public class MySQLiteOpenHelper extends android.database.sqlite.SQLiteOpenHelper {
    private final static String TAG = "App:SQLite";
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
        Log.e(TAG, "数据库创建成功");
        //新建学生表
        db.execSQL("CREATE TABLE student(_id INTEGER PRIMARY KEY AUTOINCREMENT, name CHAR(10), age INTEGER, sno INTEGER, cpp FLOAT, math FLOAT, english FLOAT)");
    }

    //升级数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e(TAG, "数据库升级成功");
    }
}
