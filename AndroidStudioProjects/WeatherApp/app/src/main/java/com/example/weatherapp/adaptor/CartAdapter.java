package com.example.weatherapp.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.weatherapp.CartActivity;
import com.example.weatherapp.Helper.ManagementCart;
import com.example.weatherapp.databinding.LayoutCartItemBinding;
import com.example.weatherapp.databinding.LoyoutPopularListItemBinding;
import com.example.weatherapp.domain.FoodDomain;
import com.example.weatherapp.my_interface.ChangeNumberListener;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    ArrayList<FoodDomain> foodDomainLists;
    private ManagementCart managementCart;
    Context context;
    private ChangeNumberListener changeNumberListener;

//
//    public CartAdapter(List<FoodDomain> foodDomainLists,
//                       ManagementCart managementCart, ChangeNumberListener changeNumberListener,Context context) {
//        this.foodDomainLists = foodDomainLists;
//        this.managementCart = managementCart;
//        this.changeNumberListener = changeNumberListener;
//        this.context=context;
//    }

    public CartAdapter(ArrayList<FoodDomain> foodDomains, Context context, ChangeNumberListener changeNumberListener) {
        this.context=context;
        this.foodDomainLists = foodDomains;

        this.managementCart = new ManagementCart(context);
        this.changeNumberListener = changeNumberListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutCartItemBinding binding =LayoutCartItemBinding.inflate(LayoutInflater.from(context),parent,false);
        return new CartAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewTitle.setText(foodDomainLists.get(position).getTitle());
        holder.fee.setText(String.valueOf(foodDomainLists.get(position).getFee()));
        holder.totalEachItem.setText(String.valueOf(Math.round(foodDomainLists.get(position).
                getNumberInCart()* foodDomainLists.get(position).getFee())*100));
        holder.num.setText(String.valueOf(foodDomainLists.get(position).getNumberInCart()));

        int drawableResource =holder.itemView.getContext().
                getResources().getIdentifier(foodDomainLists.get(position).getPic(),
        "drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResource)
                .into(holder.pic);

        holder.plus.setOnClickListener(view -> {
            managementCart.plusNumber(foodDomainLists, position, new ChangeNumberListener() {
                @Override
                public void changed() {
                    notifyDataSetChanged();
                    changeNumberListener.changed();
                }
            });

        });

        holder.itemView.setOnClickListener(view -> {
            managementCart.minus(foodDomainLists, position, new ChangeNumberListener() {
                @Override
                public void changed() {
                    notifyDataSetChanged();
                    changeNumberListener.changed();
                }
            });
        });

    }

    @Override
    public int getItemCount() {
        return foodDomainLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle,fee,num,totalEachItem;
        ImageView pic,plus,minus;
        RelativeLayout relativeLayout;
        public ViewHolder(@NonNull LayoutCartItemBinding binding) {
            super(binding.getRoot());
            textViewTitle=binding.title;
            fee=binding.fee;
            num=binding.orderNumber;
            totalEachItem=binding.total;
            plus= binding.plus;
            minus= binding.minus;
            pic= binding.pic;
            relativeLayout=binding.mainLayout;


        }
    }
}
