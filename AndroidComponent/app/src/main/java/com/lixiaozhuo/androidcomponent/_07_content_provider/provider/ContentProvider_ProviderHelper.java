package com.lixiaozhuo.androidcomponent._07_content_provider.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 提供数据者帮助器
 */
public class ContentProvider_ProviderHelper extends SQLiteOpenHelper {
    /**
     * 创建图书SQL
     */
    public static final String CREATE_BOOK = "create table Book ("
            + "id integer primary key autoincrement, "
            + "author text, "
            + "price real, "
            + "pages integer, "
            + "name text)";
    /**
     *创建分类SQL
     */
    public static final String CREATE_CATEGORY = "create table Category ("
            + "id integer primary key autoincrement, "
            + "category_name text, "
            + "category_code integer)";

    public ContentProvider_ProviderHelper(Context context, String name,
                                          SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建图书表
        db.execSQL(CREATE_BOOK);
        //创建分类表
        db.execSQL(CREATE_CATEGORY);
    }

    /**
     * 数据库升级
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists Category");
        onCreate(db);
    }

}
