package com.example.news.views.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.news.R;
import com.example.news.model.NewsListObject;
import com.example.news.views.viewmodel.NewsListViewModel;
import com.example.news.views.viewmodel.NewsMainViewModel;
import com.example.news.views.adapter.NewsListAdapter;

public class NewsListFragment extends Fragment {

    private NewsListViewModel mViewModel;
    private NewsMainViewModel mainViewModel;
    private RecyclerView recyclerView;
    private NewsListAdapter newsListAdapter;

    public static NewsListFragment newInstance() {
        return new NewsListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.news_list_fragment, container, false);
        recyclerView = root.findViewById(R.id.recycler_news);
        newsListAdapter = new NewsListAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(newsListAdapter);
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainViewModel.frag1Tofrag2.setValue(1);
            }
        });
        return root;
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
                newsListAdapter.setData(newsListObject.getNewsArrayList());
            }
        });
        mViewModel.fetchData();
    }

}
