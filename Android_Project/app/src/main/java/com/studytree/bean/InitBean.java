package com.studytree.bean;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.studytree.log.Logger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 初始化更新信息bean
 * Title: InitBean
 * @date 2018/6/24 17:42
 * @author Freedom0013
 */
public class InitBean implements Serializable {
    private static final String TAG = InitBean.class.getSimpleName();
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
    public List<String> UpdataMessage = new ArrayList<String>();

    public InitBean(){}

    /**
     * 全参构造函数
     */
    public InitBean(boolean updata_flag, boolean ismust_updata_flag, String updata_title, int updata_visionCode, String updata_url, String updata_date, List<String> updataMessage) {
        this.updata_flag = updata_flag;
        this.ismust_updata_flag = ismust_updata_flag;
        this.updata_title = updata_title;
        this.updata_visionCode = updata_visionCode;
        this.updata_url = updata_url;
        this.updata_date = updata_date;
        UpdataMessage = updataMessage;
    }

    /**
     * Json构造函数
     * @param info 初始化更新json信息
     */
    public InitBean(JsonObject info){
        try{
            //解析普通数据
            this.updata_flag = info.get("updata_flag").getAsBoolean();
            this.ismust_updata_flag = info.get("ismust_updata_flag").getAsBoolean();
            this.updata_title = info.get("updata_title").getAsString();
            this.updata_visionCode = info.get("updata_visionCode").getAsInt();
            this.updata_url = info.get("updata_url").getAsString();
            this.updata_date = info.get("updata_date").getAsString();
            Gson gson = new Gson();
            //解析更新说明数组
            JsonArray jsonArray = info.getAsJsonArray("updataMessage");
            if(jsonArray!=null){
                this.UpdataMessage = gson.fromJson(jsonArray,new TypeToken<List<String>>(){}.getType());
            }
        }catch (Exception e){
            Logger.e(TAG,"使用json初始化失败",e);
        }
    }

    @Override
    public String toString() {
        return "InitBean [updata_flag=" + updata_flag + ", ismust_updata_flag=" + ismust_updata_flag + ", updata_title="
                + updata_title + ", updata_visionCode=" + updata_visionCode + ", updata_url=" + updata_url
                + ", updata_date=" + updata_date + ", UpdataMessage=" + UpdataMessage + "]";
    }
}
