package com.lixiaozhuo.game.domain;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.lixiaozhuo.game.R;

/**
 * 踏板类
 */
public class GamePedal {
    //踏板长度
    private int length;
    //踏板位置
    private int x,y;
    //踏板图像
    private ImageView pedalImage;
    //踏板移动距离
    private  int speed;

    public GamePedal(int length, int x, int y,int speed, Context context) {
        this.length = length;
        this.x = x;
        this.y = y;
        this.speed = speed;
        //设踏板的显示图像
        pedalImage = new ImageView(context);
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

    public void setLength(int length) {
        this.length = length;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        //根据踏板位置移动踏板
        this.pedalImage.setX(x);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        //根据踏板位置移动踏板
        this.pedalImage.setY(y);
    }

    public ImageView getPedalImage() {
        return pedalImage;
    }

    public void setPedalImage(ImageView pedalImage) {
        this.pedalImage = pedalImage;
        //更新踏板位置
        this.x = (int)this.pedalImage.getX();
        this.y = (int)this.pedalImage.getY();
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
