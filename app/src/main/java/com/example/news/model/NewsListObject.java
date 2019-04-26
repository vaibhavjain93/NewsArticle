package com.example.news.model;

import java.util.ArrayList;
import java.util.List;

public class NewsListObject {
    private List<News> newsArrayList;
    private boolean isError = false;

    public NewsListObject() {
        this.newsArrayList = new ArrayList<>();
    }

    public List<News> getNewsArrayList() {
        return newsArrayList;
    }

    public void setNewsArrayList(List<News> newsArrayList) {
        this.newsArrayList = newsArrayList;
    }

    public void addNews(News news) {
        if (this.newsArrayList == null)
            this.newsArrayList = new ArrayList<>();
        this.newsArrayList.add(news);
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    @Override
    public String toString() {
        return newsArrayList.toString();
    }
}
