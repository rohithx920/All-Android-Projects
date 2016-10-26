package com.example.rohith.inclass_08;

import java.io.Serializable;

/**
 * Created by Rohith on 6/14/2016.
 */
public class TopApps implements Serializable {

String Appname;
    String Devloper_name;
    String Price;
    String Release_Date;
    String Category;
    String Image;

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
