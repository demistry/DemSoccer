package com.djtech.demsoccer.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.djtech.demsoccer.R;
import com.djtech.demsoccer.utils.ConstantsClass;
import com.djtech.demsoccer.utils.WebClientClass;

public class NewsViewingActivity extends AppCompatActivity {

    private WebView newsView;
    private WebClientClass webClient;
    private WebSettings webSettings;

    private ProgressBar progressBar;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_view_layout);
        getSupportActionBar().hide();

        newsView = (WebView) findViewById(R.id.news_view_layout);
        progressBar = (ProgressBar) findViewById(R.id.news_view_progress_bar);

        bundle = getIntent().getExtras();
        webClient = new WebClientClass(progressBar, newsView, this);
        webSettings = newsView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        newsView.setWebViewClient(webClient);
        newsView.loadUrl(bundle.getString(ConstantsClass.NEWS_FRAG_BUNDLE));

    }
}
