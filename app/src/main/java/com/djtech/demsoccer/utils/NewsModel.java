package com.djtech.demsoccer.utils;

/**
 * Created by ILENWABOR DAVID on 18/02/2018.
 */

public class NewsModel {
    private String titleText, descriptionText, timeText, imageLink;
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


    public NewsModel(String titleText, String descriptionText, String timeText, String imageLink) {
        this.titleText = titleText;
        this.descriptionText = descriptionText;
        this.timeText = timeText;
        this.imageLink = imageLink;
    }







}
