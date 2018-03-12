package com.djtech.demsoccer.utils;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by ILENWABOR DAVID on 18/02/2018.
 */

@Entity
public class NewsModel {
    @PrimaryKey(autoGenerate = true)
    private int _id;


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


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}
