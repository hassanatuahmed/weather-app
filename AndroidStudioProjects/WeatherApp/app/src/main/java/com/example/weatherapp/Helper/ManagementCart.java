package com.example.weatherapp.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.weatherapp.domain.FoodDomain;
import com.example.weatherapp.my_interface.ChangeNumberListener;

import java.util.ArrayList;
import java.util.List;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB=new TinyDB(context);
    }

    public void insertFood(FoodDomain item){
        ArrayList<FoodDomain> foodDomainArrayList = getListCart();
                boolean existAlready=false;
                int n=0;
                for(int i=0;i<foodDomainArrayList.size();i++){
                    if(foodDomainArrayList.get(i).getTitle().equals(item.getTitle())){
                        existAlready=true;
                        n=i;
                        break;
                    }
                }
                if(existAlready){
                    foodDomainArrayList.get(n).setNumberInCart(item.getNumberInCart());
                }else{
                    foodDomainArrayList.add(item);
                }
                tinyDB.putListObject("CardList",foodDomainArrayList);
                Toast.makeText(context,"Added To Your Cart",Toast.LENGTH_SHORT).show();


    }
    public ArrayList<FoodDomain> getListCart(){
        return tinyDB.getListObject("CartList");
    }

    public void plusNumber(List<FoodDomain> listFood, int postion, ChangeNumberListener changeNumberListener){
    listFood.get(postion).setNumberInCart(listFood.get(postion).getNumberInCart()+1);
    tinyDB.putListObject("CartList", (ArrayList<FoodDomain>) listFood);
    changeNumberListener.changed();
    }

    public void minus(List<FoodDomain> listFood,int position,ChangeNumberListener changeNumberListener){
        if(listFood.get(position).getNumberInCart()==1){
            listFood.remove(position);
        }else{
            listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart()-1);
        }
        tinyDB.putListObject("CartList", (ArrayList<FoodDomain>) listFood);
        changeNumberListener.changed();
    }

    public double getTotalFee(){
        List<FoodDomain> list=getListCart();
        double fee=0;
        for(int i=0;i<list.size();i++){
           // fee =fee +(list.get(i).getFee() * list.get(i).getNumberInCart());
            fee= 20;
        }
        return fee;
    }
}
