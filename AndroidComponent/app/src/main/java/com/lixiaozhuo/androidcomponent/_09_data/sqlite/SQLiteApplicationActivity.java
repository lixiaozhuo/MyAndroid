package com.lixiaozhuo.androidcomponent._09_data.sqlite;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lixiaozhuo.androidcomponent.R;

import java.util.ArrayList;

/**
 * 学生数据库管理器
 */
public class SQLiteApplicationActivity extends Activity {
    //帮助器对象
    private SQLiteApplicationHelper dbHelper;
    //数据库管理对象
    private SQLiteDatabase database;
    //显示数据控件
    private RecyclerView mRecyclerView;
    //布局管理器
    private RecyclerView.LayoutManager mLayoutManager;
    //数据适配器
    private RecyclerView.Adapter mAdapter;
    //创建按钮
    Button btnCreate;
    //初始化按钮
    Button btnInit;
    //展示按钮
    Button btnList;
    //插入按钮
    Button btnInsert;
    //删除按钮
    Button btnDelete;
    //更新按钮
    Button btnUpdate;
    //查询按钮
    Button btnQuery;
    //id编辑对话框
    EditText editTextID;
    //姓名编辑对话框
    EditText editTextName;
    //年龄编辑对话框
    EditText editTextAge;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlite_application);
        //禁止软键盘打开界面时自动跳出
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //获取控件并绑定事件
        btnCreate = findViewById(R.id.db_create);
        btnInit = findViewById(R.id.db_init);
        btnList = findViewById(R.id.db_list);
        btnInsert = findViewById(R.id.db_insert);
        btnDelete = findViewById(R.id.db_delete);
        btnUpdate = findViewById(R.id.db_update);
        btnQuery = findViewById(R.id.db_query);
        editTextName = findViewById(R.id.stu_name);
        editTextID = findViewById(R.id.stu_no);
        editTextAge = findViewById(R.id.stu_age);
        //取消编辑控件闪烁效果
        editTextID.setCursorVisible(false);
        editTextAge.setCursorVisible(false);
        editTextName.setCursorVisible(false);
        //设置按钮的侦听器
        btnCreate.setOnClickListener(listener);
        btnInit.setOnClickListener(listener);
        btnList.setOnClickListener(listener);
        btnInsert.setOnClickListener(listener);
        btnDelete.setOnClickListener(listener);
        btnUpdate.setOnClickListener(listener);
        btnQuery.setOnClickListener(listener);
    }

    //监听器
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //当前按钮
            Button button = (Button) view;
            try {
                switch (button.getId()) {
                    //创建数据库
                    case R.id.db_create: {
                        dbHelper = new SQLiteApplicationHelper(SQLiteApplicationActivity.this, "SQLite_application.db", null, 1);
                        //获取数据库管理对象
                        database = dbHelper.getWritableDatabase();
                        //关闭数据库
                        database.close();
                        Toast.makeText(SQLiteApplicationActivity.this, "数据库已建立", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    //初始化数据库
                    case R.id.db_init: {
                        //获取数据库管理器
                        database = dbHelper.getWritableDatabase();
                        //初始化数据库
                        dbHelper.onInit(database);
                        //关闭数据库
                        database.close();
                        Toast.makeText(SQLiteApplicationActivity.this, "新写入10条数据", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    //列出所有数据
                    case R.id.db_list: {
                        //重新初始化RecyclerView
                        initDbData();
                        //初始化布局
                        initView();
                        Toast.makeText(SQLiteApplicationActivity.this, "显示全部数据", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    //根据id查询数据
                    case R.id.db_query: {
                        //初始化RecyclerView
                        initLineData();
                        //初始化
                        initView();
                        Toast.makeText(SQLiteApplicationActivity.this, "查询当前学号学生", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    //插入数据
                    case R.id.db_insert: {
                        //获取数据库管理器
                        database = dbHelper.getWritableDatabase();
                        //获取数据
                        int InsertId = Integer.parseInt(editTextID.getText().toString());
                        String InsertName = editTextName.getText().toString();
                        int InsertAge = Integer.parseInt(editTextAge.getText().toString());
                        // 插入数据
                        dbHelper.onInsert(database, InsertId, InsertName, InsertAge);
                        //关闭数据库
                        database.close();
                        Toast.makeText(SQLiteApplicationActivity.this, "插入一条数据", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    //删除数据
                    case R.id.db_delete: {
                        //获取数据库管理器
                        database = dbHelper.getWritableDatabase();
                        int DeleteId = Integer.parseInt(editTextID.getText().toString());
                        // 删除数据
                        dbHelper.onDelete(database, DeleteId);
                        //关闭数据库
                        database.close();
                        Toast.makeText(SQLiteApplicationActivity.this, "删除一条数据", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    //更新数据
                    case R.id.db_update: {
                        //获取数据库管理器
                        database = dbHelper.getWritableDatabase();
                        //获取数据
                        int UpdateId = Integer.parseInt(editTextID.getText().toString());
                        String UpdateName = editTextName.getText().toString();
                        int UpdateAge = Integer.parseInt(editTextAge.getText().toString());
                        // 更新数据
                        dbHelper.onUpdate(database, UpdateId, UpdateName, UpdateAge);
                        //关闭数据库
                        database.close();
                        Toast.makeText(SQLiteApplicationActivity.this, "更新一条数据", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    default:
                        break;
                }
            } catch (Exception e) {
            }
        }
    };

    /**
     * 初始化自定义布局的数据,显示全部数据
     */
    private void initDbData() {
        //实例化布局管理器
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //实例化数据适配器-数据源为全部数据
        mAdapter = new SQLiteApplicationAdapter(getStudentList());
    }

    /**
     * 获取全部数据
     *
     * @return
     */
    public ArrayList<SQLiteApplicationStudent> getStudentList() {
        //创建帮助器对象
        dbHelper = new SQLiteApplicationHelper(SQLiteApplicationActivity.this, "school.db", null, 3);
        //获取数据库管理对象
        database = dbHelper.getWritableDatabase();
        //存储学生信息集合
        ArrayList<SQLiteApplicationStudent> studentList = new ArrayList<>();
        //游标记录数据集
        Cursor cursor = dbHelper.onList(database);
        if (cursor.moveToFirst()) {
            do {
                //存储学生信息
                SQLiteApplicationStudent student = new SQLiteApplicationStudent();
                //获取学生信息并存储
                student.student_ID = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLiteApplicationStudent.KEY_ID)));
                student.name = cursor.getString(cursor.getColumnIndex(SQLiteApplicationStudent.KEY_name));
                student.age = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLiteApplicationStudent.KEY_age)));
                //加入学生信息集合
                studentList.add(student);
            } while (cursor.moveToNext());
        }
        //关闭游标
        cursor.close();
        //关闭数据库
        database.close();
        return studentList;
    }


    /**
     * 初始化自定义布局的数据，显示一条数据
     */
    private void initLineData() {
        //实例化布局管理器
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //实例化数据适配器-数据源为全部数据
        mAdapter = new SQLiteApplicationAdapter(getStudentLine());
    }

    /**
     * 获取查询结果
     *
     * @return
     */
    public ArrayList<SQLiteApplicationStudent> getStudentLine() {
        //创建帮助器对象
        dbHelper = new SQLiteApplicationHelper(SQLiteApplicationActivity.this, "school.db", null, 3);
        //获取数据库管理对象
        database = dbHelper.getWritableDatabase();
        //存储学生信息集合
        ArrayList<SQLiteApplicationStudent> studentLine = new ArrayList<>();
        //学生id
        int QueryId = Integer.parseInt(editTextID.getText().toString());
        //游标记录数据集
        Cursor cursor = dbHelper.onQuery(database, QueryId);
        if (cursor.moveToFirst()) {
            do {
                //存储学生信息
                SQLiteApplicationStudent student = new SQLiteApplicationStudent();
                //获取学生信息并存储
                student.student_ID = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLiteApplicationStudent.KEY_ID)));
                student.name = cursor.getString(cursor.getColumnIndex(SQLiteApplicationStudent.KEY_name));
                student.age = Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLiteApplicationStudent.KEY_age)));
                //加入学生信息集合
                studentLine.add(student);
            } while (cursor.moveToNext());
        }
        //关闭游标
        cursor.close();
        //关闭数据库
        database.close();
        return studentLine;
    }

    /**
     * 配置显示数据控件
     */
    private void initView() {
        //获取控件
        mRecyclerView = findViewById(R.id.my_recycler_view);
        //设置布局管理器
        mRecyclerView.setLayoutManager(mLayoutManager);
        // 设置数据适配器
        mRecyclerView.setAdapter(mAdapter);
    }


}
