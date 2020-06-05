package com.example.dictionary;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WordsDao {
    @Insert
     void insert(Words word);

    @Update
    void update(Words word);

    @Delete
    void delete(Words word) ;

    @Query(" DELETE FROM wordTable ")
    void deleteAllWords();

    @Query(" SELECT * FROM wordTable")
      LiveData<List<Words>>getAllWords();
}
