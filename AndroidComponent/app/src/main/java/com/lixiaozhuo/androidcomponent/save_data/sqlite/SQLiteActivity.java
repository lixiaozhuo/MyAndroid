package com.lixiaozhuo.androidcomponent.save_data.sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lixiaozhuo.androidcomponent.MainActivity;
import com.lixiaozhuo.androidcomponent.R;

/**
 * SQLite
 */
public class SQLiteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite);
    }

    /**
     *创建帮助器
     */
    private MySQLiteOpenHelper oh;
    /**
     *创建数据库对象
     */
    private SQLiteDatabase db;

    /**
     * 创建数据库
     * @param view
     */
    public void createDatabase(View view) {
        //创建帮助器对象
        oh = new MySQLiteOpenHelper(this, "people.db", null, 1);
        //创建数据库对象
        db = oh.getWritableDatabase();
    }

    /**
     * 向数据库中添加数据
     * @param view
     */
    public void Insert(View view) {
        //向学生表中添加10名学生
        db.execSQL("insert into student(name, age, sno, cpp, math, english) values(?, ?, ?, ?, ?, ?)", new Object[]{"刘得意", 19, 1001, 60, 98, 75});
        db.execSQL("insert into student(name, age, sno, cpp, math, english) values(?, ?, ?, ?, ?, ?)", new Object[]{"王锐", 20, 1002, 63, 90, 96});
        db.execSQL("insert into student(name, age, sno, cpp, math, english) values(?, ?, ?, ?, ?, ?)", new Object[]{"何煜中", 19, 1003, 90, 73, 82});
        db.execSQL("insert into student(name, age, sno, cpp, math, english) values(?, ?, ?, ?, ?, ?)", new Object[]{"王磊", 21, 1004, 87, 86, 92});
        db.execSQL("insert into student(name, age, sno, cpp, math, english) values(?, ?, ?, ?, ?, ?)", new Object[]{"冯松", 19, 1005, 89, 98, 83});
        db.execSQL("insert into student(name, age, sno, cpp, math, english) values(?, ?, ?, ?, ?, ?)", new Object[]{"裴培", 20, 1006, 75, 82, 91});
        db.execSQL("insert into student(name, age, sno, cpp, math, english) values(?, ?, ?, ?, ?, ?)", new Object[]{"马骁", 19, 1007, 62, 67, 90});
        db.execSQL("insert into student(name, age, sno, cpp, math, english) values(?, ?, ?, ?, ?, ?)", new Object[]{"马婧", 20, 1008, 98, 84, 87});
        db.execSQL("insert into student(name, age, sno, cpp, math, english) values(?, ?, ?, ?, ?, ?)", new Object[]{"周俊升", 19, 1009, 57, 68, 96});
        db.execSQL("insert into student(name, age, sno, cpp, math, english) values(?, ?, ?, ?, ?, ?)", new Object[]{"贺祺", 21, 1010, 61, 96, 72});
    }

    /**
     * 删除数据库中的数据
     * @param view
     */
    public void Delete(View view) {
        //删除姓名为"刘得意"的学生的信息
        db.execSQL("delete from Student where name = ?", new Object[]{"刘得意"});
    }

    /**
     * 修改数据库中的数据
     * @param view
     */
    public void Update(View view) {
        //将数据库中所有人的学号减少1
        db.execSQL("update student set sno = sno -1");
    }

    /**
     * 清空数据库中的数据
     * @param view
     */
    public void Clear(View view) {
        db.execSQL("delete from student");
    }

    /**
     * 查询数据库中的数据
     * @param view
     */
    public void Select(View view) {
        //查询姓名和C++成绩
        Cursor cursor = db.rawQuery("select name,sno, cpp from student", null);
        while (cursor.moveToNext()) {
            //获得信息
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int no = cursor.getInt(cursor.getColumnIndex("sno"));
            float cpp = cursor.getFloat(cursor.getColumnIndex("cpp"));
            //输出学生的学号、姓名和与姓名对应的C++成绩
            Log.d("MainActivity", "[" + no + ","+ name + ", " + cpp + "]");
        }
        Log.d("MainActivity","================");
    }

    /**
     * 跳转到学生数据库管理器
     */
    public void app(View view){
        Intent intent = new Intent(SQLiteActivity.this,SQLiteApplicationActivity.class);
        startActivity(intent);
    }
}
