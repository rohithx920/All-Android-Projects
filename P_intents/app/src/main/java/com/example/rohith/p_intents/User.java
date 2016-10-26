package com.example.rohith.p_intents;

import java.io.Serializable;

/**
 * Created by Rohith on 6/8/2016.
 */
public class User implements Serializable {
    int id;
    String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
