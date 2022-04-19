package com.example.Dekanosidze.room;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;



@Dao
public interface UserDao {

    @Query("select * from bloodbank order by random() limit :limit")
    LiveData<List<BloodBank>> getRandom(int limit);

    /**
     * Returns a random Smiley.
     */
    @Query("select * from bloodbank order by random() limit 1")
    BloodBank getSmiley();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(BloodBank bloodBank);

    @Delete
    void delete(BloodBank smiley);

    @Query("SELECT * FROM BloodBank")
    DataSource.Factory<Integer,BloodBank>  getAll();

    @Query("SELECT * FROM BloodBank")
    List<BloodBank> getAllBloodies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ArrayList<BloodBank> bloodBanks);
}