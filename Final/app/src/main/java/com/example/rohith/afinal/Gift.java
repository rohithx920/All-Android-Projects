package com.example.rohith.afinal;

import java.io.Serializable;

/**
 * Created by Rohith on 6/28/2016.
 */
public class Gift implements Serializable {
String gift,image,price;

    public String getGift() {
        return gift;
    }

    public void setGift(String gift) {
        this.gift = gift;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
