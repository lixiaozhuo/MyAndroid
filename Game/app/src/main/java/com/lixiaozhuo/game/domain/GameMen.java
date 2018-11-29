package com.lixiaozhuo.game.domain;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.lixiaozhuo.game.R;

/**
 * 游戏人物
 */
public class GameMen {
    //人物编号
    private int menNO;
    //人物坐标
    private int x,y;
    //人物大小
    private int width,height;
    //人物移动速度
    private int speed;
    //人物图像
    private ImageView menImage;

    public GameMen(int menNO, int x, int y,int speed, Context context) {
        this.menNO = menNO;
        this.x = x;
        this.y = y;
        this.speed = speed;
        //实例化人物图片并设置其属性
        this.menImage = new ImageView(context);
        this.menImage.setImageResource(getMenImageID());
        this.menImage.setLayoutParams(new RelativeLayout.LayoutParams(100, 150));
        this.menImage.setScaleType(ImageView.ScaleType.FIT_XY);
        this.menImage.setX(x);
        this.menImage.setY(y);
        //初始化人物大小属性
        this.width = 100;
        this.height = 150;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        //根据人物位置移动人物
        this.menImage.setX(x);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        //根据人物位置移动人物
        this.menImage.setY(y);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSpeed(){
        return speed;
    }

    public ImageView getMenImage(){
        return menImage;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //获取人物对应图片
    private int getMenImageID() {
        int imageId;
        switch (menNO) {
            case R.id.radioMen1:
                imageId = R.mipmap.men1;
                break;
            case R.id.radioMen2:
                imageId = R.mipmap.men2;
                break;
            default:
                imageId = R.mipmap.men1;
        }
        return imageId;
    }
}
