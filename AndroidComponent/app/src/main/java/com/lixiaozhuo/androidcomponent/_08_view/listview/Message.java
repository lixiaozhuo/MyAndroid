package com.lixiaozhuo.androidcomponent._08_view.listview;

import java.util.Date;

/**
 * 消息类
 */
public class Message {
    /**
     * 图片id
     */
    private Integer imageId;
    /**
     * 联系人
     */
    private String name;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 消息时间
     */
    private Date dateTime;

    public Message(Integer imageId, String name, String content, Date dateTime) {
        this.imageId = imageId;
        this.name = name;
        this.content = content;
        this.dateTime = dateTime;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
