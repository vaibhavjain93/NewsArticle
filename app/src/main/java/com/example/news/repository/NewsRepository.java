package com.example.news.repository;

import android.arch.lifecycle.MutableLiveData;

import com.example.news.model.NewsListObject;
import com.example.news.networking.FetchNewsFromApi;

public class NewsRepository {
    public void fetchData(MutableLiveData<NewsListObject> liveData) {
        FetchNewsFromApi.getData(liveData);
    }
}
