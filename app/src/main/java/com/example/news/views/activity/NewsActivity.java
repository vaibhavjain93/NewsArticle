package com.example.news.views.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.news.R;
import com.example.news.views.fragment.NewsDetailFragment;
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
        newsMainViewModel.frag1Tofrag2.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Log.i(TAG, "onChanged: "+s);
                inflateFragment2(s);
            }
        });
        inflateFragments();
    }

    private void inflateFragments() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_fragment, NewsListFragment.newInstance(),"")
                .commit();
    }
    private void inflateFragment2(String url) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container_fragment, NewsDetailFragment.newInstance(url),"")
                .addToBackStack(null)
                .commit();
    }
}
