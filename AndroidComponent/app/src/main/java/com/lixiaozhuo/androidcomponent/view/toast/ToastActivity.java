package com.lixiaozhuo.androidcomponent.view.toast;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.lixiaozhuo.androidcomponent.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Toast
 */
public class ToastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toast);
    }

    /**
     * 默认样式:LONG3.5秒
     *
     * @param v
     */
    public void btnToast1(View v) {
        Toast.makeText(getApplicationContext(), "Toast默认样式", Toast.LENGTH_LONG).show();
    }

    /**
     * 默认样式:SHORT2秒
     *
     * @param v
     */
    public void btnToast2(View v) {
        Toast.makeText(this, "Toast默认样式", Toast.LENGTH_SHORT).show();
    }

    /**
     * 自定义Toast样式
     *
     * @param V
     */
    public void btnToast3(View V) {
        // 创建Toast
        Toast toast = new Toast(this);
        // 设置Toast显示的时长
        toast.setDuration(Toast.LENGTH_SHORT);
        // 创建图像视图
        ImageView img = new ImageView(this);
        // 设置图像视图路径
        img.setImageResource(R.mipmap.ic_launcher);
        // 设置Toast的视图图片
        toast.setView(img);
        // 设置位置
        toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.CENTER, 0, 0);
        // 显示
        toast.show();
    }

    /**
     * 自定义布局
     *
     * @param V
     */
    public void btnToast4(View V) {
        //创建Toast
        Toast toast = new Toast(this);
        // 获取布局
        View layout = View.inflate(this, R.layout.toast_custom, null);
        // 设置布局
        toast.setView(layout);
        // 设置位置
        toast.setGravity(Gravity.FILL_HORIZONTAL | Gravity.CENTER, 0, 0);
        // 显示
        toast.show();
    }

    /**
     * 自定义时长
     *
     * @param V
     */
    public void btnToast5(View V) {
        Toast toast = Toast.makeText(this, "可以设置时长的Toast", Toast.LENGTH_SHORT);
        showMyToast(toast, 100 * 1000);
    }

    /**
     * 自定义时长(此方法在 API 24 有效，在 API 26+ 失效。)
     *
     * @param toast Toast对象
     * @param cnt   显示的毫秒数
     */
    public void showMyToast(final Toast toast, final int cnt) {
        //每隔Toast.LENGTH_LONG对应时间显示一次toast,延迟toast消失
        final Timer timer = new Timer();
        //每隔Toast.LENGTH_LONG调用一次show方法;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                toast.show();
            }
        }, 0, 2000);
        //经过设置时间关闭该任务
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                toast.cancel();
                timer.cancel();
            }
        }, cnt);

    }
}
