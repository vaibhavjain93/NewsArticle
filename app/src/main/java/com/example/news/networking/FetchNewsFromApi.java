package com.example.news.networking;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.example.news.model.News;
import com.example.news.model.NewsListObject;
import com.example.news.parser.NewsListParser;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.news.networking.RetrofitClient.getInstance;

public class FetchNewsFromApi {
    private static final String TAG = "vaibhav FetchFromApi";
    public static void getData(final MutableLiveData<NewsListObject> liveData) {
        Log.i(TAG, "getData: started");
        Call<ResponseBody> call = RetrofitClient.getInstance().getNewsList();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                NewsListObject newsListObject;
                try {
                    if (response.body() != null) {
                        Log.i(TAG, "onResponse: received");
                        newsListObject = NewsListParser.parseNewsData(response.body().string());
                        Log.i(TAG, "onResponse: "+newsListObject.toString());
                    }
                    else {
                        newsListObject = new NewsListObject();
                        newsListObject.setError(true);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    newsListObject = new NewsListObject();
                    newsListObject.setError(true);
                }
                liveData.setValue(newsListObject);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure: ",t );
                NewsListObject newsListObject = new NewsListObject();
                newsListObject.setError(true);
                liveData.setValue(newsListObject);
            }
        });
    }
}
