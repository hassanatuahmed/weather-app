package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weatherapp.Helper.ManagementCart;
import com.example.weatherapp.databinding.ActivityDetailBinding;
import com.example.weatherapp.domain.FoodDomain;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;
    TextView textViewAddBtn,textViewFee,textViewTitle,textViewDescription, textViewNumberOrder;
    ImageView imageViewFoodPic,imageViewMinus,imageViewPlus;
    private FoodDomain foodDomainObject;
    int orderNumber=1;
    private ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
        getBundle();
        managementCart =new ManagementCart(this);
    }

    private void init(){
        textViewAddBtn=binding.addToCartBtn;
        textViewDescription = binding.txtDescription;
        textViewFee=binding.txtFee;
        textViewTitle=binding.txtTitle;
        textViewNumberOrder =binding.orderNumber;
        imageViewFoodPic=binding.imageViewFoodPic;
        imageViewMinus=binding.imageViewMinus;
        imageViewPlus=binding.imageViewPlus;
    }

    private void getBundle(){
        foodDomainObject = (FoodDomain) getIntent().getSerializableExtra("object");
        int drawableResourceId=this.getResources().getIdentifier(foodDomainObject.getPic(),
                "drawable",this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(imageViewFoodPic);
        textViewTitle.setText(foodDomainObject.getTitle());
        textViewFee.setText("GH"+ foodDomainObject.getFee());
        textViewDescription.setText(foodDomainObject.getDescription());
        textViewNumberOrder.setText(String.valueOf(orderNumber));

        imageViewPlus.setOnClickListener(view -> {
            orderNumber+=1;
            textViewNumberOrder.setText(String.valueOf(orderNumber));
        });

        imageViewMinus.setOnClickListener(view -> {
            if(orderNumber>1){
                orderNumber-=1;

            }
            textViewNumberOrder.setText(String.valueOf(orderNumber));

        });
        textViewAddBtn.setOnClickListener(view -> {
            foodDomainObject.setNumberInCart(orderNumber);
            managementCart.insertFood(foodDomainObject);
        });

    }
}