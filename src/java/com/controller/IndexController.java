package com.controller;

import com.utils.HzptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by hdy on 2017/9/16.
 */
@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private HzptUtils hzptUtils;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }


    @RequestMapping("/banner")
    @ResponseBody
    public String getBanner() {
        String banner = hzptUtils.getBanner();
        return banner;
    }

    @RequestMapping("/indexNews")
    @ResponseBody
    public String getIndexNews() {
        String news = hzptUtils.getIndexNews();
        return news;
    }

    @RequestMapping("/list")
    @ResponseBody
    public String getList(@RequestParam(required = true) Integer cid, Integer page) {
        if (page == null) {
            page = 1;
        }
        String list = hzptUtils.getList(cid + "", page);
        return list;
    }


    @RequestMapping("/detail")
    @ResponseBody
    public String getDetail(@RequestParam(required = true) Integer id) {
        String text = hzptUtils.getPageText(id + "");
        return text;
    }

    @RequestMapping(value = "find", method = RequestMethod.POST)
    @ResponseBody
    public String find(@RequestParam(required = true) String find, Integer p) {
        String s = hzptUtils.find(find, p);
        return s;
    }
}
