package com.djtech.demsoccer.fragments;


import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.djtech.demsoccer.R;
import com.djtech.demsoccer.activities.NewsViewingActivity;
import com.djtech.demsoccer.adapters.NewsRecyclerViewAdapter;
import com.djtech.demsoccer.network.NewsNetworkQueryClass;
import com.djtech.demsoccer.storage.NewsDatabase;
import com.djtech.demsoccer.utils.ConstantsClass;
import com.djtech.demsoccer.utils.NewsModel;
import com.djtech.demsoccer.utils.NewsSingleton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment implements  LoaderManager.LoaderCallbacks<String>, NewsRecyclerViewAdapter.ClickedArticleInterface {

    private RecyclerView newsRecyclerView;
    private NewsRecyclerViewAdapter adapter;
    private static List<NewsModel> arrayList;
    private ProgressBar newsProgressBar;

    private static NewsDatabase newsDatabase;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fragment_layout, container, false);
        newsRecyclerView = (RecyclerView) view.findViewById(R.id.newsRecyclerView);
        newsProgressBar = (ProgressBar) view.findViewById(R.id.news_progress_bar);
        newsDatabase = Room.databaseBuilder(this.getContext(),NewsDatabase.class, "database-name").build();
        arrayList = new ArrayList<>();

        getLoaderManager().initLoader(ConstantsClass.NEWS_LOADER_ID, null, this).forceLoad();
        //Log.v("David", NewsNetworkQueryClass.queryNewsLink());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        /*if(NewsSingleton.getInstance().getArrayList()!=null){
            getLoaderManager().initLoader(ConstantsClass.NEWS_LOADER_ID, null, this).cancelLoad();
            adapter = new NewsRecyclerViewAdapter(this.getContext(), this, newsDatabase);
            newsRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
            newsRecyclerView.setAdapter(adapter);
        }*/
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return new NewsNetworkQueryClass(this.getContext(), newsProgressBar);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
//        Log.v("David", data);
        arrayList = NewsNetworkQueryClass.processJSON(data);
        NewsSingleton.getInstance().setArrayList(arrayList);
        Handler mainHandler = new Handler(Looper.getMainLooper());

        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {

            } // This is your code
        };
        mainHandler.post(myRunnable);
        new MyAsyncs().execute("");

        /*adapter = new NewsRecyclerViewAdapter(this.getContext(), this, newsDatabase);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        newsRecyclerView.setAdapter(adapter);*/
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        loader.reset();
    }
    public class MyAsyncs extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... voids) {
            //Log.v("David", "Database data is " + newsDatabase.newsPostDAO().getAllPosts().toString());
            for (int i = 0; i<arrayList.size(); i++){
                newsDatabase.newsPostDAO().insertAllPosts(arrayList.get(i));
                Log.v("David", "Post inserted " + arrayList.get(i).toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void v) {
            super.onPostExecute(v);
            //Log.v("David", "Post inserted " + arrayList.get(0).toString());
            adapter = new NewsRecyclerViewAdapter(NewsFragment.this.getContext(), NewsFragment.this, newsDatabase);
            newsRecyclerView.setLayoutManager(new LinearLayoutManager(NewsFragment.this.getContext()));
            newsRecyclerView.setAdapter(adapter);

        }
    }

    @Override
    public void articleClicked(int position, String newslink) {
        Intent intent = new Intent(this.getActivity(), NewsViewingActivity.class);
        intent.putExtra(ConstantsClass.NEWS_FRAG_BUNDLE, newslink);
        startActivity(intent);
    }


}
