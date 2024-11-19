package com.example.zampamortes;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Meal.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract MealDao mealDao();
}
