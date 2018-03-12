package com.djtech.demsoccer.storage;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.djtech.demsoccer.utils.NewsModel;

/**
 * Created by ILENWABOR DAVID on 10/03/2018.
 */

@Database(entities = {NewsModel.class}, version = 1, exportSchema = false)
public abstract class NewsDatabase extends RoomDatabase {
    public abstract NewsPostDAO newsPostDAO();

}
