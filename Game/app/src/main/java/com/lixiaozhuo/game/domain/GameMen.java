package com.lixiaozhuo.game.domain;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.lixiaozhuo.game.activity.GameMenu;
import com.lixiaozhuo.game.R;

/**
 * 游戏人物
 */
public class GameMen {
    //人物编号
    private int menNO;
    //人物坐标
    private int x = 0;
    private int y = 0;
    //人物图像
    private ImageView menImage;
    //人物速度
    public  int speed;

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
    }

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

    /**
     * 获取人物名称
     *
     * @return
     */
    public String getMenName() {
        return GameMenu.gameMenMap.get(menNO);
    }

    public int getMenNO() {
        return menNO;
    }

    public void setMenNO(int menNO) {
        this.menNO = menNO;
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

    public ImageView getMenImage() {
        return menImage;
    }

    public void setMenImage(ImageView menImage) {
        this.menImage = menImage;
        //更新位置信息
        this.x = (int)this.menImage.getX();
        this.y = (int)this.menImage.getY();
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

}
