package com.example.news.views.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.news.R;
import com.example.news.model.News;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.MyViewHolder> {

    private ArrayList<News> mItems;
    private Context context;

    public NewsListAdapter(Context context) {
        mItems = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_news_item,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        News news = mItems.get(i);
        Glide.with(context).load(news.getImageUrl()).into(viewHolder.coverImage);
        viewHolder.titleText.setText(news.getTitle());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setData(ArrayList<News> news) {
        this.mItems.clear();
        this.mItems = news;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView coverImage;
        TextView titleText;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            coverImage = itemView.findViewById(R.id.img_news);
            titleText = itemView.findViewById(R.id.textDesc);
        }
    }
}
