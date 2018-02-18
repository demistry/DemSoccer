package com.djtech.demsoccer.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.djtech.demsoccer.R;
import com.djtech.demsoccer.utils.NewsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ILENWABOR DAVID on 17/02/2018.
 */

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder> {

    String data;
    ArrayList<NewsModel> newsModels;
    Context context;

    public NewsRecyclerViewAdapter(Context context,ArrayList<NewsModel> newsModels){
        this.data = data;
        this.newsModels = newsModels;
    }
    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item_layout, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        holder.titleText.setText(newsModels.get(position).getTitleText());

        holder.descriptionText.setText(newsModels.get(position).getDescriptionText());
        holder.timeText.setText(newsModels.get(position).getTimeText());
        Picasso.with(context).load(newsModels.get(position).getImageLink()).into(holder.newsImage);
    }

    @Override
    public int getItemCount() {
        return newsModels.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{
        private TextView titleText, descriptionText, timeText;
        private ImageView newsImage;
        public NewsViewHolder(View itemView) {
            super(itemView);
            titleText = (TextView) itemView.findViewById(R.id.title_textview);
            descriptionText = (TextView) itemView.findViewById(R.id.description_textview);
            timeText = (TextView) itemView.findViewById(R.id.time_textview);
            newsImage = (ImageView) itemView.findViewById(R.id.newsImage);
        }
    }
}
