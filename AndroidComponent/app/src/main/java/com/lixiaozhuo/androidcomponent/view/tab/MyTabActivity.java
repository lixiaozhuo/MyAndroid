package com.lixiaozhuo.androidcomponent.view.tab;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

import com.lixiaozhuo.androidcomponent.R;

/**
 * tab活动
 */
public class MyTabActivity extends TabActivity {
    //
	private TabHost tabHost;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tabHost = this.getTabHost();
        LayoutInflater.from(this).inflate(R.layout.mytabs, tabHost.getTabContentView(), true);
        //添加tab
        tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator("tab1")
                .setContent(R.id.view1));
        //添加意图tab
        Intent intent = new Intent(this, TabActivity2.class);
        tabHost.addTab(tabHost.newTabSpec("tab2")
        		.setIndicator("tab2")
        		.setContent(intent));
    }
}