package com.example.news.views.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

public class NewsMainViewModel extends AndroidViewModel {

    public MutableLiveData<Integer> frag1Tofrag2 = new MutableLiveData<>();

    public NewsMainViewModel(@NonNull Application application) {
        super(application);
    }
}
