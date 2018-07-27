package com.studytree.bean;

import java.io.Serializable;

/**
 * 资讯Bean
 * Title: NewsBean
 * @date 2018/7/27 11:00
 * @author Freedom0013
 */
public class NewsBean implements Serializable {
    /** 资讯id */
    public String id;
    /** 资讯标题 */
    public String title;
    /** 资讯创建时间 */
    public String createTime;
    /** 资讯推荐时间 */
    public String publishTime;
    /** 资讯描述 */
    public String description;
    /** 资讯图片 */
    public String pictureUrl;
    /** 资讯内容地址 */
    public String detailUrl;
    /** 是否读过 */
    public int readFlag;
    /** 资讯类型 */
    public String type;
    /** 资讯简介 */
    public String content;

    public NewsBean() {}

    public NewsBean(String id, String title, String createTime, String publishTime, String description, String pictureUrl, String detailUrl, int readFlag, String type, String content) {
        this.id = id;
        this.title = title;
        this.createTime = createTime;
        this.publishTime = publishTime;
        this.description = description;
        this.pictureUrl = pictureUrl;
        this.detailUrl = detailUrl;
        this.readFlag = readFlag;
        this.type = type;
        this.content = content;
    }

    @Override
    public String toString() {
        return "NewsBean{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", createTime='" + createTime + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", description='" + description + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", detailUrl='" + detailUrl + '\'' +
                ", readFlag=" + readFlag +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
