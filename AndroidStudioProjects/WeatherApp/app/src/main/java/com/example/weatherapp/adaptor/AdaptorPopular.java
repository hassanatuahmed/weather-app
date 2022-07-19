package com.example.weatherapp.adaptor;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.weatherapp.CartActivity;
import com.example.weatherapp.DetailActivity;
import com.example.weatherapp.R;
import com.example.weatherapp.databinding.LayoutListItemBinding;
import com.example.weatherapp.databinding.LoyoutPopularListItemBinding;
import com.example.weatherapp.domain.CategoryDomain;
import com.example.weatherapp.domain.FoodDomain;

import java.util.List;

public class AdaptorPopular extends RecyclerView.Adapter<AdaptorPopular.ViewHolder>{
    Context context;
    List<FoodDomain> foodDomains;


    public AdaptorPopular(Context context, List<FoodDomain> foodDomains) {
        this.context = context;
        this.foodDomains = foodDomains;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LoyoutPopularListItemBinding binding =LoyoutPopularListItemBinding.inflate(LayoutInflater.from(context),parent,false);
        return new ViewHolder(binding);
       // return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewCategoryName.setText(foodDomains.get(position).getTitle());
        holder.fee.setText(String.valueOf(foodDomains.get(position).getFee()));
        String picUrl="";
        switch (position){
            case 0:
            case 6: {
                picUrl="pizza";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(
                        holder.imageViewCategoryPic.getContext(), R.drawable.cart_background));
                break;
            }
            case 1:
            case 3: {
                picUrl="pop_2";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(
                        holder.imageViewCategoryPic.getContext(), R.drawable.cart_background));
                break;

            }
            case 2: {
                picUrl="pop_1";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(
                        holder.imageViewCategoryPic.getContext(), R.drawable.cart_background));
                break;

            }
            case 4: {
                picUrl="logo";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(
                        holder.imageViewCategoryPic.getContext(), R.drawable.cart_background));
                break;

            }
            case 5: {
                picUrl="pop_3";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(
                        holder.imageViewCategoryPic.getContext(), R.drawable.cart_background));
                break;

            }


        }
        int drawableResourceId=holder.mainLayout.getContext().getResources()
                .getIdentifier(picUrl,"drawable",holder.mainLayout.getContext().getPackageName());

        Glide.with(holder.mainLayout.getContext())
                .load(drawableResourceId).into(holder.imageViewCategoryPic);

        holder.addBtn.setOnClickListener(view -> {
            Intent intent= new Intent(holder.itemView.getContext(), DetailActivity.class);
            intent.putExtra("object",foodDomains.get(position));

            holder.itemView.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {

        return foodDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCategoryName,fee,addBtn;
        ImageView imageViewCategoryPic;
        RelativeLayout mainLayout;



        public ViewHolder(@NonNull LoyoutPopularListItemBinding binding) {
            super(binding.getRoot());
            textViewCategoryName=binding.textViewTitle;
            imageViewCategoryPic=binding.imgPic;
            fee=binding.txtFee;
            mainLayout=binding.mainLayout;
            addBtn = binding.addBtn;

        }


    }
}
