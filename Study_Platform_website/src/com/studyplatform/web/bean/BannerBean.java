package com.studyplatform.web.bean;

/**
 * BannerBean
 * Title: BannerBean
 * @date 2018年7月12日
 * @author Freedom0013
 */
public class BannerBean {
    /** id */
    private int bannner_id;
    /** banner标题 */
    private String banner_title;
    /** banner图片 */
    private String banner_image;
    /** banner内容地址 */
    private String banner_url;
    /** banner更新日期 */
    private String banner_updata_date;
    
    public BannerBean(){
        
    }
    
    /**
     * 全参构造函数
     */
    public BannerBean(int bannner_id, String banner_title, String banner_image, String banner_url,
            String banner_updata_date) {
        super();
        this.bannner_id = bannner_id;
        this.banner_title = banner_title;
        this.banner_image = banner_image;
        this.banner_url = banner_url;
        this.banner_updata_date = banner_updata_date;
    }

    public int getBannner_id() {
        return bannner_id;
    }

    public void setBannner_id(int bannner_id) {
        this.bannner_id = bannner_id;
    }

    public String getBanner_title() {
        return banner_title;
    }

    public void setBanner_title(String banner_title) {
        this.banner_title = banner_title;
    }

    public String getBanner_image() {
        return banner_image;
    }

    public void setBanner_image(String banner_image) {
        this.banner_image = banner_image;
    }

    public String getBanner_url() {
        return banner_url;
    }

    public void setBanner_url(String banner_url) {
        this.banner_url = banner_url;
    }

    public String getBanner_updata_date() {
        return banner_updata_date;
    }

    public void setBanner_updata_date(String banner_updata_date) {
        this.banner_updata_date = banner_updata_date;
    }

    @Override
    public String toString() {
        return "BannerBean [bannner_id=" + bannner_id + ", banner_title=" + banner_title + ", banner_image="
                + banner_image + ", banner_url=" + banner_url + ", banner_updata_date=" + banner_updata_date + "]";
    }
}
