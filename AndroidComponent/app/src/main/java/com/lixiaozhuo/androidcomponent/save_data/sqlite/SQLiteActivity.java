package com.lixiaozhuo.androidcomponent.save_data.sqlite;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lixiaozhuo.androidcomponent.MainActivity;
import com.lixiaozhuo.androidcomponent.R;
import com.lixiaozhuo.androidcomponent.save_data.shared_preferences.SharedPreferencesActivity;

/**
 * SQLite
 */
public class SQLiteActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite);
    }

    /**
     *帮助器
     */
    private MySQLiteOpenHelper oh;
    /**
     *数据库管理类
     */
    private SQLiteDatabase db;

    /**
     * 创建数据库
     * @param view
     */
    public void createDatabase(View view) {
        //创建帮助器对象
        oh = new MySQLiteOpenHelper(this, "people.db", null, 1);
        //获取数据库管理对象
        db = oh.getWritableDatabase();
        Toast.makeText(this, "创建数据库成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 向数据库中添加数据
     * @param view
     */
    public void insert(View view) {
        //向学生表中添加10名学生
        db.execSQL("INSERT INTO student(name, age, sno, cpp, math, english) VALUES(?, ?, ?, ?, ?, ?)", new Object[]{"刘得意", 19, 1001, 60, 98, 75});
        db.execSQL("INSERT INTO student(name, age, sno, cpp, math, english) VALUES(?, ?, ?, ?, ?, ?)", new Object[]{"王锐", 20, 1002, 63, 90, 96});
        db.execSQL("INSERT INTO student(name, age, sno, cpp, math, english) VALUES(?, ?, ?, ?, ?, ?)", new Object[]{"何煜中", 19, 1003, 90, 73, 82});
        db.execSQL("INSERT INTO student(name, age, sno, cpp, math, english) VALUES(?, ?, ?, ?, ?, ?)", new Object[]{"王磊", 21, 1004, 87, 86, 92});
        db.execSQL("INSERT INTO student(name, age, sno, cpp, math, english) VALUES(?, ?, ?, ?, ?, ?)", new Object[]{"冯松", 19, 1005, 89, 98, 83});
        db.execSQL("INSERT INTO student(name, age, sno, cpp, math, english) VALUES(?, ?, ?, ?, ?, ?)", new Object[]{"裴培", 20, 1006, 75, 82, 91});
        db.execSQL("INSERT INTO student(name, age, sno, cpp, math, english) VALUES(?, ?, ?, ?, ?, ?)", new Object[]{"马骁", 19, 1007, 62, 67, 90});
        db.execSQL("INSERT INTO student(name, age, sno, cpp, math, english) VALUES(?, ?, ?, ?, ?, ?)", new Object[]{"马婧", 20, 1008, 98, 84, 87});
        db.execSQL("INSERT INTO student(name, age, sno, cpp, math, english) VALUES(?, ?, ?, ?, ?, ?)", new Object[]{"周俊升", 19, 1009, 57, 68, 96});
        db.execSQL("INSERT INTO student(name, age, sno, cpp, math, english) VALUES(?, ?, ?, ?, ?, ?)", new Object[]{"贺祺", 21, 1010, 61, 96, 72});
        Toast.makeText(this, "添加数据成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 删除数据库中的数据
     * @param view
     */
    public void delete(View view) {
        //删除姓名为"刘得意"的学生的信息
        db.execSQL("DELETE FROM Student WHERE name = ?", new Object[]{"刘得意"});
        Toast.makeText(this, "删除数据成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 修改数据库中的数据
     * @param view
     */
    public void update(View view) {
        //将数据库中所有人的学号减少1
        db.execSQL("UPDATE student SET sno = sno -1");
        Toast.makeText(this, "修改数据成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 清空数据库中的数据
     * @param view
     */
    public void clear(View view) {
        db.execSQL("DELETE FROM student");
        Toast.makeText(this, "清空数据库成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 查询数据库中的数据
     * @param view
     */
    public void select(View view) {
        //查询姓名和C++成绩
        Cursor cursor = db.rawQuery("SELECT name,sno, cpp FROM student", null);
        while (cursor.moveToNext()) {
            //获得信息
            String name = cursor.getString(cursor.getColumnIndex("name"));
            int no = cursor.getInt(cursor.getColumnIndex("sno"));
            float cpp = cursor.getFloat(cursor.getColumnIndex("cpp"));
            //输出学生的学号、姓名和与姓名对应的C++成绩
            Log.e("AndroidApplication", "[NO = " + no + ",Name = "+ name + ", 成绩 = " + cpp + "]");
        }
        Log.e("AndroidApplication","===============================");
        Toast.makeText(this, "查询数据成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 跳转到学生数据库管理器
     */
    public void app(View view){
        Intent intent = new Intent(SQLiteActivity.this,SQLiteApplicationActivity.class);
        startActivity(intent);
    }
}
