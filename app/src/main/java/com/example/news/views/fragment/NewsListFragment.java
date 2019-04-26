package com.example.news.views.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.news.R;
import com.example.news.listeners.NewsItemClickListener;
import com.example.news.model.NewsListObject;
import com.example.news.utils.Constants;
import com.example.news.views.viewmodel.NewsListViewModel;
import com.example.news.views.viewmodel.NewsMainViewModel;
import com.example.news.views.adapter.NewsListAdapter;

import java.util.Random;

public class NewsListFragment extends Fragment implements NewsItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    private NewsListViewModel mViewModel;
    private NewsMainViewModel mainViewModel;
    private RecyclerView recyclerView;
    private NewsListAdapter newsListAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View loadContainer;
    private Handler loadHandler;
    private TextView loadText;

    public static NewsListFragment newInstance() {
        return new NewsListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.news_list_fragment, container, false);
        recyclerView = root.findViewById(R.id.recycler_news);
        swipeRefreshLayout = root.findViewById(R.id.swipe_refresh);
        loadContainer = root.findViewById(R.id.load_container);
        swipeRefreshLayout.setOnRefreshListener(this);
        newsListAdapter = new NewsListAdapter(getContext(),this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(newsListAdapter);
        loadHandler = new Handler();
        loadText = root.findViewById(R.id.load_text);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        startLoadHandler();
    }

    void startLoadHandler() {
        loadHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "run: ");
                setLoadText();
                startLoadHandler();
            }
        },700);
    }

    private static final String TAG = "201102387 NewsListFr";
    void setLoadText() {
        loadText.setText(Constants.INTRO_ARR[(new Random().nextInt(Constants.INTRO_ARR.length))]);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NewsListViewModel.class);
        if (getActivity() != null) {
            mainViewModel = ViewModelProviders.of(getActivity()).get(NewsMainViewModel.class);
        }
        mViewModel.liveData.observe(this, new Observer<NewsListObject>() {
            @Override
            public void onChanged(@Nullable NewsListObject newsListObject) {
                swipeRefreshLayout.setRefreshing(false);
                if (newsListObject != null) {
                    if (newsListObject.isError()) {
                        mViewModel.fetchDataFromDb();
                    }
                    else {
                        loadContainer.setVisibility(View.GONE);
                        loadHandler.removeCallbacksAndMessages(null);
                        newsListAdapter.setData(newsListObject.getNewsArrayList());
                        mViewModel.insertToDb(newsListObject.getNewsArrayList());

                    }
                }
            }
        });
        mViewModel.fetchData();
    }

    @Override
    public void newsItemClicked(String url) {
        mainViewModel.frag1Tofrag2.setValue(url);
    }

    @Override
    public void onRefresh() {
        mViewModel.fetchData();
        swipeRefreshLayout.setRefreshing(true);
    }
}
