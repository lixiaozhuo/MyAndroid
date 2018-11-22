package com.lixiaozhuo.androidcomponent.intent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.lixiaozhuo.androidcomponent.R;

/**
 * 主活动
 */
public class IntentActivity1 extends Activity {
    /**
     * 打电话
     */
    private Button ButtonCall;
    /**
     * 发短信
     */
    private Button ButtonMessage;
    /**
     * 打开浏览器
     */
    private Button ButtonBrowser;
    /**
     * 拍照
     */
    private Button ButtonPhoto;
    /**
     * 设置
     */
    private Button ButtonSetting;
    /**
     * 转到桌面
     */
    private Button ButtonDeskTop;
    /**
     * 打开另一个Activity（显式，不带参数）
     */
    private Button ButtonOtherActivity;
    /**
     * 打开另一个Activity（隐式）
     */
    private Button ButtonOtherActivity1;
    /**
     * 带参数打开另一个Activity
     */
    private Button ButtonOtherActivityParameter;
    /**
     * 带返回值打开另一个Activity
     */
    private Button ButtonOtherActivityReturn;
    /**
     *显示打开Activity的返回值
     */
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent1);
        //获取显示控件
        textView = findViewById(R.id.iReturn);

        //打电话
        ButtonCall =  findViewById(R.id.iCall);
        ButtonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("tel:10086");
                //Intent.ACTION_DIAL:打开拨号界面,用户确定是否拨打
                //Intent.ACTION_CALL;直接拨打,高版本缺失权限
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });
        //发短信
        ButtonMessage =  findViewById(R.id.iMessage);
        ButtonMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("smsto:10086");
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.putExtra("sms_body", "Hello");
                startActivity(intent);
            }
        });
        //打开浏览器
        ButtonBrowser =  findViewById(R.id.iBrowser);
        ButtonBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.hbu.cn");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        //拍照
        ButtonPhoto = findViewById(R.id.iPhoto);
        ButtonPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });
        //设置
        ButtonSetting = findViewById(R.id.iSetting);
        ButtonSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 进入无线网络设置界面
                Intent intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                startActivityForResult(intent, 0);
            }
        });
        //转到桌面
        ButtonDeskTop =  findViewById(R.id.iDesktop);
        ButtonDeskTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                // 添加Action属性
                intent.setAction(Intent.ACTION_MAIN);
                // 添加Category属性
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
            }
        });
        //打开另一个Activity（显式，不带参数）
        ButtonOtherActivity =  findViewById(R.id.iOtherActivity);
        ButtonOtherActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntentActivity1.this, IntentActivity2.class);
                startActivity(intent);
            }
        });
        //打开另一个Activity（隐式）
        ButtonOtherActivity1 =  findViewById(R.id.iOtherActivity1);
        ButtonOtherActivity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.lixiaozhuo.androidcomponent.intent.ACTION_START");
                intent.addCategory("com.lixiaozhuo.androidcomponent.intent.MY_CATEGORY");
                startActivity(intent);
            }
        });
        //带参数打开另一个Activity
        ButtonOtherActivityParameter = findViewById(R.id.iOtherActivityParameter);
        ButtonOtherActivityParameter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //多参数
                Bundle bundle = new Bundle();
                bundle.putString("University", "HebeiUniversity");
                bundle.putString("College", "Cyberspace Security & Computer");
                intent.putExtras(bundle);
                //单参数
                //intent.putExtra("data", "网络空间安全与计算机学院");
                intent.setClass(IntentActivity1.this, IntentActivity4.class);
                startActivity(intent);
            }
        });
        //带返回值打开另一个Activity
        ButtonOtherActivityReturn =  findViewById(R.id.iOtherActivityReturn);
        ButtonOtherActivityReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("参数1-2", "参数值1-2");
                intent.putExtras(bundle);
                intent.setClass(IntentActivity1.this, IntentActivity5.class);
                // 0 用于识别第二个页面返回值
                startActivityForResult(intent, 0);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    Bundle b = data.getExtras();
                    String string = b.getString("参数2-1");
                    textView.setText(string);
                }
                break;
            default:
                break;
        }
    }
}
