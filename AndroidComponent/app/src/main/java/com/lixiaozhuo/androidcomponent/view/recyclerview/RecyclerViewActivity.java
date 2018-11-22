package com.lixiaozhuo.androidcomponent.view.recyclerview;


import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.lixiaozhuo.androidcomponent.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * RecyclerView
 */
public class RecyclerViewActivity extends Activity {
    //存放水果数据
    private List<Fruit> fruitList = new ArrayList<Fruit>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview);
        //初始化数据
        initFruits();
        //获取控件
        RecyclerView recyclerView =findViewById(R.id.recycler_view);
        //布局管理器
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        //数据适配器
        FruitAdapter adapter = new FruitAdapter(fruitList);
        //设置数据适配器
        recyclerView.setAdapter(adapter);
    }

    private void initFruits() {
        for (int i = 0; i < 2; i++) {
            fruitList.add(new Fruit(getRandomLengthName("Apple"), R.drawable.apple_pic));
            fruitList.add(new Fruit(getRandomLengthName("Banana"), R.drawable.banana_pic));
            fruitList.add(new Fruit(getRandomLengthName("Orange"), R.drawable.orange_pic));
            fruitList.add(new Fruit(getRandomLengthName("Watermelon"), R.drawable.watermelon_pic));
            fruitList.add(new Fruit(getRandomLengthName("Pear"), R.drawable.pear_pic));
            fruitList.add(new Fruit(getRandomLengthName("Grape"), R.drawable.grape_pic));
            fruitList.add(new Fruit(getRandomLengthName("Pineapple"), R.drawable.pineapple_pic));
            fruitList.add(new Fruit(getRandomLengthName("Strawberry"), R.drawable.strawberry_pic));
            fruitList.add( new Fruit(getRandomLengthName("Cherry"), R.drawable.cherry_pic));
            fruitList.add( new Fruit(getRandomLengthName("Mango"), R.drawable.mango_pic));
        }
    }
    //获取随记长度的名字
    private String getRandomLengthName(String name) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(name);
        }
        return builder.toString();
    }

}
