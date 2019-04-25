package com.example.news.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.example.news.model.News;

import java.util.ArrayList;

public class NewsMainViewModel extends AndroidViewModel {

    public MutableLiveData<Integer> frag1Tofrag2 = new MutableLiveData<>();

    public NewsMainViewModel(@NonNull Application application) {
        super(application);
    }
}
