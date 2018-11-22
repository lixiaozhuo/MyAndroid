package com.lixiaozhuo.androidcomponent.intent;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.lixiaozhuo.androidcomponent.R;

/**
 * 活动2
 */
public class IntentActivity2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent2);
    }
}
