package com.model;

/**
 * Created by hdy on 2017/9/16.
 */
public class SmallNews {
    private String title;
    private String url;
    private String date;
    private String pic;

    public SmallNews(String title, String url, String date, String pic) {
        this.title = title;
        this.url = url;
        this.date = date;
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "SmallNews{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", date='" + date + '\'' +
                ", pic='" + pic + '\'' +
                '}';
    }
}
