package com.studyplatform.web.servlet.forAndroid;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import com.google.gson.JsonObject;
import com.studyplatform.web.bean.NewsBean;
import com.studyplatform.web.system.SystemCommonValue;
import com.studyplatform.web.utils.DebugUtils;
import com.studyplatform.web.utils.WebUtils;

/**
 * 资讯Action
 * Title: NewsAction
 * @date 2018年7月27日
 * @author Freedom0013
 */
public class NewsAction extends HttpServlet {
    /** 版本控制 */
    private static final long serialVersionUID = 1L;
    /** 图片地址 */
    private String Image_Url_Host = "http://"+SystemCommonValue.HOST+"/images/android_images/news_images/";
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 控制字符集
        WebUtils.setCharSet(request, response);
        PrintWriter out = response.getWriter();
        
        List<NewsBean> NewsList = new ArrayList<NewsBean>();
//===================================暂用事例新闻数据（非最终）============================================
        NewsBean bean0 = new NewsBean();
        bean0.id = "0";
        bean0.title = "习近平金砖讲话照亮第二个金色十年";
        bean0.createTime = "2018-07-26 11:31:20";
        bean0.publishTime = "2018-07-26 11:31:20";
        bean0.description = "国家主席习近平7月25日应邀出席在南非约翰内斯堡举行的金砖国家工商论坛，并发表题为《顺应时代潮流　实现共同发展》的重要讲话。";
        bean0.pictureUrl = Image_Url_Host + "news0.jpg";
        bean0.detailUrl = "http://news.cctv.com/2018/07/26/ARTIZ8BnSw5kn5V1HqH1Lrm3180726.shtml";
        bean0.readFlag = 0;
        bean0.type = "国内";
        bean0.content = "国家主席习近平7月25日应邀出席在南非约翰内斯堡举行的金砖国家工商论坛，并发表题为《顺应时代潮流　实现共同发展》的重要讲话。";
        NewsList.add(bean0);
        
        NewsBean bean1 = new NewsBean();
        bean1.id = "1";
        bean1.title = "武汉生物40万支疫苗不合格湖北食药监局回应";
        bean1.createTime = "2018-07-26 10:31:20";
        bean1.publishTime = "2018-07-26 10:31:20";
        bean1.description = "湖北食药监局疫苗监管问答";
        bean1.pictureUrl = Image_Url_Host + "news1.jpg";
        bean1.detailUrl = "http://www.yidianzixun.com/article/0Je109JT";
        bean1.readFlag = 0;
        bean1.type = "国内";
        bean1.content = "湖北食药监局疫苗监管问答";
        NewsList.add(bean1);
        
        NewsBean bean2 = new NewsBean();
        bean2.id = "2";
        bean2.title = "人工智能撬动未来教育新空间";
        bean2.createTime = "2018-07-25 07:54:46";
        bean2.publishTime = "2018-07-25 07:54:46";
        bean2.description = "人工智能阅卷评分、大数据帮助选科选专业、课堂由学生主讲....随着人工智能浪潮奔涌而来，传统教育思维和教学模式正悄然发生转变，未来教育新空间进一步开启。";
        bean2.pictureUrl = Image_Url_Host + "news2.jpg";
        bean2.detailUrl = "http://www.xinhuanet.com/tech/2018-07/25/c_1123172198.htm";
        bean2.readFlag = 0;
        bean2.type = "国内";
        bean2.content = "人工智能撬动未来教育新空间";
        NewsList.add(bean2);
        
        NewsBean bean3 = new NewsBean();
        bean3.id = "3";
        bean3.title = "FAST引领 贵州构建天文科研矩阵";
        bean3.createTime = "2018-07-25 08:02:47";
        bean3.publishTime = "2018-07-25 08:02:47";
        bean3.description = "24日，来自贵州省科技厅的消息显示，截至目前，FAST已发现44颗脉冲星，其中18颗获得国际认证。";
        bean3.pictureUrl = Image_Url_Host + "news3.jpg";
        bean3.detailUrl = "http://www.xinhuanet.com/tech/2018-07/25/c_1123172300.htm";
        bean3.readFlag = 0;
        bean3.type = "国内";
        bean3.content = "FAST引领 贵州构建天文科研矩阵";
        NewsList.add(bean3);
        
        NewsBean bean4 = new NewsBean();
        bean4.id = "4";
        bean4.title = "我首次利用四维纠缠态实现量子密集编码";
        bean4.createTime = "2018-07-25 08:00:20";
        bean4.publishTime = "2018-07-25 08:00:20";
        bean4.description = "记者从中国科大获悉，该校郭光灿院士团队的李传锋、柳必恒等人，首次利用四维纠缠态实现量子密集编码。";
        bean4.pictureUrl = Image_Url_Host + "news4.jpg";
        bean4.detailUrl = "http://www.xinhuanet.com/tech/2018-07/25/c_1123172276.htm";
        bean4.readFlag = 0;
        bean4.type = "国内";
        bean4.content = "我首次利用四维纠缠态实现量子密集编码";
        NewsList.add(bean4);
        
        NewsBean bean5 = new NewsBean();
        bean5.id = "5";
        bean5.title = "工信部：上半年移动流量资费比去年底降46.2%";
        bean5.createTime = "2018-07-25 07:31:10";
        bean5.publishTime = "2018-07-25 07:31:10";
        bean5.description = "上半年移动流量平均资费较2017年底下降46.2%。";
        bean5.pictureUrl = Image_Url_Host + "news5.jpg";
        bean5.detailUrl = "http://www.xinhuanet.com/tech/2018-07/25/c_1123171972.htm";
        bean5.readFlag = 0;
        bean5.type = "国内";
        bean5.content = "工信部：上半年移动流量资费比去年底降46.2%";
        NewsList.add(bean5);
        
        NewsBean bean6 = new NewsBean();
        bean6.id = "6";
        bean6.title = "世卫组织宣布刚果（金）埃博拉疫情结束";
        bean6.createTime = "2018-07-25 11:02:06";
        bean6.publishTime = "2018-07-25 11:02:06";
        bean6.description = "世界卫生组织24日宣布刚果（金）于今年5月暴发的埃博拉疫情正式结束。";
        bean6.pictureUrl = Image_Url_Host + "news6.jpg";
        bean6.detailUrl = "http://www.xinhuanet.com/world/2018-07/25/c_1123174233.htm";
        bean6.readFlag = 0;
        bean6.type = "国际";
        bean6.content = "世卫组织宣布刚果（金）埃博拉疫情结束";
        NewsList.add(bean6);
        
        NewsBean bean7 = new NewsBean();
        bean7.id = "7";
        bean7.title = "袁隆平团队在迪拜成功试种沙漠海水稻";
        bean7.createTime = "2018-07-23 08:02:11";
        bean7.publishTime = "2018-07-23 08:02:11";
        bean7.description = "标志着袁隆平海水稻团队此次在迪拜沙漠地区的试验种植取得了阶段性成功。";
        bean7.pictureUrl = Image_Url_Host + "news7.jpg";
        bean7.detailUrl = "http://www.xinhuanet.com/world/2018-07/23/c_1123161579.htm";
        bean7.readFlag = 0;
        bean7.type = "国际";
        bean7.content = "袁隆平团队在迪拜成功试种沙漠海水稻";
        NewsList.add(bean7);
        
        NewsBean bean8 = new NewsBean();
        bean8.id = "8";
        bean8.title = "火星首次发现液态水湖 专家：移民火星仍不现实";
        bean8.createTime = "2018-07-27 09:31:14";
        bean8.publishTime = "2018-07-27 09:31:14";
        bean8.description = "火星南极冰盖表面下约1.5千米处存在一个约20千米宽的液态水湖。";
        bean8.pictureUrl = Image_Url_Host + "news8.jpg";
        bean8.detailUrl = "http://www.xinhuanet.com/tech/2018-07/27/c_1123184456.htm";
        bean8.readFlag = 0;
        bean8.type = "国际";
        bean8.content = "火星首次发现液态水湖 专家：移民火星仍不现实”";
        NewsList.add(bean8);
        
        NewsBean bean9 = new NewsBean();
        bean9.id = "9";
        bean9.title = "警惕互联网上的消费陷阱";
        bean9.createTime = "2018-07-26 08:09:12";
        bean9.publishTime = "2018-07-26 08:09:12";
        bean9.description = "消费者转发优惠微信却被非法地搜集了个人信息、想省钱选择优惠互联网家装却遭卷款跑路、网购商品出问题被拒绝退换。";
        bean9.pictureUrl = Image_Url_Host + "news9.png";
        bean9.detailUrl = "http://www.xinhuanet.com/tech/2018-07/26/c_1123177759.htm";
        bean9.readFlag = 0;
        bean9.type = "国内";
        bean9.content = "警惕互联网上的消费陷阱";
        NewsList.add(bean9);
        
        NewsBean bean10 = new NewsBean();
        bean10.id = "10";
        bean10.title = "工信部向首批15家虚拟运营商颁发经营许可证";
        bean10.createTime = "2018-07-24 07:35:24";
        bean10.publishTime = "2018-07-24 07:35:24";
        bean10.description = "移动转售业务迎来商用新起点 。";
        bean10.pictureUrl = Image_Url_Host + "news10.jpg";
        bean10.detailUrl = "http://www.xinhuanet.com/tech/2018-07/24/c_1123166752.htm";
        bean10.readFlag = 0;
        bean10.type = "国内";
        bean10.content = "工信部向首批15家虚拟运营商颁发经营许可证";
        NewsList.add(bean10);
        
//===================================暂用事例新闻数据结束============================================
        
        Gson gson = new Gson();
        
        String newslist = gson.toJson(NewsList);
        DebugUtils.showLog(newslist);
        
        JsonObject data = new JsonObject();
        data.addProperty("data", newslist);
        
        
        out.write(data.toString());  
        out.flush();  
        out.close();  
    }

    public NewsAction() {
        super();
    }
    
    public void destroy() {
        super.destroy();
    }

    
    public void init() throws ServletException {
        
    }
}
