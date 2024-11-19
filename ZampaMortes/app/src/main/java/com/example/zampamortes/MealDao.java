package com.example.zampamortes;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;
import java.util.List;

@Dao
public interface MealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Meal meal);

    @Query("SELECT * FROM meals WHERE id = :id")
    Meal getMealById(int id);

    @Query("SELECT * FROM meals")
    List<Meal> getAllMeals();

    @Update
    void update(Meal meal);


    @Delete
    void delete(Meal meal);
}
