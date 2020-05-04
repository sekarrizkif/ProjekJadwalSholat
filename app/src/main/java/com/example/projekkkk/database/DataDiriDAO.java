package com.example.projekkkk.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface DataDiriDAO {
    @Insert
    Long insertData(DataDiri dataDiri);

    @Query("Select * from datadiri_db")
    List<DataDiri> getData();

    @Delete
    int deletedata(DataDiri dataDiri);
}
