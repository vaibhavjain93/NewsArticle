package com.example.news.views.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.news.R;
import com.example.news.views.viewmodel.NewsDetailViewModel;

public class NewsDetailFragment extends Fragment {

    private NewsDetailViewModel mViewModel;
    private static final String WEB_URL = "web_url";
    private WebView webView;

    public static NewsDetailFragment newInstance(String url) {
        NewsDetailFragment newsDetailFragment = new NewsDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(WEB_URL,url);
        newsDetailFragment.setArguments(bundle);
        return newsDetailFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.news_detail_fragment, container, false);
        webView = root.findViewById(R.id.webviewDetail);
        String url="";
        if (getArguments()!=null) {
            url = getArguments().getString(WEB_URL);
        }
        webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.getSettings().setJavaScriptEnabled(false);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                webView.setVisibility(View.GONE);
                root.findViewById(R.id.error_webview).setVisibility(View.VISIBLE);
                super.onReceivedError(view, request, error);
            }
        });
        webView.loadUrl(url);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(NewsDetailViewModel.class);
        // TODO: Use the ViewModel
    }

}
