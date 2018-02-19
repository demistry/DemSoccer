package com.djtech.demsoccer.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * Created by ILENWABOR DAVID on 19/02/2018.
 */

public class WebClientClass extends WebViewClient {
    private ProgressBar progressBar;
    private WebView webView;
    private Context context;

    public WebClientClass(ProgressBar progressBar, WebView webView, Context context) {
        this.progressBar = progressBar;
        this.webView = webView;
        this.context = context;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return false;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if(view.canGoBack())progressBar.setVisibility(View.GONE);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        if (errorCode == ERROR_HOST_LOOKUP){
            webView.setVisibility(View.INVISIBLE);
            //Toast.makeText(context, "Error Loading...\n Check Internet Connection",Toast.LENGTH_SHORT).show();
            Snackbar.make(view, "Error Loading...\n Check Internet Connection", Snackbar.LENGTH_LONG).show();
        }
        else {
            webView.setVisibility(View.INVISIBLE);
            Snackbar.make(view, "Check Internet Connection", Snackbar.LENGTH_LONG).show();
            //Toast.makeText(context, "Check Internet Connection", Toast.LENGTH_SHORT).show();

        }
    }

}
