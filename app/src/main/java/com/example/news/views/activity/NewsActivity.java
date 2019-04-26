package com.example.news.views.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
    int currFrag;
    String FRAG_KEY = "curr_frag";
    String FRAG1 = "frag1";
    String FRAG2 = "frag2";

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
        if (savedInstanceState == null) {
            inflateFragment1();
        }
        else {
            String fragKey;
            if (savedInstanceState.getInt(FRAG_KEY) == 1) {
                fragKey = FRAG1;
            }
            else {
                fragKey = FRAG2;
            }
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(fragKey);

        }
    }

    private void inflateFragment1() {
        currFrag = 1;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_fragment, NewsListFragment.newInstance(),FRAG1)
                .commit();
    }
    private void inflateFragment2(String url) {
        currFrag = 2;
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container_fragment, NewsDetailFragment.newInstance(url),FRAG2)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt(FRAG_KEY,currFrag);
    }
}
