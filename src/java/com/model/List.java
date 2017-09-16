package com.model;

import java.util.ArrayList;

/**
 * Created by hdy on 2017/9/16.
 */
public class List {
    private String listTitle;
    private ArrayList<SmallNews> lists;
    private Integer page;
    private Integer pageCount;

    public List(String listTitle, ArrayList<SmallNews> lists, Integer page, Integer pageCount) {
        this.listTitle = listTitle;
        this.lists = lists;
        this.page = page;
        this.pageCount = pageCount;
    }

    public String getListTitle() {
        return listTitle;
    }

    public void setListTitle(String listTitle) {
        this.listTitle = listTitle;
    }

    public ArrayList<SmallNews> getLists() {
        return lists;
    }

    public void setLists(ArrayList<SmallNews> lists) {
        this.lists = lists;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        return "List{" +
                "listTitle='" + listTitle + '\'' +
                ", lists=" + lists +
                ", page=" + page +
                ", pageCount=" + pageCount +
                '}';
    }
}
