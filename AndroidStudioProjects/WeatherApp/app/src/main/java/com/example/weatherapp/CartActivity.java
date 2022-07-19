package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.weatherapp.Helper.ManagementCart;
import com.example.weatherapp.Helper.TinyDB;
import com.example.weatherapp.adaptor.CartAdapter;
import com.example.weatherapp.databinding.ActivityCartBinding;
import com.example.weatherapp.domain.FoodDomain;
import com.example.weatherapp.my_interface.ChangeNumberListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    ActivityCartBinding binding;
    private RecyclerView.Adapter adapter;
    TextView textViewItemTotal,textViewDeliveryFee,textViewTax,textViewTotal,
            textViewCheckoutBtn,textViewEmptyCart;

    TinyDB tinyDB;
    private ScrollView scrollView;
    ManagementCart managementCart;
    RecyclerView recyclerView;
    Serializable list;
    //FoodDomain foodDomainObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        list =  getIntent().getSerializableExtra("object");

        init();
        calculate();
        bottomNavigation();
    }
    private void init(){
        //managementCart=new ManagementCart(this);
        recyclerView=binding.recyclerView;
        textViewCheckoutBtn=binding.btnCheckout;
        textViewDeliveryFee=binding.txtDeliveryFee;
        textViewTax=binding.txtTaxFee;
        textViewTotal=binding.itemTotal;
        textViewItemTotal=binding.txtTotalTotal;
        textViewEmptyCart=binding.txtEmptyCart;
        tinyDB = new TinyDB(this);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        ArrayList<FoodDomain> foodDomains=new ArrayList<>();

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
        adapter = new CartAdapter((ArrayList<FoodDomain>) list, this,new ChangeNumberListener() {
            @Override
            public void changed() {
                calculate();

            }
        });
        recyclerView.setAdapter(adapter);
//        if(managementCart.getListCart().isEmpty()){
//            textViewEmptyCart.setVisibility(View.VISIBLE);
////            scrollView.setVisibility(View.GONE);
//        }else{
//            textViewEmptyCart.setVisibility(View.GONE);
////            scrollView.setVisibility(View.VISIBLE);
//        }



    }
    public void calculate(){
        double percentageTax=0.02;
        double delivery=10;
        double tax= 122.0;
        //double total=Math.round((managementCart.getTotalFee()+tax+delivery)*100)/100;
        double total=50.0;
        double itemTotal=total+tax+delivery;
        textViewItemTotal.setText("GHS"+itemTotal);
        textViewTax.setText("GHS"+tax);
        textViewDeliveryFee.setText("GHS"+delivery);
        textViewTotal.setText("GHS"+total);
    }

    public void bottomNavigation(){
        FloatingActionButton floatingActionButton=binding.floatignActionBtn;
        floatingActionButton.setOnClickListener(view -> {
            startActivity(new Intent(CartActivity.this,CartActivity.class));
        });
    }
}