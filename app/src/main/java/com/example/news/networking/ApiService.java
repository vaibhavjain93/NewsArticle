package com.example.news.networking;

import com.example.news.utils.Config;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("top-headlines?apiKey="+ Config.API_KEY +"&country=in")
    Call<ResponseBody> getNewsList();
}
