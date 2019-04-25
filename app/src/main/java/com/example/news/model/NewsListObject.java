package com.example.news.model;

import java.util.ArrayList;

public class NewsListObject {
    private ArrayList<News> newsArrayList;

    public NewsListObject() {
        this.newsArrayList = new ArrayList<>();
    }

    public ArrayList<News> getNewsArrayList() {
        return newsArrayList;
    }

    public void setNewsArrayList(ArrayList<News> newsArrayList) {
        this.newsArrayList = newsArrayList;
    }

    public void addNews(News news) {
        if (this.newsArrayList == null)
            this.newsArrayList = new ArrayList<>();
        this.newsArrayList.add(news);
    }

    @Override
    public String toString() {
        return newsArrayList.toString();
    }
}
