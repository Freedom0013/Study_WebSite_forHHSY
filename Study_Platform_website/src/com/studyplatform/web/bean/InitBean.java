package com.studyplatform.web.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 用用初始化信息Bean
 * Title: InitBean
 * @date 2018年6月25日
 * @author Freedom0013
 */
public class InitBean {
    /** 更新标记 */
    public boolean  updata_flag;
    /** 是否为强制更新 */
    public boolean ismust_updata_flag;
    /** 更新标题 */
    public String updata_title;
    /** 更新版本号 */
    public int updata_visionCode;
    /** 更新地址 */
    public String updata_url;
    /** 更新日期 */
    public String updata_date;
    /** 更新说明 */
    List<String> UpdataMessage = new ArrayList<String>();
    
    public InitBean(boolean updata_flag, boolean ismust_updata_flag, String updata_title, int updata_visionCode, String updata_url,
            String updata_date, List<String> updataMessage) {
        this.updata_flag = updata_flag;
        this.ismust_updata_flag = ismust_updata_flag;
        this.updata_title = updata_title;
        this.updata_visionCode = updata_visionCode;
        this.updata_url = updata_url;
        this.updata_date = updata_date;
        UpdataMessage = updataMessage;
    }
    
    public boolean isUpdata_flag() {
        return updata_flag;
    }
    public void setUpdata_flag(boolean updata_flag) {
        this.updata_flag = updata_flag;
    }
    public boolean isIsmust_updata_flag() {
        return ismust_updata_flag;
    }
    public void setIsmust_updata_flag(boolean ismust_updata_flag) {
        this.ismust_updata_flag = ismust_updata_flag;
    }
    public String getUpdata_title() {
        return updata_title;
    }
    public void setUpdata_title(String updata_title) {
        this.updata_title = updata_title;
    }
    public int getUpdata_visionCode() {
        return updata_visionCode;
    }
    public void setUpdata_visionCode(int updata_visionCode) {
        this.updata_visionCode = updata_visionCode;
    }
    public String getUpdata_url() {
        return updata_url;
    }
    public void setUpdata_url(String updata_url) {
        this.updata_url = updata_url;
    }
    public String getUpdata_date() {
        return updata_date;
    }
    public void setUpdata_date(String updata_date) {
        this.updata_date = updata_date;
    }
    public List<String> getUpdataMessage() {
        return UpdataMessage;
    }
    public void setUpdataMessage(List<String> updataMessage) {
        UpdataMessage = updataMessage;
    }

    @Override
    public String toString() {
        return "InitBean [updata_flag=" + updata_flag + ", ismust_updata_flag=" + ismust_updata_flag + ", updata_title="
                + updata_title + ", updata_visionCode=" + updata_visionCode + ", updata_url=" + updata_url
                + ", updata_date=" + updata_date + ", UpdataMessage=" + UpdataMessage + "]";
    }
    
}
