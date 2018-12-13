package com.lixiaozhuo.androidcomponent._08_view.fragment.fragment2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;

import com.lixiaozhuo.androidcomponent.R;

/**
 * Fragment2活动
 */
public class Fragment2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment2);
        //设置Fragment
        Display display = getWindowManager().getDefaultDisplay();
        if(display.getWidth()>display.getHeight())
        {
            Fragment2_1 fragment1 = new Fragment2_1();
            getFragmentManager().beginTransaction().replace(R.id.main_layout,fragment1).commit();
        }
        else
        {
            Fragment2_2 fragment2 = new Fragment2_2();
            getFragmentManager().beginTransaction().replace(R.id.main_layout,fragment2).commit();
        }
    }
}
