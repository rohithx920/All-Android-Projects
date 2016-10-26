package com.example.rohith.p_list;

/**
 * Created by Rohith on 6/14/2016.
 */
public class Color  {

    String color_name;
    String color_hex;

    public Color(String color_name, String color_hex) {
        this.color_name = color_name;
        this.color_hex = color_hex;
    }

    public String getColor_name() {
        return color_name;
    }

    public void setColor_name(String color_name) {
        this.color_name = color_name;
    }

    public String getColor_hex() {
        return color_hex;
    }

    public void setColor_hex(String color_hex) {
        this.color_hex = color_hex;
    }

    @Override
    public String toString() {
        return color_name+color_hex;
    }
}
