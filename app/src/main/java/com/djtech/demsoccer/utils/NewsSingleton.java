package com.djtech.demsoccer.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ILENWABOR DAVID on 18/02/2018.
 */

public class NewsSingleton {

    private List<NewsModel> arrayList;
    private static final NewsSingleton ourInstance = new NewsSingleton();

    public static NewsSingleton getInstance() {
        return ourInstance;
    }

    private NewsSingleton() {
    }
    public List<NewsModel> getArrayList() {
        return arrayList;
    }

    public void setArrayList(List<NewsModel> arrayList) {
        this.arrayList = arrayList;
    }
}
