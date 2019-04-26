package com.example.news.views.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.example.news.model.News;
import com.example.news.model.NewsListObject;
import com.example.news.repository.NewsRepository;

import java.util.List;

public class NewsListViewModel extends AndroidViewModel {
    public MutableLiveData<NewsListObject> liveData;
    private NewsRepository repository;

    public NewsListViewModel(@NonNull Application application) {
        super(application);
        repository = new NewsRepository(application);
        liveData = new MutableLiveData<>();
    }

    public void fetchData() {
        repository.fetchData(liveData);
    }

    public void fetchDataFromDb() {
        repository.fetchDataFromDb(liveData);
    }

    public void insertToDb(List<News> list) {
        repository.insertNews(list);
    }
}
