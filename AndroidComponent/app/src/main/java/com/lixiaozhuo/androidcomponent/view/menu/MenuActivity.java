package com.lixiaozhuo.androidcomponent.view.menu;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lixiaozhuo.androidcomponent.R;

/**
 * 上下文菜单和选项菜单
 */
public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        TextView myMenu =findViewById(R.id.david);
        //注册上下文菜单
        registerForContextMenu(myMenu);
    }

    /**
     * 选项菜单
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 1.group的id;2.item的id;3.是否排序;4.将要显示的内容
        menu.add(0, 1, 0, "计算机科学与技术");
        menu.add(0, 2, 0, "网络工程");
        menu.add(0, 3, 0, "信息安全");
        menu.add(0, 4, 0, "河北大学艺术学院");
        menu.add(0, 5, 0, "河北大学质检学院");
        //子菜单
        SubMenu sub = menu.addSubMenu("子菜单");
        sub.add(0, 5, 0, "子菜单一");
        sub.add(0, 6, 0, "子菜单二");
        sub.add(0, 7, 0, "子菜单三");
        //不同组
        menu.add(1, 6, 0, "河北大学计算机学院");
        menu.add(1, 7, 1, "河北大学电信学院");
        menu.add(1, 8, 5, "河北大学新闻学院");
        menu.add(1, 9, 2, "河北大学艺术学院");
        menu.add(1, 10, 3, "河北大学质检学院");
        return true;
    }

    /**
     * 选项菜单点击事件
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Toast.makeText(MenuActivity.this, "我是计算机学院", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(MenuActivity.this, "我是电信学院", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(MenuActivity.this, "我是。。。", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(MenuActivity.this, "I am 。。。", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    /**
     * 上下文菜单
     * @param menu
     * @param v
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        // 1.group的id;2.item的id;3.是否排序;4.将要显示的内容
        menu.add(0, 1, 0, "我是菜单1");
        menu.add(0, 2, 0, "我是菜单2");
        menu.add(0, 3, 0, "我是菜单3");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    /*上下文菜单选中回调
     * @param item
     * @return
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Toast.makeText(MenuActivity.this, "菜单1", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(MenuActivity.this, "菜单2", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(MenuActivity.this, "菜单3", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
