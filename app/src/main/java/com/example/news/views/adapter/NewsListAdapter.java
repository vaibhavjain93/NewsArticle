package com.example.news.views.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.news.R;
import com.example.news.listeners.NewsItemClickListener;
import com.example.news.model.News;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.MyViewHolder> {

    private List<News> mItems;
    private Context context;
    private NewsItemClickListener newsItemClickListener;

    public NewsListAdapter(Context context, NewsItemClickListener newsItemClickListener) {
        mItems = new ArrayList<>();
        this.context = context;
        this.newsItemClickListener = newsItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_news_item,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        final News news = mItems.get(i);
        Glide.with(context).load(news.getImageUrl()).into(viewHolder.coverImage);
        viewHolder.titleText.setText(news.getTitle());
        viewHolder.descText.setText(news.getDescription());
        viewHolder.coverImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newsItemClickListener.newsItemClicked(news.getUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setData(List<News> news) {
        Log.i(TAG, "setData: refreshed");
        this.mItems.clear();
        this.mItems = news;
        notifyDataSetChanged();
    }

    private static final String TAG = "201102387 NewstAdapter";
    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView coverImage;
        TextView titleText;
        TextView descText;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            coverImage = itemView.findViewById(R.id.img_news);
            descText = itemView.findViewById(R.id.textDesc);
            titleText = itemView.findViewById(R.id.textviewTitle);
        }
    }
}
