package com.djtech.demsoccer.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.djtech.demsoccer.adapters.NewsRecyclerViewAdapter;
import com.djtech.demsoccer.network.NewsNetworkQueryClass;
import com.djtech.demsoccer.utils.NewsModel;
import com.djtech.demsoccer.utils.NewsSingleton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment implements  LoaderManager.LoaderCallbacks<String> {

    private RecyclerView newsRecyclerView;
    private NewsRecyclerViewAdapter adapter;
    private ArrayList<NewsModel> arrayList;
    private ProgressBar newsProgressBar;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_fragment_layout, container, false);
        newsRecyclerView = (RecyclerView) view.findViewById(R.id.newsRecyclerView);
        newsProgressBar = (ProgressBar) view.findViewById(R.id.news_progress_bar);
        arrayList = new ArrayList<>();

        getLoaderManager().initLoader(0, null, this).forceLoad();
        //Log.v("David", NewsNetworkQueryClass.queryNewsLink());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(NewsSingleton.getInstance().getArrayList()!=null){
            getLoaderManager().initLoader(0, null, this).cancelLoad();
            adapter = new NewsRecyclerViewAdapter(this.getContext(),NewsSingleton.getInstance().getArrayList());
            newsRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
            newsRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return new NewsNetworkQueryClass(this.getContext(), newsProgressBar);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        Log.v("David", data);
        arrayList = NewsNetworkQueryClass.processJSON(data);
        NewsSingleton.getInstance().setArrayList(arrayList);
        adapter = new NewsRecyclerViewAdapter(this.getContext(),arrayList);
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        newsRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        loader.reset();
    }


}
