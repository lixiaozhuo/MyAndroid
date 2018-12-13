package com.lixiaozhuo.game.domain;

import android.widget.ImageView;

import com.lixiaozhuo.game.MyApplication;
import com.lixiaozhuo.game.R;

/**
 * 踏板类
 */
public class GamePedal {
    //踏板长度
    private int length;
    //踏板位置
    private int x, y;
    //踏板速度
    private int speed;
    //踏板图像
    private ImageView pedalImage;

    public GamePedal(int length, int x, int y, int speed) {
        this.length = length;
        this.x = x;
        this.y = y;
        this.speed = speed;
        //设踏板的显示图像
        pedalImage = new ImageView(MyApplication.getContext());
        //设置图片源
        pedalImage.setImageResource(R.mipmap.pedal);
        //不按比例缩放图片
        pedalImage.setScaleType(ImageView.ScaleType.FIT_XY);
        //设置踏板位置
        pedalImage.setX(x);
        pedalImage.setY(y);

    }

    public int getLength() {
        return length;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        //根据踏板位置移动踏板
        this.pedalImage.setY(y);
    }

    public int getSpeed() {
        return speed;
    }

    public ImageView getPedalImage() {
        return pedalImage;
    }
}
