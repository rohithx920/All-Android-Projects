package com.example.rohith.midterm;

import java.io.Serializable;

/**
 * Created by Rohith on 6/9/2016.
 */
//temperature, humidity, pressure,
//weather description(s) and weather icon(s)
public class weather implements Serializable {
    Double temperature;
    Double humidity;
    Double pressure;
    String description;
    String icon;

   /* public weather(int temperature, int humidity, int pressure, String description, String icon) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.description = description;
        this.icon = icon;
    } */

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
