package com.example.news.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.example.news.dao.NewsDao;
import com.example.news.db.AppDatabase;
import com.example.news.model.News;
import com.example.news.model.NewsListObject;
import com.example.news.networking.FetchNewsFromApi;
import com.example.news.utils.Utils;

import java.util.List;

public class NewsRepository {
    NewsDao newsDao;
    Application application;
    public NewsRepository(Application application) {
        this.application = application;
        AppDatabase appDatabaseJava = AppDatabase.getInstance(application);
        newsDao = appDatabaseJava.newsDao();
    }
    public void fetchData(MutableLiveData<NewsListObject> liveData) {
        if (Utils.isNetworkAvailable(application)) {
            FetchNewsFromApi.getData(liveData);
        }
        else {
            fetchDataFromDb(liveData);
        }
    }

    public void fetchDataFromDb(MutableLiveData<NewsListObject> liveData) {
        (new FetchFromDB()).execute(liveData);
    }

    public void insertNews(List<News> list) {
        (new InsertToDbTask()).execute(list);
    }

    class InsertToDbTask extends AsyncTask<List<News>,Void,Void> {
        @Override
        protected Void doInBackground(List<News>... lists) {
            if (lists[0]!=null) {
                newsDao.deleteAllNews();
                newsDao.insertAllNews(lists[0]);
            }
            return null;
        }
    }

    class FetchFromDB extends AsyncTask<MutableLiveData<NewsListObject>,Void,Void> {

        @Override
        protected Void doInBackground(MutableLiveData<NewsListObject>... mutableLiveData) {
            if (mutableLiveData[0] != null) {
                NewsListObject newsListObject = new NewsListObject();
                newsListObject.setNewsArrayList(newsDao.getAllNews());
                mutableLiveData[0].postValue(newsListObject);
            }
            return null;
        }
    }
}
