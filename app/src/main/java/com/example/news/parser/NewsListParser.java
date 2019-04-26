package com.example.news.parser;

import com.example.news.model.News;
import com.example.news.model.NewsListObject;
import com.example.news.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NewsListParser {
    public static NewsListObject parseNewsData(String jsonBody) {
        NewsListObject newsListObject = new NewsListObject();
        if (jsonBody != null && !jsonBody.isEmpty()) {
            try {
                JSONObject jsonObject = new JSONObject(jsonBody);
                JSONArray articleArray = jsonObject.optJSONArray(Constants.ARTICLES);
                for (int i = 0; i < articleArray.length(); i++) {
                    JSONObject articleObj = articleArray.optJSONObject(i);
                    if (articleObj != null) {
                        News news = new News();
                        news.setImageUrl(articleObj.optString(Constants.URLTOIMAGE));
                        news.setTitle(articleObj.optString(Constants.TITLE));
                        news.setUrl(articleObj.optString(Constants.URL));
                        news.setDescription(articleObj.optString(Constants.DESCRIPTION));
                        newsListObject.addNews(news);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return newsListObject;
    }
}
