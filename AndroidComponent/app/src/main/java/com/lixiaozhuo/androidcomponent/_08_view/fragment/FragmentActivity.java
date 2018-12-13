package com.lixiaozhuo.androidcomponent._08_view.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lixiaozhuo.androidcomponent.R;
import com.lixiaozhuo.androidcomponent._08_view.fragment.fragment1.Fragment1Activity;
import com.lixiaozhuo.androidcomponent._08_view.fragment.fragment2.Fragment2Activity;
import com.lixiaozhuo.androidcomponent._08_view.fragment.fragment3.Fragment3Activity;

/**
 * Fragment
 */
public class FragmentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment);
    }

    /**
     * Fragment1
     * @param v
     */
    public void fragment1Test(View v){
        Intent intent = new Intent(FragmentActivity.this, Fragment1Activity.class);
        startActivity(intent);
    }

    /**
     * Fragment2
     * @param v
     */
    public void fragment2Test(View v){
        Intent intent = new Intent(FragmentActivity.this, Fragment2Activity.class);
        startActivity(intent);
    }

    /**
     * Fragment3
     * @param v
     */
    public void fragment3Test(View v){
        Intent intent = new Intent(FragmentActivity.this, Fragment3Activity.class);
        startActivity(intent);
    }
}
