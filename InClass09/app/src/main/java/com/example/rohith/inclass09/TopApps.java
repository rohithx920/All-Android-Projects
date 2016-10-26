package com.example.rohith.inclass09;

/**
 * Created by This on 6/23/16.
 */
import java.io.Serializable;

public class TopApps implements Serializable {

    String Appname;
    String Devloper_name;
    String Price;
    String Release_Date;
    String Category;
    String Image;
    int fav;

    public int getFav() {
        return fav;
    }

    public void setFav(int fav) {
        this.fav = fav;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getAppname() {
        return Appname;
    }

    public void setAppname(String appname) {
        Appname = appname;
    }

    public String getDevloper_name() {
        return Devloper_name;
    }

    public void setDevloper_name(String devloper_name) {
        Devloper_name = devloper_name;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getRelease_Date() {
        return Release_Date;
    }

    public void setRelease_Date(String release_Date) {
        Release_Date = release_Date;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
