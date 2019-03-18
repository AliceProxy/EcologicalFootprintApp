package com.example.ecologicalfootprintapp.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface ReccoDao {

    @Insert
    void insert(Recco recco);

    @Delete
    void delete(Recco recco);
}
