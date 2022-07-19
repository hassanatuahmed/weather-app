package com.example.weatherapp.domain;

import java.io.Serializable;

public class FoodDomain implements Serializable {

    private String title;
    private double fee;
    private String pic;
    private String description;
    private int numberInCart;


    public FoodDomain(String title, double fee, String pic, String description) {
        this.title = title;
        this.fee = fee;
        this.pic = pic;
        this.description = description;
    }

//    public FoodDomain(String title, double fee, String pic, String description, int numberInCart) {
//        this.title = title;
//        this.fee = fee;
//        this.pic = pic;
//        this.description = description;
//        this.numberInCart = numberInCart;
//    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
