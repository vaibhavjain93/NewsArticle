package com.example.news.views.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.news.R;
import com.example.news.views.viewmodel.NewsMainViewModel;
import com.example.news.views.fragment.NewsListFragment;


public class NewsActivity extends AppCompatActivity {

    NewsMainViewModel newsMainViewModel;
    private static final String TAG = "201102387 NewsActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsMainViewModel = ViewModelProviders.of(this).get(NewsMainViewModel.class);
        newsMainViewModel.frag1Tofrag2.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                Log.i(TAG, "onChanged: "+integer);
            }
        });
        inflateFragments();
    }

    private void inflateFragments() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_fragment, NewsListFragment.newInstance(),"")
                .commit();
    }
}
