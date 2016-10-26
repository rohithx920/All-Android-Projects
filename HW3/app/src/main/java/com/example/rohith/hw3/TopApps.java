package com.example.rohith.hw3;

import java.io.Serializable;

/**
 * Created by Rohith on 6/14/2016.
 */
public class TopApps implements Serializable {
    String id;
    String app_title;
    String developer_name;
    String url;
    String small_photo_url;
    String large_photo_url;
    String app_price;

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    String release_date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApp_title() {
        return app_title;
    }

    public void setApp_title(String app_title) {
        this.app_title = app_title;
    }

    public String getDeveloper_name() {
        return developer_name;
    }

    public void setDeveloper_name(String developer_name) {
        this.developer_name = developer_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {

        return super.toString();
    }

    public String getSmall_photo_url() {
        return small_photo_url;
    }

    public void setSmall_photo_url(String small_photo_url) {
        this.small_photo_url = small_photo_url;
    }

    public String getLarge_photo_url() {
        return large_photo_url;
    }

    public void setLarge_photo_url(String large_photo_url) {
        this.large_photo_url = large_photo_url;
    }

    public String getApp_price() {
        return app_price;
    }

    public void setApp_price(String app_price) {
        this.app_price = app_price;
    }
}
