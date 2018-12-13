package com.lixiaozhuo.androidcomponent._09_data.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 学生数据库管理器:帮助器
 */
public class SQLiteApplicationHelper extends SQLiteOpenHelper {
    /**
     * @param context 上下文
     * @param name    数据库文件的名字
     * @param factory 游标工厂(结果集)
     * @param version 数据库的版本号(用于升级)
     */
    public SQLiteApplicationHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //创建数据库
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_STUDENT = "CREATE TABLE " + SQLiteApplicationStudent.TABLE + "("
                + SQLiteApplicationStudent.KEY_ID + " INTEGER PRIMARY KEY ,"
                + SQLiteApplicationStudent.KEY_name + " TEXT, "
                + SQLiteApplicationStudent.KEY_age + " INTEGER )";
        //创建一个学生表
        db.execSQL(CREATE_TABLE_STUDENT);
    }

    /**
     * 升级数据库
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SQLiteApplicationStudent.TABLE);
        onCreate(db);
    }

    /**
     * 初始化数据
     * @param db
     */
    public void onInit(SQLiteDatabase db) {
        db.execSQL("delete from " + SQLiteApplicationStudent.TABLE);
        db.execSQL("insert into " + SQLiteApplicationStudent.TABLE + "(" + SQLiteApplicationStudent.KEY_ID + "," + SQLiteApplicationStudent.KEY_name + "," + SQLiteApplicationStudent.KEY_age + ")" + " values(?, ?, ?)", new Object[]{101, "刘得意", 19});
        db.execSQL("insert into " + SQLiteApplicationStudent.TABLE + "(" + SQLiteApplicationStudent.KEY_ID + "," + SQLiteApplicationStudent.KEY_name + "," + SQLiteApplicationStudent.KEY_age + ")" + " values(?, ?, ?)", new Object[]{102, "王锐", 20});
        db.execSQL("insert into " + SQLiteApplicationStudent.TABLE + "(" + SQLiteApplicationStudent.KEY_ID + "," + SQLiteApplicationStudent.KEY_name + "," + SQLiteApplicationStudent.KEY_age + ")" + " values(?, ?, ?)", new Object[]{103, "何煜中", 19});
        db.execSQL("insert into " + SQLiteApplicationStudent.TABLE + "(" + SQLiteApplicationStudent.KEY_ID + "," + SQLiteApplicationStudent.KEY_name + "," + SQLiteApplicationStudent.KEY_age + ")" + " values(?, ?, ?)", new Object[]{104, "王磊", 21});
        db.execSQL("insert into " + SQLiteApplicationStudent.TABLE + "(" + SQLiteApplicationStudent.KEY_ID + "," + SQLiteApplicationStudent.KEY_name + "," + SQLiteApplicationStudent.KEY_age + ")" + " values(?, ?, ?)", new Object[]{105, "冯松", 19});
        db.execSQL("insert into " + SQLiteApplicationStudent.TABLE + "(" + SQLiteApplicationStudent.KEY_ID + "," + SQLiteApplicationStudent.KEY_name + "," + SQLiteApplicationStudent.KEY_age + ")" + " values(?, ?, ?)", new Object[]{106, "得意刘", 19});
        db.execSQL("insert into " + SQLiteApplicationStudent.TABLE + "(" + SQLiteApplicationStudent.KEY_ID + "," + SQLiteApplicationStudent.KEY_name + "," + SQLiteApplicationStudent.KEY_age + ")" + " values(?, ?, ?)", new Object[]{107, "锐王", 20});
        db.execSQL("insert into " + SQLiteApplicationStudent.TABLE + "(" + SQLiteApplicationStudent.KEY_ID + "," + SQLiteApplicationStudent.KEY_name + "," + SQLiteApplicationStudent.KEY_age + ")" + " values(?, ?, ?)", new Object[]{108, "煜中何", 19});
        db.execSQL("insert into " + SQLiteApplicationStudent.TABLE + "(" + SQLiteApplicationStudent.KEY_ID + "," + SQLiteApplicationStudent.KEY_name + "," + SQLiteApplicationStudent.KEY_age + ")" + " values(?, ?, ?)", new Object[]{109, "磊王", 21});
        db.execSQL("insert into " + SQLiteApplicationStudent.TABLE + "(" + SQLiteApplicationStudent.KEY_ID + "," + SQLiteApplicationStudent.KEY_name + "," + SQLiteApplicationStudent.KEY_age + ")" + " values(?, ?, ?)", new Object[]{110, "松冯", 19});

    }

    /**
     * 查询全部数据
     * @param db
     * @return
     */
    public Cursor onList(SQLiteDatabase db) {
        String selectQuery = "SELECT  " + SQLiteApplicationStudent.KEY_ID + "," + SQLiteApplicationStudent.KEY_name + "," + SQLiteApplicationStudent.KEY_age +
                " FROM " + SQLiteApplicationStudent.TABLE;
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }

    /**
     * 查询学生信息
     * @param db
     * @param id
     * @return
     */
    public Cursor onQuery(SQLiteDatabase db, int id) {
        Cursor cursor;
        String selectQuery = "SELECT  " + SQLiteApplicationStudent.KEY_ID + "," + SQLiteApplicationStudent.KEY_name + "," + SQLiteApplicationStudent.KEY_age +
                " FROM " + SQLiteApplicationStudent.TABLE + " where " + SQLiteApplicationStudent.KEY_ID + " = " + id;
        cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }

    /**
     * 插入学生信息
     * @param db
     * @param id
     * @param name
     * @param age
     */
    public void onInsert(SQLiteDatabase db, int id, String name, int age) {
        db.execSQL("insert into " + SQLiteApplicationStudent.TABLE + "(" + SQLiteApplicationStudent.KEY_ID + "," + SQLiteApplicationStudent.KEY_name + "," + SQLiteApplicationStudent.KEY_age + ")" +
                " values(" + id + "," + name + "," + age + ")");
    }

    /**
     * 删除指定id学生信息
     * @param db
     * @param id
     */
    public void onDelete(SQLiteDatabase db, int id) {
        db.execSQL("delete from " + SQLiteApplicationStudent.TABLE + " where " + SQLiteApplicationStudent.KEY_ID + " = " + id);
    }

    /**
     * 更新学生信息
     * @param db
     * @param id
     * @param name
     * @param age
     */
    public void onUpdate(SQLiteDatabase db, int id, String name, int age) {
        db.execSQL(" update " + SQLiteApplicationStudent.TABLE + " " +
                " set " + SQLiteApplicationStudent.KEY_ID + " = " + id + "," + SQLiteApplicationStudent.KEY_name + " = '" + name + "'," + SQLiteApplicationStudent.KEY_age + " = " + age +
                " where " + SQLiteApplicationStudent.KEY_ID + " = " + id);
    }

}