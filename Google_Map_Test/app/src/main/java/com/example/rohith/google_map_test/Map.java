package com.example.rohith.google_map_test;

import java.io.Serializable;

/**
 * Created by Rohith on 7/13/2016.
 */
public class Map implements Serializable {
    String source;



    String dest;
    String dist;
    String dur;
    String s_lat;
    String d_lat;
    String s_long;


    String d_long;
    String polyline;

    public String getPolyline() {
        return polyline;
    }

    public void setPolyline(String polyline) {
        this.polyline = polyline;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getD_long() {
        return d_long;
    }

    public void setD_long(String d_long) {
        this.d_long = d_long;
    }

    public String getS_long() {
        return s_long;
    }

    public void setS_long(String s_long) {
        this.s_long = s_long;
    }

    public String getD_lat() {
        return d_lat;
    }

    public void setD_lat(String d_lat) {
        this.d_lat = d_lat;
    }

    public String getS_lat() {
        return s_lat;
    }

    public void setS_lat(String s_lat) {
        this.s_lat = s_lat;
    }

    public String getDur() {
        return dur;
    }

    public void setDur(String dur) {
        this.dur = dur;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }


}
