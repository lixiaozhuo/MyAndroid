package com.lixiaozhuo.androidcomponent.view.recyclerview;

/**
 * 水果类
 */
public class Fruit {
    //名称
    private String name;
    //图片id
    private int imageId;

    public Fruit(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

}
