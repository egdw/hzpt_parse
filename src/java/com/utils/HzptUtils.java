package com.utils;

import com.alibaba.fastjson.JSON;
import com.model.Banner;
import com.model.List;
import com.model.Page;
import com.model.SmallNews;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by hdy on 2017/9/16.
 * 用于解析hzpt的内容
 */
@Component
public class HzptUtils {
//    @Autowired
//    private JedisPool jedisPool;

    /**
     * 获取学院要点新闻
     */
    public String getBanner() {
//        Jedis jedis = jedisPool.getResource();
//        String banner = jedis.get("hzpt_getBanner");
//        if (banner != null) {
////            jedis.close();
//            return banner;
//        }
        String get = NetUtils.get("http://www.hzpt.edu.cn/News/index.php");
        Document document =
                Jsoup.parse(get);
        Elements banners = document.getElementsByClass("a_bigImg");
        ArrayList<Banner> list = new ArrayList<Banner>();
        if (banners.size() >= 3) {
            for (int i = 0; i < banners.size(); i++) {
                Element element = banners.get(i);
                //<a href="?action=newsDetail&amp;id=28258" class="a_bigImg"><img src="../Uploadfile/2017-06/2017-06-235624.jpg" width="315" height="213" alt=""></a>
                String attr = element.attr("href");
                attr = attr.substring(attr.indexOf("id=") + 3);
                String src = element.getElementsByTag("img").get(0).attr("src");
                list.add(new Banner("http://www.hzpt.edu.cn" + src.substring(2), attr));
            }
        }
//        jedis.set("hzpt_getBanner", JSON.toJSONString(list));
//        jedis.expire("hzpt_getBanner", 60 * 60 * 24);
//        jedis.close();
        return JSON.toJSONString(list);
    }

    /**
     * 获取首页的新闻
     */
    public String getIndexNews() {
//        Jedis jedis = jedisPool.getResource();
//        String banner = jedis.get("hzpt_getIndexNews");
//        if (banner != null) {
////            jedis.close();
//            return banner;
//        }
        String get = NetUtils.get("http://www.hzpt.edu.cn/News/index.php");
        Document document =
                Jsoup.parse(get);
        Elements news = document.getElementsByClass("left_con_r").get(0).getElementsByTag("ul").get(0).getElementsByTag("li");
        ArrayList<SmallNews> list = new ArrayList<SmallNews>();
        if (news.size() >= 3) {
            for (int i = 0; i < news.size(); i++) {
                Element a = news.get(i).getElementsByTag("a").get(0);
                Element span = news.get(i).getElementsByTag("span").get(0);
                String href = a.attr("href");
                href = href.substring(href.indexOf("id=") + 3);
                String title = a.text();
                SmallNews smallNews = new SmallNews(title, href, span.text(), null);
                list.add(smallNews);
            }
        }
//        jedis.set("hzpt_getIndexNews", JSON.toJSONString(list));
//        jedis.expire("hzpt_getIndexNews", 60 * 60 * 24);
//        jedis.close();
        return JSON.toJSONString(list);
    }

    /**
     * 获取新闻主要内容
     *
     * @param id
     */
    public String getPageText(String id) {
//        Jedis jedis = jedisPool.getResource();
//        String banner = jedis.get("hzpt_getPageText_" + id);
//        if (banner != null) {
////            jedis.close();
//            return banner;
//        }
        String get = NetUtils.get("http://www.hzpt.edu.cn/News/index.php?action=newsDetail&id=" + id);
        Document document =
                Jsoup.parse(get);
        Elements list = document.getElementsByClass("list");
        String list_tit = document.getElementsByClass("list_tit").get(0).text();
        //获取列表标题
        Elements detail = document.getElementsByClass("detail").get(0).getElementsByClass("detail_tit").get(0).getElementsByTag("p");
        //文章标题
        String title = detail.get(0).getElementsByTag("a").get(0).text();
        //文章日期
        String date = detail.get(1).getElementsByTag("span").text();
        //文章内容
        String content = document.getElementsByClass("detail").get(0).getElementsByClass("detail_con").html();

        //content当中的所有图片指向都要加上学校的域名
        content = content.replace("/uploadFile/", "http://www.hzpt.edu.cn/uploadFile/");
        Page page = new Page(list_tit, title, date, content);
//        jedis.set("hzpt_getPageText_" + id, JSON.toJSONString(list));
//        jedis.expire("hzpt_getPageText_" + id, 60 * 60 * 24);
//        jedis.close();
        return JSON.toJSONString(page);
    }


    /**
     * 获取各个分类的内容
     * <p>
     * 适用于
     * 学院要闻 441
     * 信息通告 444
     * 综合新闻 442
     * 专题新闻 443
     * 视频新闻 447
     * 高职视野 451
     * 媒体视角 445
     * 校园人物 449
     *
     * @param cid
     * @param num
     */
    public String getList(String cid, Integer num) {
//        Jedis jedis = jedisPool.getResource();
//        String s = jedis.get("hzpt_getList_" + cid + "_" + num);
//        if (s != null) {
////            jedis.close();
//            return s;
//        }
        //http://www.hzpt.edu.cn/News/index.php?action=newsList&pernums=15&cid=442&page=2
        String get = NetUtils.get("http://www.hzpt.edu.cn/News/index.php?action=newsList&pernums=15&cid=" + cid + "&page=" + num);
        Document document =
                Jsoup.parse(get);
        Element list = document.getElementsByClass("list").get(0);
        String list_tit = list.getElementsByClass("list_tit").text();
        Elements elements = list.select("div.list_con > div.list_con_1 > ul > li > a");
        Elements elements2 = list.select("div.list_con > div.list_con_1 > ul > li");
        ArrayList<SmallNews> news = new ArrayList<SmallNews>();
        for (int i = 0; i < elements.size(); i++) {
            String href = elements.get(i).attr("href");
            String date = elements2.get(i).getElementsByTag("span").get(0).text();
            //获取id
            href = href.substring(href.lastIndexOf("id=") + 3);
            //获取标题
            String title = elements.get(i).text();
            news.add(new SmallNews(title, href, date, null));
        }
        Elements page = list.select("div.page > form > a");
        //获取当前的页数
        String last = page.get(page.size() - 1).attr("href");
        String currentPage = page.get(page.size() - 2).attr("href");
        currentPage = (Integer.valueOf(currentPage.substring(currentPage.lastIndexOf("page=") + 5)) - 1) + "";
        if ("0".equals(currentPage)) {
            currentPage = "1";
        }
        //获取页数
        last = last.substring(last.lastIndexOf("page=") + 5);
        List list1 = new List(list_tit, news, Integer.valueOf(currentPage), Integer.valueOf(last));
//        jedis.set("hzpt_getList_" + cid + "_" + num, JSON.toJSONString(list));
//        jedis.expire("hzpt_getList_" + cid + "_" + num, 60 * 60 * 24);
//        jedis.close();
        return JSON.toJSONString(list1);
    }

    /**
     * 用于解决图片新闻的版块解析
     *
     * @return
     */
    public void handlerPicNews(Document document) {
        Element list = document.getElementsByClass("list").get(0);
        String list_tit = list.getElementsByClass("list_tit").text();
        Elements elements = document.getElementsByClass("list_img");
    }


    public String find(String find, Integer p) {
        if (p == null) {
            p = 1;
        }
        try {
            //http://www.hzpt.edu.cn/News/index.php?action=newsList&pernums=15&keywords=%E6%B5%8B&page=2
            String url = "http://www.hzpt.edu.cn/News/index.php?action=newsList&pernums=15&keywords=" + URLEncoder.encode(find, "utf-8") + "&page=" + p;
            url = NetUtils.get(url);
            Document document =
                    Jsoup.parse(url);
            Element list = document.getElementsByClass("list_con").get(0);
            Elements elements = list.select("div.list_con_1 > ul > li > a");
            Elements elements2 = list.select("div.list_con_1 > ul > li");
            ArrayList<SmallNews> news = new ArrayList<SmallNews>();
            for (int i = 0; i < elements.size(); i++) {
                String href = elements.get(i).attr("href");
                String date = elements2.get(i).getElementsByTag("span").get(0).text();
                //获取id
                href = href.substring(href.lastIndexOf("id=") + 3);
                //获取标题
                String title = elements.get(i).text();
                news.add(new SmallNews(title, href, date, null));
            }
            Elements page = list.select("div.page > form > a");
            //获取当前的页数
            String last = page.get(page.size() - 1).attr("href");
            String currentPage = page.get(page.size() - 2).attr("href");
            currentPage = (Integer.valueOf(currentPage.substring(currentPage.lastIndexOf("page=") + 5)) - 1) + "";
            if ("0".equals(currentPage)) {
                currentPage = "1";
            }
            //获取页数
            last = last.substring(last.lastIndexOf("page=") + 5);
            List list1 = new List("搜索", news, Integer.valueOf(currentPage), Integer.valueOf(last));
            return JSON.toJSONString(list1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
