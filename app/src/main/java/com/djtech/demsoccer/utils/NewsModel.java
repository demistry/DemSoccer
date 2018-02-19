package com.djtech.demsoccer.utils;

/**
 * Created by ILENWABOR DAVID on 18/02/2018.
 */

public class NewsModel {
    private String titleText;
    private String descriptionText;
    private String timeText;
    private String imageLink;
    private String newsLink;

    public String getNewsLink() {
        return newsLink;
    }
    public String getTitleText() {
        return titleText;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public String getTimeText() {
        return timeText;
    }

    public String getImageLink() {
        return imageLink;
    }


    public NewsModel(String titleText, String descriptionText, String newsLink, String timeText, String imageLink) {
        this.titleText = titleText;
        this.descriptionText = descriptionText;
        this.timeText = timeText;
        this.imageLink = imageLink;
        this.newsLink = newsLink;
    }







}
