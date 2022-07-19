package com.example.weatherapp.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.weatherapp.R;
import com.example.weatherapp.databinding.LayoutListItemBinding;
import com.example.weatherapp.domain.CategoryDomain;
import com.example.weatherapp.domain.FoodDomain;

import java.util.ArrayList;
import java.util.List;

public class AdaptorCategory extends RecyclerView.Adapter<AdaptorCategory.ViewHolder>{
    Context context;
    List<CategoryDomain> category;

    public AdaptorCategory(Context context, ArrayList<CategoryDomain> category) {
        this.context = context;
        this.category = category;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutListItemBinding binding =LayoutListItemBinding.inflate(LayoutInflater.from(context),parent,false);
        return new ViewHolder(binding);
       // return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewCategoryName.setText(category.get(position).getTitle());
        String picUrl="";
        switch (position){
            case 0: {
                picUrl="cat_1";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(
                        holder.imageViewCategoryPic.getContext(), R.drawable.cat_background));
                break;
            }
            case 1: {
                picUrl="cat_2";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(
                        holder.imageViewCategoryPic.getContext(),R.drawable.cat_background2));
                break;

            }
            case 2: {
                picUrl="cat_3";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(
                        holder.imageViewCategoryPic.getContext(),R.drawable.cat_background3));
                break;

            }
            case 3: {
                picUrl="cat_4";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(
                        holder.imageViewCategoryPic.getContext(),R.drawable.cat_background4));
                break;

            }
            case 4: {
                picUrl="cat_1";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(
                        holder.imageViewCategoryPic.getContext(),R.drawable.cat_background5));
                break;

            }
            case 5: {
                picUrl="cat_4";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(
                        holder.imageViewCategoryPic.getContext(),R.drawable.cat_background6));
                break;

            }
            case 6: {
                picUrl="pizza";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(
                        holder.imageViewCategoryPic.getContext(),R.drawable.cat_background7));
                break;

            }
            case 7: {
                picUrl="pop_2";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(
                        holder.imageViewCategoryPic.getContext(),R.drawable.cat_background8));
                break;

            }

        }
        int drawableResourceId=holder.mainLayout.getContext().getResources()
                .getIdentifier(picUrl,"drawable",holder.mainLayout.getContext().getPackageName());

        Glide.with(holder.mainLayout.getContext())
                .load(drawableResourceId).into(holder.imageViewCategoryPic);

    }

    @Override
    public int getItemCount() {

        return category.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCategoryName;
        ImageView imageViewCategoryPic;
        LinearLayout mainLayout;



        public ViewHolder(@NonNull LayoutListItemBinding binding) {
            super(binding.getRoot());
            textViewCategoryName=binding.textViewCategoryName;
            imageViewCategoryPic=binding.imageViewCategoryPic;
            mainLayout=binding.mainLayout;

        }
    }
}
