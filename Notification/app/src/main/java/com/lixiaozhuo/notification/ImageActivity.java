package com.lixiaozhuo.notification;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 图片显示视图
 */
public class ImageActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ImageView imageView = (ImageView) findViewById(R.id.image);
        PhotoViewAttacher attacher = new PhotoViewAttacher(imageView);
    }
}
