package com.djtech.demsoccer.utils;

import java.util.ArrayList;

/**
 * Created by ILENWABOR DAVID on 18/02/2018.
 */

public class NewsSingleton {

    private ArrayList<NewsModel> arrayList;
    private static final NewsSingleton ourInstance = new NewsSingleton();

    public static NewsSingleton getInstance() {
        return ourInstance;
    }

    private NewsSingleton() {
    }
    public ArrayList<NewsModel> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<NewsModel> arrayList) {
        this.arrayList = arrayList;
    }
}
