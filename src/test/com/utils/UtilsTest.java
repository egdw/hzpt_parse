package com.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

/**
 * Created by hdy on 2017/9/16.
 */
public class UtilsTest {

    @Test
    public void getBannerTest() {
        HzptUtils utils = new HzptUtils();
        utils.getBanner();
    }

    @Test
    public void getPageTest() {
        new HzptUtils().getPageText("28417");
    }

    @Test
    public void getIndexNews() {
        new HzptUtils().getIndexNews();
    }

    @Test
    public void getList() {
        new HzptUtils().getList("448", 1);
    }

    @Test
    public void test() {
        String get = NetUtils.get("http://www.hzpt.edu.cn/News/index.php?action=newsList&pernums=15&cid=" + 448 + "&page=" + 1);
        Document document =
                Jsoup.parse(get);
        new HzptUtils().handlerPicNews(document);
    }

    @Test
    public void testFind() {
        new HzptUtils().find("测", 1);
    }
}
