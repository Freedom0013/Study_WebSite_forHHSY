package com.studytree.bean;

import java.io.Serializable;

/**
 * BannerBean
 * Title: BannerBean
 * @date 2018/7/12 15:54
 * @author Freedom0013
 */
public class BannerBean implements Serializable {
    /** id */
    public int bannner_id;
    /** banner标题 */
    public String banner_title;
    /** banner图片 */
    public String banner_image;
    /** banner内容地址 */
    public String banner_url;
    /** banner更新日期 */
    public String banner_updata_date;

    public BannerBean(){}

    @Override
    public String toString() {
        return "BannerBean [bannner_id=" + bannner_id + ", banner_title=" + banner_title + ", banner_image="
                + banner_image + ", banner_url=" + banner_url + ", banner_updata_date=" + banner_updata_date + "]";
    }
}
