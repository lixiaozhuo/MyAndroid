package com.lixiaozhuo.androidcomponent.save_data.sqlite;

/**
 *  学生数据库管理器:学生信息
 */
public class SQLiteApplicationStudent {
    /**
     * 表名
     */
    public static final String TABLE = "student";
    /**
     * id列名
     */
    public static final String KEY_ID = "id";
    /**
     * 姓名列名
     */
    public static final String KEY_name = "name";
    /**
     * 年龄列名
     */
    public static final String KEY_age = "age";
    /**
     * id
     */
    public int student_ID;
    /**
     * 姓名
     */
    public String name;
    /**
     * 年龄
     */
    public int age;
}