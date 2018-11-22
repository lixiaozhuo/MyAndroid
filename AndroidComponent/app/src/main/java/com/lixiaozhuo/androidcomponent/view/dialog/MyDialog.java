package com.lixiaozhuo.androidcomponent.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.lixiaozhuo.androidcomponent.R;

/**
 * 自定义对话框
 */
public class MyDialog extends Dialog {
    public MyDialog(Context context) {
        super(context);
        //获取窗口管理对象
        Window window = this.getWindow();
        //去掉系统默认的对话框背景
        window.setBackgroundDrawable(new ColorDrawable(0));
        //获取窗口参数
        WindowManager.LayoutParams lp = window.getAttributes();
        //设置对话框显示在屏幕的顶部,默认是显示在屏幕的中心
        lp.gravity = Gravity.TOP;
        //设置外部点击可以回收对话框
        setCanceledOnTouchOutside(true);
        //去掉对话框的标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置对话框的内容
        window.setContentView(R.layout.dialog_custom);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){ //Bundle savedInstanceState
        super.onCreate(savedInstanceState);
        //退出按钮
        Button mConfirm=  findViewById(R.id.button);
        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
    }
    @Override
    protected void onStop(){
        super.onStop();
    }
}
