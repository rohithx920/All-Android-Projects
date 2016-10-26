package com.example.rohith.afinal;

import java.io.Serializable;

/**
 * Created by Rohith on 6/28/2016.
 */
public class Person implements Serializable {
    String person_icon;
    String person_name;
    String person_budget;
    String person_no_gifts;

    public String getPerson_spent() {
        return person_spent;
    }

    public void setPerson_spent(String person_spent) {
        this.person_spent = person_spent;
    }

    String person_spent;

    public String getPerson_icon() {
        return person_icon;
    }

    public void setPerson_icon(String person_icon) {
        this.person_icon = person_icon;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getPerson_budget() {
        return person_budget;
    }

    public void setPerson_budget(String person_budget) {
        this.person_budget = person_budget;
    }

    public String getPerson_no_gifts() {
        return person_no_gifts;
    }

    public void setPerson_no_gifts(String person_no_gifts) {
        this.person_no_gifts = person_no_gifts;
    }
}
