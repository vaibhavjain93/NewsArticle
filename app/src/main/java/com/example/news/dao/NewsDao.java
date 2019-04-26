package com.example.news.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.news.model.News;

import java.util.List;

@Dao
public interface NewsDao {
    @Insert
    void insertAllNews(List<News> newsList);

    @Query("SELECT * FROM item_table")
    List<News> getAllNews();

    @Query("DELETE FROM item_table")
    void deleteAllNews();
}
