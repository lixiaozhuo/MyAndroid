package com.lixiaozhuo.toastdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 默认样式:LONG3.5秒
     * @param v
     */
    public void btnToast1(View v){
        Toast.makeText(getApplicationContext(),"Toast默认样式",Toast.LENGTH_LONG).show();
    }

    /**
     * 默认样式:SHORT2秒
     * @param v
     */
    public void btnToast2(View v){
        Toast.makeText(this,"Toast默认样式",Toast.LENGTH_SHORT).show();
    }

    /**
     * 自定义Toast样式
     * @param V
     */
    public void btnToast3(View V){
        Toast toast = new Toast(this);// 创建Toast
        toast.setDuration(Toast.LENGTH_SHORT);// 设置Toast显示的时长
        ImageView img = new ImageView(this);// 创建ImageView
        img.setImageResource(R.mipmap.ic_launcher);// 设置ImageView路径
        toast.setView(img);// 设置Toast的视图图片
        toast.setGravity(Gravity.FILL_HORIZONTAL|Gravity.CENTER,0,0); // 设置位置
        toast.show();// 显示
    }

    /**
     * 自定义布局
     * @param V
     */
    public void btnToast4(View V){
        Toast toast = new Toast(this);
        View layout = View.inflate(this,R.layout.toast,null);// 获取布局
        toast.setView(layout);// 设置布局
        toast.setGravity(Gravity.FILL_HORIZONTAL|Gravity.CENTER,0,0);// 设置位置
        toast.show();// 显示
    }

    /**
     * 自定义时长
     * @param V
     */
    public void btnToast5(View V){
        Toast toast=Toast.makeText(this, "可以设置时长的Toast", Toast.LENGTH_LONG);
        showMyToast(toast, 100*1000);
    }

    /**
     * 自定义时长
     * @param toast Toast对象
     * @param cnt 显示的毫秒数
     */
    public void showMyToast(final Toast toast, final int cnt) {
        //注意: 此方法在 API 24 有效，在 API 26+ 失效。
        //每隔Toast.LENGTH_LONG时间显示一次toast,延迟toast消失
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                toast.show();
            }
        }, 0, Toast.LENGTH_LONG);//每隔Toast.LENGTH_LONG调用一次show方法;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                toast.cancel();
                timer.cancel();
            }
        }, cnt);//经过多长时间关闭该任务
    }
}
