package com.djtech.demsoccer.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.djtech.demsoccer.R;
import com.djtech.demsoccer.utils.ConstantsClass;
import com.djtech.demsoccer.utils.WebClientClass;

/**
 * Created by ILENWABOR DAVID on 19/02/2018.
 */

public class NewsViewFragment extends Fragment {

    private WebView newsView;
    private WebClientClass webClient;
    private WebSettings webSettings;

    private ProgressBar progressBar;
    private Bundle bundle;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.news_view_layout, container, false);
        newsView = (WebView) view.findViewById(R.id.news_view_layout);
        progressBar = (ProgressBar) view.findViewById(R.id.news_view_progress_bar);

        bundle = getArguments();
        webClient = new WebClientClass(progressBar, newsView, this.getContext());
        webSettings = newsView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        newsView.setWebViewClient(webClient);
        newsView.loadUrl(bundle.getString(ConstantsClass.NEWS_FRAG_BUNDLE));
        return view;
    }

}
