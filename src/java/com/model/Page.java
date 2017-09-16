package com.model;

/**
 * Created by hdy on 2017/9/16.
 * 用于存放新闻内容
 */
public class Page {
    private String list_title;
    private String title;
    private String date;
    private String content;

    public Page(String list_title, String title, String date, String content) {
        this.list_title = list_title;
        this.title = title;
        this.date = date;
        this.content = content;
    }

    public String getList_title() {
        return list_title;
    }

    public void setList_title(String list_title) {
        this.list_title = list_title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Page{" +
                "list_title='" + list_title + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
