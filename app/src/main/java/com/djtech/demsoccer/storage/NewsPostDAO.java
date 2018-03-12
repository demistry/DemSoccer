package com.djtech.demsoccer.storage;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.djtech.demsoccer.utils.NewsModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ILENWABOR DAVID on 10/03/2018.
 */

@Dao
public interface NewsPostDAO {
    @Query("SELECT * FROM NewsModel")
    List<NewsModel> getAllPosts();

    @Insert
    void insertAllPosts(NewsModel... newsModels);

    @Delete
    void deletePost(NewsModel model);
}
