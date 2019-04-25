package com.example.news.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.news.model.NewsListObject;
import com.example.news.repository.NewsRepository;

public class NewsListViewModel extends ViewModel {
    public MutableLiveData<NewsListObject> liveData = new MutableLiveData<>();
    NewsRepository repository;
    public void fetchData() {
        repository = new NewsRepository();
        repository.fetchData(liveData);
    }
}
