package com.example.zampamortes;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText et_id, et_name, et_description, et_price;
    private MealDao mealDao;
    private RecyclerView recyclerView;
    private MealAdapter mealAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        et_id = findViewById(R.id.et_id);
        et_name = findViewById(R.id.et_name);
        et_description = findViewById(R.id.et_description);
        et_price = findViewById(R.id.et_price);
        recyclerView = findViewById(R.id.recycler_view);

        AppDataBase db = Room.databaseBuilder(getApplicationContext(),
                        AppDataBase.class, "ZampaMortez")
                .allowMainThreadQueries()
                .build();

        mealDao = db.mealDao();

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMeal();
                loadMeals();
            }
        });

        Button deleteButton = findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteMeal();
                loadMeals();
            }
        });

        Button updateButton = findViewById(R.id.update_button);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateMeal();
                loadMeals();

            }
        });

        loadMeals();
    }


    private void loadMeals() {
        List<Meal> mealList = mealDao.getAllMeals();
        mealAdapter = new MealAdapter(mealList);
        recyclerView.setAdapter(mealAdapter);
        for (Meal meal : mealList) {
            System.out.println("ID: " + meal.getId() + ", Name: " + meal.getName() + ", Description: " + meal.getDescription() + ", Price: " + meal.getPrice());
        }
    }

    private void saveMeal() {
        int id = Integer.parseInt(et_id.getText().toString());
        String name = et_name.getText().toString();
        String description = et_description.getText().toString();
        double price = Double.parseDouble(et_price.getText().toString());
        Meal meal = new Meal(id, name, description, price);
        mealDao.insert(meal);

        Toast.makeText(this, "Comida guardada", Toast.LENGTH_SHORT).show();
    }

    private void searchMeal() {
        int id = Integer.parseInt(et_id.getText().toString());
        Meal meal = mealDao.getMealById(id);
        if (meal != null) {
            et_name.setText(meal.getName());
            et_description.setText(meal.getDescription());
            et_price.setText(String.valueOf(meal.getPrice()));
        } else {
            Toast.makeText(this, "Comida no encontrada", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateMeal() {
        int id = Integer.parseInt(et_id.getText().toString());
        String name = et_name.getText().toString();
        String description = et_description.getText().toString();
        double price = Double.parseDouble(et_price.getText().toString());
        Meal meal = new Meal(id, name, description, price);
        mealDao.update(meal);
        Toast.makeText(this, "Comida actualizada", Toast.LENGTH_SHORT).show();
    }

    private void deleteMeal() {
        int id = Integer.parseInt(et_id.getText().toString());
        Meal meal = mealDao.getMealById(id);
        if (meal != null) {
            mealDao.delete(meal);
            Toast.makeText(this, "Comida eliminada", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Comida no encontrada", Toast.LENGTH_SHORT).show();
        }
    }
}