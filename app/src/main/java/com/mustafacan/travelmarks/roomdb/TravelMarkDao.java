package com.mustafacan.travelmarks.roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.mustafacan.travelmarks.model.TravelMark;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface TravelMarkDao {

    @Query("SELECT * FROM TravelMark")
    Flowable<List<TravelMark>> getAll();

    @Insert
    Completable insert(TravelMark travelMark);

    @Delete
    Completable delete(TravelMark travelMark);

}
