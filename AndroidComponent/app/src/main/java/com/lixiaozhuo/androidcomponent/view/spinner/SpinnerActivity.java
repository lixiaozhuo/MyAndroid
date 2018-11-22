package com.lixiaozhuo.androidcomponent.view.spinner;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.lixiaozhuo.androidcomponent.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 下拉菜单
 */
public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener{
    /**
     * 显示控件
     */
    private TextView textView;
    /**
     * 下拉控件
     */
    private Spinner spinner;
    /**
     * 数据源
     */
    private List<String> list;
    /**
     * 数据适配器
     */
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spineer);
        //获取显示控件
        textView =findViewById(R.id.textView);
        //获取下拉控件
        spinner = findViewById(R.id.spinner);
        textView.setText("您选择的城市是北京");
        //1.设置数据源
        list = new ArrayList<>();
        list.add("北京");
        list.add("上海");
        list.add("广州");
        list.add("深圳");
        list.add("保定");
        //2.定义适配器
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        //3.adapter设置下拉样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //4.spinner加载适配器
        spinner.setAdapter(adapter);
        //5.spinner设置监听器
        spinner.setOnItemSelectedListener(this);
    }

    //选择选项后回显
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String cityName = adapter.getItem(position);
        textView.setText("您选择的城市是:" + cityName);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
