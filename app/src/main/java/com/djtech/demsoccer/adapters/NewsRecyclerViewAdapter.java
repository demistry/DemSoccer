package com.djtech.demsoccer.adapters;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.djtech.demsoccer.R;
import com.djtech.demsoccer.storage.NewsDatabase;
import com.djtech.demsoccer.utils.NewsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ILENWABOR DAVID on 17/02/2018.
 */

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder> {

    private static NewsDatabase newsDatabase;
    private static List<NewsModel> newsModels;
    private Context context;
    private ClickedArticleInterface articleInterfaceObject;

    public NewsRecyclerViewAdapter(Context context, ClickedArticleInterface articleInterfaceObject, NewsDatabase newsDatabases){
        //this.newsModels = newsModels;
        this.context = context;
        this.articleInterfaceObject = articleInterfaceObject;
        newsDatabase = newsDatabases;
        new MyAsync().execute("");
    }
    public static class MyAsync extends AsyncTask<String, Object, List<NewsModel>>{

        @Override
        protected List<NewsModel> doInBackground(String... voids) {
            Log.v("David", "Database data is " + newsDatabase.newsPostDAO().getAllPosts().toString());
            return newsDatabase.newsPostDAO().getAllPosts();
        }

        @Override
        protected void onPostExecute(List<NewsModel> newsModelss) {
            super.onPostExecute(newsModelss);
            newsModels = newsModelss;
        }
    }

    public interface ClickedArticleInterface{
        void articleClicked(int position, String newslink);
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item_layout, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NewsViewHolder holder, int position) {
        if (newsModels!=null){
            holder.titleText.setText(newsModels.get(position).getTitleText());

            holder.descriptionText.setText(newsModels.get(position).getDescriptionText());
            holder.timeText.setText(newsModels.get(position).getTimeText());
            Picasso.with(context).load(newsModels.get(position).getImageLink()).into(holder.newsImage);


            holder.linearLayout.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            articleInterfaceObject.articleClicked(holder.getAdapterPosition(), newsModels.get(holder.getAdapterPosition()).getNewsLink());
                        }
                    }
            );
        }


    }

    @Override
    public int getItemCount() {

        if (newsModels!=null)
            return newsModels.size();
        return 15;
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{
        private TextView titleText, descriptionText, timeText;
        private ImageView newsImage;
        private LinearLayout linearLayout;
        public NewsViewHolder(View itemView) {
            super(itemView);
            titleText = (TextView) itemView.findViewById(R.id.title_textview);
            descriptionText = (TextView) itemView.findViewById(R.id.description_textview);
            timeText = (TextView) itemView.findViewById(R.id.time_textview);
            newsImage = (ImageView) itemView.findViewById(R.id.newsImage);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.news_root_layout);
        }
    }
}
