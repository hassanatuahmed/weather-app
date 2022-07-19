package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.weatherapp.adaptor.AdaptorCategory;
import com.example.weatherapp.adaptor.AdaptorPopular;
import com.example.weatherapp.databinding.ActivityMainBinding;
import com.example.weatherapp.domain.CategoryDomain;
import com.example.weatherapp.domain.FoodDomain;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private RecyclerView.Adapter adapter;
    private RecyclerView.Adapter adapter2;
    private RecyclerView recyclerViewCategoriesList;
    private RecyclerView recyclerViewPopularList;
    ArrayList<FoodDomain> foodDomains=new ArrayList<>();

    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        floatingActionButton=binding.floatignActionBtn;
        getSupportActionBar().hide();


        recyclerViewCategory();
        setRecyclerViewPopularList();
        floatingActionButton.setOnClickListener(view -> {

//            startActivity(new Intent(MainActivity.this,CartActivity.class));
            Intent intent= new Intent(getApplicationContext(), DetailActivity.class);
            intent.putExtra("object",foodDomains);

            startActivity(intent);
        });



    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager
                (this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategoriesList = binding.recyclerViewCategories;
        recyclerViewCategoriesList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> category=new ArrayList<>();

        category.add(new CategoryDomain("Pizza","cat_1"));
        category.add(new CategoryDomain("Burger","cat_2"));
        category.add(new CategoryDomain("Hot Dog","cat_3"));
        category.add(new CategoryDomain("Drink","cat_4"));
        category.add(new CategoryDomain("Donut","cat_5"));
        category.add(new CategoryDomain("Chicken","cat_3"));
        category.add(new CategoryDomain("Meat","cat_2"));
        category.add(new CategoryDomain("Cake","pizza"));

        adapter=new AdaptorCategory(MainActivity.this,category);
        recyclerViewCategoriesList.setAdapter(adapter);


    }


    public void setRecyclerViewPopularList(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager
                (this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPopularList=binding.recyclerViewPopular;
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);


        foodDomains.add(new FoodDomain("Pepperoni Pizza",4.0,"pizza",
                "open-faced baked pie of Italian origin, consisting of a thin layer" +
                        " of bread dough topped with spiced tomato sauce and cheese, " +
                        "often garnished with anchovies, sausage slices, mushrooms, etc."));

        foodDomains.add(new FoodDomain("Cheese Burger",10.0,"burger","The patty" +
                " needs to be juicy, the bun soft but sturdy, and you want the " +
                "meat/bun/accompaniment ratio to be even from first to last bite"));

        foodDomains.add(new FoodDomain("Pepperoni Pizza",45.0,"pizza1",
                "open-faced baked pie of Italian origin, consisting of a thin layer" +
                        " of bread dough topped with spiced tomato sauce and cheese, " +
                        "often garnished with anchovies, sausage slices, mushrooms, etc."));

        foodDomains.add(new FoodDomain("Cheese Burger",15.0,"burger","The patty" +
                " needs to be juicy, the bun soft but sturdy, and you want the " +
                "meat/bun/accompaniment ratio to be even from first to last bite"));

        foodDomains.add(new FoodDomain("Vegetable Pizza",50.0,"pizza2","roasted" +
                " red peppers, baby spinach, onions, mushrooms, tomatoes, and black olives"));

        adapter2=new AdaptorPopular(MainActivity.this,foodDomains);
        recyclerViewPopularList.setAdapter(adapter2);




    }
}