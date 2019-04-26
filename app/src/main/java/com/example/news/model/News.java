package com.example.news.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "item_table")
public class News {
    @ColumnInfo(name = "title")private String title;
    @ColumnInfo(name = "description")private String description;
    @ColumnInfo(name = "url")private String url;
    @ColumnInfo(name = "image_url")private String imageUrl;
    @PrimaryKey(autoGenerate = true) public int _id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Title:"+getTitle();
    }
}
