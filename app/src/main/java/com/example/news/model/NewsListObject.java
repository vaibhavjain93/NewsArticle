package com.example.news.model;

import java.util.ArrayList;

public class NewsListObject {
    private ArrayList<News> newsArrayList;
    private boolean isError;

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
