package com.djtech.demsoccer.network;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import com.djtech.demsoccer.activities.HomeActivity;
import com.djtech.demsoccer.fragments.NewsFragment;
import com.djtech.demsoccer.utils.NewsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ILENWABOR DAVID on 18/02/2018.
 */

public class NewsNetworkQueryClass extends AsyncTaskLoader<String> {
    private static final String url = "https://newsapi.org/v2/everything?" +
            "q=+chelsea,+conte&" +
            "language=en&" +
            "sortBy=publishedAt" +
            "&apiKey=9bb10c9febb943c3b9aa09de7a9e2a71";
    private static String receivedString;
    private static Response response;
    private ProgressBar bar;
    private static ArrayList<NewsModel> arrayList;

    public NewsNetworkQueryClass(Context context, ProgressBar bar) {
        super(context);
        this.bar = bar;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        bar.setVisibility(View.VISIBLE);
    }

    @Override
    public String loadInBackground() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();


        try {
            response = client.newCall(request).execute();
            receivedString = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return receivedString;
    }

    @Override
    public void deliverResult(String data) {
        super.deliverResult(data);
        bar.setVisibility(View.GONE);
    }

    public static ArrayList<NewsModel> processJSON(String jsonString){
        arrayList = new ArrayList<>();
        String titleText, descriptionText, timeText, imageLink;
        try {
            JSONObject object = new JSONObject(jsonString);
            JSONArray array = object.getJSONArray("articles");
            for (int i = 0; i<array.length(); i++){
                JSONObject object1 = array.getJSONObject(i);
                titleText = object1.getString("title");
                descriptionText = object1.getString("description");
                imageLink = object1.getString("urlToImage");
                timeText = object1.getString("publishedAt");
                arrayList.add(new NewsModel(titleText, descriptionText, timeText, imageLink));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    //public static String processJSon




}
