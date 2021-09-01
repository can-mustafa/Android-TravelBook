package com.mustafacan.travelmarks.roomdb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mustafacan.travelmarks.model.TravelMark;

@Database(entities = {TravelMark.class}, version = 1)
public abstract class TravelMarkDatabase extends RoomDatabase {
    public abstract TravelMarkDao travelMarkDao();
}


