package com.lixiaozhuo.androidcomponent.view.notification;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.lixiaozhuo.androidcomponent.R;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 图片活动
 */
public class Notification_ImageActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_image);
        ImageView imageView = (ImageView) findViewById(R.id.image);
        //设置图片双指缩放
        PhotoViewAttacher attacher = new PhotoViewAttacher(imageView);
    }
}
